package com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.ui.activity


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rocca.umrah.elmenustask.R
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.domain.enitities.tagsResponses.Tag
import com.rocca.umrah.elmenustask.presentation.ItemDetailScreen.ui.activity.ElmenusItemDeailsActivity
import com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.ui.adapter.ElmenusAdapter
import com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.viewmodel.ElmenusTagsViewModel
import com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.viewmodel.ElmenusTagsViewModelFactory
import com.rocca.umrah.elmenustask.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_elmenus_tags_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import kotlin.coroutines.CoroutineContext

internal const val INTENT_KEY = "ELMENUS_ITEM_DETAIL"
private const val TRANSITION_KEY = "elmenusItem"
internal const val LOLLIPOP_VERSION = 21

class MainActivity : AppCompatActivity(), KodeinAware, CoroutineScope {


    /////////////////////Inject View Model ////////////////////////////////
    override val kodein by closestKodein()
    private val viewModelFactory: ElmenusTagsViewModelFactory by instance()
    private lateinit var viewModel: ElmenusTagsViewModel

    //////////////////////Generating Courtine Scope ///////////////////////////
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private lateinit var job: Job

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var elmenusTagsLiveData: LiveData<MutableList<Tag?>>

    ////////////////////Creating An adapter and mutuable list for tags //////////////////////
    private val mElmenusTagsList = mutableListOf<Tag?>()

    ///////////////Creating an adapter and mutuable list for Items Of Specific tag ///////////////
    private val mElmenusItemOfSpecificTag = mutableListOf<Item?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()
        linearLayoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        setupLayout()
        setupViewModel()
        getElmenusTags(0)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ElmenusTagsViewModel::class.java)
    }

    private fun setupLayout() {
        /////////////////////Setup Recycler View For Tags Layout ///////////////////////////////////////////////////
        recyclerview_tags.apply {
            layoutManager = linearLayoutManager
            adapter = ElmenusAdapter(mElmenusTagsList, { tagItem: Tag? -> onElmenusTagItemClicked(tagItem) })
        }
        /////////////////////Setup Recycler View For Items /////////////////////////////////////////
        recyclerview_items.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter =
                ElmenusAdapter(mElmenusItemOfSpecificTag, { item: Item? -> onElmenusItemClicked(item) })
        }

        recyclerview_tags.addOnScrollListener(object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                getElmenusTags(page)
            }

        });
    }


    private fun getElmenusTags(page: Int) = launch {
        val elmenusTagsLiveData = viewModel.requestElmenusTags(page)
        elmenusTagsLiveData.observe(this@MainActivity, Observer {
            if (it == null) {
                return@Observer
            }
            mElmenusTagsList.clear()
            mElmenusTagsList.addAll(it)
            recyclerview_tags.adapter?.notifyDataSetChanged()
        })
    }

    private fun onElmenusTagItemClicked(tag: Tag?) = launch {
        val elmenusItemsLiveData = viewModel.requestElmenusItemsOfSpecificTag(tag?.tagName)
        elmenusItemsLiveData.observe(this@MainActivity, Observer {
            if (it == null) {
                determineRecyclerViewVisibility(View.VISIBLE, View.GONE)
                return@Observer
            }
            if (!it.isEmpty()) {
                determineRecyclerViewVisibility(View.GONE, View.VISIBLE)
                mElmenusItemOfSpecificTag.clear()
                mElmenusItemOfSpecificTag.addAll(it)
                recyclerview_items.adapter?.notifyDataSetChanged()
            } else {
                determineRecyclerViewVisibility(View.VISIBLE, View.GONE)
            }
        })
    }

    private fun onElmenusItemClicked(item: Item?) {
        val elmenusItemDetailItent = Intent(applicationContext, ElmenusItemDeailsActivity::class.java)
        elmenusItemDetailItent.putExtra(INTENT_KEY, item)
        if (Build.VERSION.SDK_INT >= LOLLIPOP_VERSION) {
            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, iv_tag, TRANSITION_KEY)
            startActivity(elmenusItemDetailItent, activityOptionsCompat.toBundle())
        } else {
            startActivity(elmenusItemDetailItent)
        }
    }

    private fun determineRecyclerViewVisibility(textViewVIsibility: Int, recyclerViewVisibility: Int) {
        tv_items_error.visibility = textViewVIsibility
        recyclerview_items.visibility = recyclerViewVisibility
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
