package com.myapplication.domain.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myapplication.data.server.ApiService
import com.myapplication.data.server.WrappedDetailsResponse
import com.myapplication.data.server.WrappedListCharactersResponse
import com.myapplication.domain.models.character.Character
import com.myapplication.domain.models.character.CharacterEntity
import com.myapplication.domain.uc.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepository @Inject constructor(val service: ApiService) : MarvelRepositoryInterface {

    override suspend fun getCharacters(): Flow<BaseResult<List<CharacterEntity>, WrappedListCharactersResponse<Character>>> {

        return flow {
            val response = service.getCharacters()
            if (response.isSuccessful){
                val body = response.body()!!
                val characters = mutableListOf<CharacterEntity>()
                var character: CharacterEntity
                body.forEach { characterResponse ->
                    character = CharacterEntity(
                        characterResponse.id.toString(),
                        characterResponse.updatedAt.toString(),
                        characterResponse.createdAt.toString(),
                        characterResponse.name.toString()
                    )
                    characters.add(character)
                }
                emit(BaseResult.Success(characters))
            }else{
                val type = object : TypeToken<WrappedListCharactersResponse<Character>>(){}.type
                val err = Gson().fromJson<WrappedListCharactersResponse<Character>>(response.errorBody()!!.charStream(), type)!!
                err.code = response.code()
                emit(BaseResult.Error(err))
            }
        }
    }

    override suspend fun getDetails(id: Int): Flow<BaseResult<CharacterEntity, WrappedDetailsResponse<java.lang.Character>>> {
        TODO("Not yet implemented")
    }

}