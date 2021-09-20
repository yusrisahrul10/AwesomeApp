package engineer.yusrisahrul.awesomeapp.network.factory

import engineer.yusrisahrul.awesomeapp.network.factory.photo.AllPhotoDataFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class Factory @Inject constructor(
    val allPhotoDataFactory: AllPhotoDataFactory
)