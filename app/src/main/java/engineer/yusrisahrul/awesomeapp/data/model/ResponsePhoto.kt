package engineer.yusrisahrul.awesomeapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponsePhoto(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val per_page: Int,
    @SerializedName("photos") val photos: List<DataPhoto>,
    @SerializedName("total_results") val total_results: Int,
    @SerializedName("next_page") val next_page: String
)
