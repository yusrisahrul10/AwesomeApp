package engineer.yusrisahrul.awesomeapp.ui

import engineer.yusrisahrul.awesomeapp.data.FakeRemoteRepository
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class PhotoViewModelTest {

    lateinit var fakeRemoteRepository: FakeRemoteRepository

    @Before
    fun setUp() {
        fakeRemoteRepository = FakeRemoteRepository()
    }

    @Test
    fun getAllPhoto() {
        val dataPhoto = fakeRemoteRepository.getAllPhoto(1).blockingGet()

        assertNotNull(dataPhoto.photos)
        assertEquals(15, dataPhoto.photos.size)
    }
}