package engineer.yusrisahrul.awesomeapp.network.source.photo

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.network.ApiService
import engineer.yusrisahrul.awesomeapp.state.photo.PhotoState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllPhotoDataSource @Inject constructor(
    private val apiService: ApiService
): PageKeyedDataSource<Int, DataPhoto>(){

    lateinit var liveData: MutableLiveData<PhotoState>
    private val disposable by lazy {
        CompositeDisposable()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataPhoto>
    ) {
        apiService.getAllPhoto(1)
            .map<PhotoState> {
                callback.onResult(it.photos.toMutableList(), 1, 2)
                PhotoState.Result(it)
            }
            .onErrorReturn(PhotoState::Error)
            .toFlowable()
            .startWith(PhotoState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataPhoto>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataPhoto>) {
        apiService.getAllPhoto(params.key)
            .map<PhotoState> {
                callback.onResult(it.photos.toMutableList(), params.key + 1)
                PhotoState.Result(it)
            }
            .onErrorReturn(PhotoState::Error)
            .toFlowable()
            .startWith(PhotoState.Loading)
            .subscribe(liveData::postValue)
            .let { return@let disposable::add }
    }
}