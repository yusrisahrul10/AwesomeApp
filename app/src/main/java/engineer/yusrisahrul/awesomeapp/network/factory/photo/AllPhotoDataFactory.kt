package engineer.yusrisahrul.awesomeapp.network.factory.photo

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import engineer.yusrisahrul.awesomeapp.data.model.DataPhoto
import engineer.yusrisahrul.awesomeapp.network.source.photo.AllPhotoDataSource
import engineer.yusrisahrul.awesomeapp.state.photo.PhotoState
import javax.inject.Inject

class AllPhotoDataFactory @Inject constructor(
    private val allPhotoDataSource: AllPhotoDataSource
) : DataSource.Factory<Int, DataPhoto>() {

    lateinit var liveData: MutableLiveData<PhotoState>

    override fun create(): DataSource<Int, DataPhoto> {
        return allPhotoDataSource.also {
            it.liveData = liveData
        }
    }
}