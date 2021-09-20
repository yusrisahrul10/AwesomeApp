package engineer.yusrisahrul.awesomeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import engineer.yusrisahrul.awesomeapp.network.ApiService
import engineer.yusrisahrul.awesomeapp.network.source.photo.AllPhotoDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideAllPhotoDataSource(
        apiService: ApiService
    ) : AllPhotoDataSource = AllPhotoDataSource(apiService)
}