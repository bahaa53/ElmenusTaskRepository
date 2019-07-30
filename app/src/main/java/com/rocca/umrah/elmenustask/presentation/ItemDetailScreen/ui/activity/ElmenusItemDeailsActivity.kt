package com.rocca.umrah.elmenustask.presentation.ItemDetailScreen.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rocca.umrah.elmenustask.R
import com.rocca.umrah.elmenustask.domain.enitities.itemsResponses.Item
import com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.ui.activity.INTENT_KEY
import com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.ui.activity.LOLLIPOP_VERSION
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_elmenus_item_details_layout.*
import kotlinx.android.synthetic.main.content_scrolling.*
import java.lang.Exception

class ElmenusItemDeailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elmenus_item_details_layout)
        /////////Get Parcable Extra Object ///////////
        val itemDataObj: Item? = intent?.getParcelableExtra<Item?>(INTENT_KEY)
        /////////Set Data Into Ui //////////////////////////
        setDataIntoUi(itemDataObj)
    }

    private fun setDataIntoUi(itemDataObj: Item?) {
        Picasso.get()
            .load(itemDataObj?.photoUrl)
            .noFade()
            .into(iv_tag, object : Callback {
                override fun onSuccess() {
                    if (Build.VERSION.SDK_INT >= LOLLIPOP_VERSION) {
                        startPostponedEnterTransition()
                    }
                }

                override fun onError(e: Exception?) {
                    if (Build.VERSION.SDK_INT >= LOLLIPOP_VERSION) {
                        startPostponedEnterTransition()
                    }
                }
            });
        tv_item_name.text = itemDataObj?.name
        tv_item_description.text = itemDataObj?.description

    }
}