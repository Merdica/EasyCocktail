package de.twosoulsmedia.easycocktails.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import java.io.IOException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

class CocktailRepository {

    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val cocktails = MutableLiveData<List<Drink>>()

    val cocktail = MutableLiveData<Drink>()

    fun loadCocktails() {

        scope.launch(getJobErrorHandler()) {

//            try {
                val webResponse = CocktailApiCalls.cocktailsApi.getDrinksFromTypeCocktailAsync().await()
                if (webResponse.isSuccessful) {
                    val response: Drinks? = webResponse.body()
                    Log.d("loadCocktails", response?.toString())
                    cocktails.postValue(response?.drinks)
                } else {
                    //handle error - HTTP status code
                    webResponse.code()
                    Log.d("CocktailRepository", "HTTP Error ${webResponse.code()}")
                }
//            } catch (error: IOException) {
//                //Error with network request
//                Log.d("CocktailRepository", "IOException: ${error.message}")
//            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.d("CocktailRepository", "Error: ${e.message}")

        when(e) {
            is IOException -> {
                //handle IO EXception
            }
            else -> Log.d("CocktailRepository", "Unknown Error: ${e.message}")
        }
    }

    private fun handleIOException(exception: IOException) {
        when (exception) {
            is UnknownHostException -> Log.d("CocktailRepository", "")
        }
    }

    fun loadRandomCocktail() {

        scope.launch {
            val webResponse = CocktailApiCalls.cocktailsApi.getRandomDrinkAsync().await()
            if (webResponse.isSuccessful) {
                val response : Drinks? = webResponse.body()
                Log.d("loadRandomCocktail", response?.toString())
                cocktails.postValue(response?.drinks)
            } else {
                // Print error information to the console
                Log.d("loadRandomCocktail", "Error ${webResponse.code()}")
            }
        }
    }

    fun loadCocktailById(cocktailId: String) {

        scope.launch {
            val webResponse = CocktailApiCalls.cocktailsApi.getDrinkByIdAsync(cocktailId).await()
            if (webResponse.isSuccessful) {
                val response : Drinks? = webResponse.body()
                Log.d("loadCocktailById", response?.toString())
                cocktails.postValue(response?.drinks)
            } else {
                // Print error information to the console
                Log.d("loadCocktailById", "Error ${webResponse.code()}")
            }
        }
    }
}