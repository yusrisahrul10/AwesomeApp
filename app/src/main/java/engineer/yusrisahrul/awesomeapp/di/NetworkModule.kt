package engineer.yusrisahrul.awesomeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import engineer.yusrisahrul.awesomeapp.BuildConfig
import engineer.yusrisahrul.awesomeapp.network.ApiService
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun providesApiKey() : Interceptor = Interceptor { chain ->
        var request: Request = chain.request()
        request = request.newBuilder().addHeader(
            "Authorization", BuildConfig.apiKey
        ).build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
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

    @Provides
    @Singleton
    fun providesHttpAdapter(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            client(client)
            baseUrl(BuildConfig.baseUrl)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        }.build()
    }

    @Provides
    @Singleton
    fun providesApiEndPoint(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}