package engineer.yusrisahrul.awesomeapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import dagger.hilt.android.lifecycle.HiltViewModel
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.repository.Repository
import engineer.yusrisahrul.awesomeapp.state.photo.PhotoState
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
        private val repository: Repository
) : ViewModel() {

    val statePhoto : MutableLiveData<PhotoState> by lazy {
        MutableLiveData<PhotoState>()
    }

    val data : MutableLiveData<PagedList<DataPhoto>> by lazy {
        MutableLiveData<PagedList<DataPhoto>>()
    }

    fun getAllPhoto() {
        repository.getAllPhoto(statePhoto, data)
    }
}