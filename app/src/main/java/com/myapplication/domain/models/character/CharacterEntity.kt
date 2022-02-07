package com.myapplication.domain.models.character

import com.myapplication.domain.models.image.ImageEntity

data class CharacterEntity(var id: Int,
                           var name: String? = null,
                           var description: String? = null,
                           var thumbnail: ImageEntity? = null, )