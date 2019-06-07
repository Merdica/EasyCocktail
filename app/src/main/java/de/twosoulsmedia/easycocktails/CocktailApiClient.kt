package de.twosoulsmedia.easycocktails

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CocktailApiClient {
    @GET("random.php")
    fun getDrinksAsync(): Deferred<Response<List<Drinks>>>

}