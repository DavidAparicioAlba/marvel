package com.myapplication.domain.repository

import com.myapplication.data.server.WrappedDetailsResponse
import com.myapplication.data.server.WrappedListCharactersResponse
import com.myapplication.domain.models.character.CharacterEntity
import com.myapplication.domain.uc.BaseResult
import kotlinx.coroutines.flow.Flow
import com.myapplication.domain.models.character.Character

interface MarvelRepositoryInterface {

    suspend fun getCharacters() : Flow<BaseResult<List<CharacterEntity>, WrappedListCharactersResponse<Character>>>
    suspend fun getDetails(id: Int) : Flow<BaseResult<CharacterEntity, WrappedDetailsResponse<Character>>>
}