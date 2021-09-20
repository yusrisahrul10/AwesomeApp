package engineer.yusrisahrul.awesomeapp.repository.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.data.model.ResponsePhoto
import engineer.yusrisahrul.awesomeapp.network.ApiService
import engineer.yusrisahrul.awesomeapp.network.factory.Factory
import engineer.yusrisahrul.awesomeapp.repository.Repository
import engineer.yusrisahrul.awesomeapp.state.photo.PhotoState
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val config: PagedList.Config,
    private val factory: Factory
) : Repository{

    var disposable: CompositeDisposable = CompositeDisposable()

    override fun getAllPhoto(
        callback: MutableLiveData<PhotoState>,
        data: MutableLiveData<PagedList<DataPhoto>>
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            LivePagedListBuilder(
                factory.allPhotoDataFactory.also {
                    it.liveData = callback
                },
                config
            ).build().observeForever(data::postValue)
        }
    }

    override fun getDisposible(): CompositeDisposable = disposable
}