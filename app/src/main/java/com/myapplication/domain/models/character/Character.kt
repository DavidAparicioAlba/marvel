package com.myapplication.domain.models.character

import com.google.gson.annotations.SerializedName
import com.myapplication.domain.models.image.Image

data class Character(@SerializedName("id") var id: Int,
                     @SerializedName("name") var name: String? = null,
                     @SerializedName("description") var description: String? = null,
                     @SerializedName("modified") var modified: String? = null,
                     @SerializedName("thumbnail") var thumbnail: Image? = null,
                     @SerializedName("urls") var urls: List<String> = listOf()
) {

}