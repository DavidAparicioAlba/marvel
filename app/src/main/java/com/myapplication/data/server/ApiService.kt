package com.myapplication.data.server

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface ApiService {

    companion object Factory {
        const val GET_CHARACTERS = "/v1/public/characters"
        const val GET_COMIC = "/v1/public/comics"
    }

    @GET(GET_CHARACTERS)
    suspend fun getCharacters(): Response<List<WrappedListCharactersResponse<Character>>>

    @GET(GET_CHARACTERS)
    suspend fun getDetails(@Body id: Int): Response<WrappedDetailsResponse<Character>>

}