package engineer.yusrisahrul.awesomeapp.di

import androidx.paging.PagedList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import engineer.yusrisahrul.awesomeapp.network.factory.Factory
import engineer.yusrisahrul.awesomeapp.repository.DataRepository
import engineer.yusrisahrul.awesomeapp.repository.local.LocalRepository
import engineer.yusrisahrul.awesomeapp.repository.remote.RemoteRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideRemoteRepository(
        config: PagedList.Config,
        factory: Factory
    ) : RemoteRepository =
        RemoteRepository(
        config,
        factory
    )

    @Provides
    @ViewModelScoped
    fun provideLocalRepository(
        config : PagedList.Config
    ) : LocalRepository =
        LocalRepository(config)

    @Provides
    @ViewModelScoped
    fun provideDataRepository(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ) : DataRepository = DataRepository(
        remoteRepository,
        localRepository
    )
}