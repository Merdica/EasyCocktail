package de.twosoulsmedia.easycocktails.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiClient {

    @GET("random.php")
    suspend fun getRandomDrinkAsync(): Response<Drinks>

    @GET("filter.php?c=Cocktail")
    suspend fun getDrinksFromTypeCocktailAsync(): Response<Drinks>

    @GET("lookup.php?")
    suspend fun getDrinkByIdAsync(@Query("i") id: String): Response<Drinks>
}