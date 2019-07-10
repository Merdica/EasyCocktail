package de.twosoulsmedia.easycocktails.data

import okhttp3.Interceptor
import okhttp3.Response

class CustomNetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)

        if(response.isSuccessful) {

        } else {

        }

        return response
    }

}