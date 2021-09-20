package engineer.yusrisahrul.awesomeapp.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.repository.local.LocalRepository
import engineer.yusrisahrul.awesomeapp.repository.remote.RemoteRepository
import engineer.yusrisahrul.awesomeapp.state.photo.PhotoState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : Repository {
    override fun getAllPhoto(
        callback: MutableLiveData<PhotoState>,
        data: MutableLiveData<PagedList<DataPhoto>>
    ) {
        remoteRepository.getAllPhoto(callback, data)
    }

    override fun getDisposible(): CompositeDisposable {
        return remoteRepository.getDisposible()
    }
}