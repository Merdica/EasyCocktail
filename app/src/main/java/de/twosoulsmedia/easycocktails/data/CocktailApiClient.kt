package de.twosoulsmedia.easycocktails.data

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiClient {

    @GET("random.php")
    fun getRandomDrink(): Deferred<Response<Drinks>>

    @GET("filter.php?c=Cocktail")
    fun getDrinksFromTypeCocktail(): Deferred<Response<Drinks>>

    @GET("lookup.php?")
    fun getDrinkByID(@Query("i") id: String): Deferred<Response<Drinks>>
}