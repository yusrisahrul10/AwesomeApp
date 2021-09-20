package engineer.yusrisahrul.awesomeapp.data.model

import com.google.gson.annotations.SerializedName

data class DataPhoto(
    @SerializedName("id") val id: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
    @SerializedName("photographer") val photographer: String,
    @SerializedName("src") val src: PhotoSrc,
)
