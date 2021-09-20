package engineer.yusrisahrul.awesomeapp.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.state.photo.PhotoState
import io.reactivex.disposables.CompositeDisposable

interface Repository {

    fun getAllPhoto(
        callback : MutableLiveData<PhotoState>,
        data : MutableLiveData<PagedList<DataPhoto>>
    )

    fun getDisposible() : CompositeDisposable
}