package engineer.yusrisahrul.awesomeapp.state.photo

import engineer.yusrisahrul.awesomeapp.data.model.ResponsePhoto

sealed class PhotoState {
    object Loading : PhotoState()
    data class Result(val data: ResponsePhoto) : PhotoState()
    data class Error(val error: Throwable) : PhotoState()
}
