package com.myapplication.data.server

import okhttp3.Interceptor
import okhttp3.Request
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val requestBuilder: Request.Builder

        requestBuilder = request.newBuilder()

        return chain.proceed(requestBuilder.build())
    }
}