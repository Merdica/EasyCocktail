package de.twosoulsmedia.easycocktails.data

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object CocktailApiCalls {

    var okHttpClient = OkHttpClient.Builder()
            .addInterceptor(CustomNetworkInterceptor()) // This is used to add ApplicationInterceptor.
            .addNetworkInterceptor(CustomNetworkInterceptor()) //This is used to add NetworkInterceptor.
            .build()


    val cocktailsApi: CocktailApiClient by lazy {
        Log.d("WebAccess", "Creating retrofit client")
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()

        // Create Retrofit client
        return@lazy retrofit.create(CocktailApiClient::class.java)
    }
}