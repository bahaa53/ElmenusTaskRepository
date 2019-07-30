package com.rocca.umrah.elmenustask.presentation

import android.app.Application
import com.rocca.umrah.elmenustask.data.api.ApiClient
import com.rocca.umrah.elmenustask.data.api.elmenusTagsNetworkDataSource.TagsNetworkDataSourceImp
import com.rocca.umrah.elmenustask.data.api.checkingConnectivity.ConnectivityInterceptor
import com.rocca.umrah.elmenustask.data.api.checkingConnectivity.ConnectivityInterceptorImpl
import com.rocca.umrah.elmenustask.data.api.elmenusItemsNetworkDataSource.ElmenusItemsNetworkDataSource
import com.rocca.umrah.elmenustask.data.api.elmenusItemsNetworkDataSource.ElmenusItemsNetworkDataSourceImpl
import com.rocca.umrah.elmenustask.data.api.elmenusTagsNetworkDataSource.TagsNetworkDataSource
import com.rocca.umrah.elmenustask.data.database.ElmenusDatabase
import com.rocca.umrah.elmenustask.data.repositories.ElmenusItemsRepositoryImpl
import com.rocca.umrah.elmenustask.data.repositories.TagsRepostoryImp
import com.rocca.umrah.elmenustask.domain.repository.ElmenusItemsRepository
import com.rocca.umrah.elmenustask.domain.repository.TagsRepository
import com.rocca.umrah.elmenustask.domain.usecase.ElmenusItemsUseCase
import com.rocca.umrah.elmenustask.domain.usecase.GetElmenusTagsUseCase
import com.rocca.umrah.elmenustask.presentation.TagsItemsBaseScreen.viewmodel.ElmenusTagsViewModelFactory
import org.kodein.di.Kodein.Companion.lazy
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApp : Application(), KodeinAware {

    override val kodein = lazy {
        import(androidXModule(this@MyApp))

        ////////////////////////////// Inject Database + its Daos //////////////////
        bind() from singleton { ElmenusDatabase(instance()) }
        bind() from singleton { instance<ElmenusDatabase>().TagsDao() }
        bind() from singleton { instance<ElmenusDatabase>().ItemsDao() }
        ///////////////////////////////////////////////////////////////////////
        //////////Inject Api Interfaces To Access EndPoints /////////////////
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiClient(instance()) }
        bind<TagsNetworkDataSource>() with singleton { TagsNetworkDataSourceImp(instance()) }
        bind<ElmenusItemsNetworkDataSource>() with singleton { ElmenusItemsNetworkDataSourceImpl(instance()) }
        /////////////////////////////////////////////////////////////////
        ///////////Inject Tags Repository ////////////////////////////////////
        bind<TagsRepository>() with singleton { TagsRepostoryImp(instance(), instance()) }

        ///////////Inject Items Repository ////////////////////////////////////
        bind<ElmenusItemsRepository>() with singleton { ElmenusItemsRepositoryImpl(instance(), instance()) }
        //////////////////////////////////////////////////////////////////////////
        /////////////////////////Inject TAGS USE CASE /////////////////////////////
        bind() from singleton { GetElmenusTagsUseCase(instance()) }
        /////////////////////////Inject Items USE CASE /////////////////////////////
        bind() from singleton { ElmenusItemsUseCase(instance()) }
        /////////////////////////////////////////////////////////////////////////
        ///////////////Inject Tags Viewmodel Factory ////////////////////////////
        bind() from provider { ElmenusTagsViewModelFactory(instance(), instance()) }
        ////////////////////////////////////////////////////////////////////////

    }

    override fun onCreate() {
        super.onCreate()
    }
}