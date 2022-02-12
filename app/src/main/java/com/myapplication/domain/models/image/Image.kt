package com.myapplication.domain.models.image

import com.google.gson.annotations.SerializedName

data class Image(@SerializedName("path") val path: String, @SerializedName("extension") val extension: String) {
    fun toEntity(): ImageEntity {
        return ImageEntity(path, extension)
    }
}