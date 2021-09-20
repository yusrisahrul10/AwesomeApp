package engineer.yusrisahrul.awesomeapp.network

import engineer.yusrisahrul.awesomeapp.data.model.ResponsePhoto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("curated")
    fun getAllPhoto(
        @Query("page") page : Int
    ) : Single<ResponsePhoto>
}