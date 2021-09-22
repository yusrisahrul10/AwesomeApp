package engineer.yusrisahrul.awesomeapp.data

import engineer.yusrisahrul.awesomeapp.BuildConfig
import engineer.yusrisahrul.awesomeapp.data.model.ResponsePhoto
import engineer.yusrisahrul.awesomeapp.network.ApiService
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class FakeRemoteRepository {
    private fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private fun providesApiKey() : Interceptor = Interceptor { chain ->
        var request: Request = chain.request()
        request = request.newBuilder().addHeader(
            "Authorization", BuildConfig.apiKey
        ).build()
        chain.proceed(request)
    }

    fun providesHttpClient(
        interceptor: HttpLoggingInterceptor,
        apiKey: Interceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(interceptor)
            addInterceptor(apiKey)
        }.build()
    }

    private fun getRetrofit() : Retrofit = Retrofit.Builder().apply {
        baseUrl(BuildConfig.baseUrl)
        client(providesHttpClient(
            providesHttpLoggingInterceptor(),
            providesApiKey()
        ))
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
    }.build()

    fun getAllPhoto(page: Int) : Single<ResponsePhoto> =
        getRetrofit().create(ApiService::class.java).getAllPhoto(page)
}