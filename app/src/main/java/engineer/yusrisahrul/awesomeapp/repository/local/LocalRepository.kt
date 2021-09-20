package engineer.yusrisahrul.awesomeapp.repository.local

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.repository.Repository
import engineer.yusrisahrul.awesomeapp.state.photo.PhotoState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val config: PagedList.Config
) : Repository {

    var disposable: CompositeDisposable = CompositeDisposable()


    override fun getAllPhoto(
        callback: MutableLiveData<PhotoState>,
        data: MutableLiveData<PagedList<DataPhoto>>
    ) {
        throw UnsupportedOperationException()
    }

    override fun getDisposible(): CompositeDisposable = disposable
}