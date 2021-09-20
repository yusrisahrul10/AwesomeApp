package engineer.yusrisahrul.awesomeapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import engineer.yusrisahrul.awesomeapp.repository.DataRepository
import engineer.yusrisahrul.awesomeapp.repository.Repository

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {
    @Binds
    abstract fun bindRepository(
        dataRepository: DataRepository
    ) : Repository
}