package engineer.yusrisahrul.awesomeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import engineer.yusrisahrul.awesomeapp.network.factory.Factory
import engineer.yusrisahrul.awesomeapp.network.factory.photo.AllPhotoDataFactory
import engineer.yusrisahrul.awesomeapp.network.source.photo.AllPhotoDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataFactoryModule {

    @Provides
    @Singleton
    fun provideFactory(
        allPhotoDataFactory: AllPhotoDataFactory
    ) : Factory = Factory(
        allPhotoDataFactory
    )

    @Provides
    @Singleton
    fun provideAllPhotoFactory(
        allPhotoDataSource: AllPhotoDataSource
    ) : AllPhotoDataFactory = AllPhotoDataFactory(allPhotoDataSource)
}