package engineer.yusrisahrul.awesomeapp.utils

import dagger.hilt.android.testing.CustomTestApplication
import engineer.yusrisahrul.awesomeapp.ui.PhotoActivity

@CustomTestApplication(PhotoActivity::class)
interface HiltTestApplication