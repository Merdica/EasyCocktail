package de.twosoulsmedia.easycocktails.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CocktailRepository {

    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val cocktails = MutableLiveData<List<Drink>>()

    val cocktail = MutableLiveData<Drink>()

    fun loadCocktails() {

        scope.launch {
            val webResponse = WebAccess.cocktailsApi.getDrinksFromTypeCocktail().await()
            if (webResponse.isSuccessful) {
                val response : Drinks? = webResponse.body()
                Log.d("loadCocktails", response?.toString())
                cocktails.postValue(response?.drinks)
            } else {
                // Print error information to the console
                Log.d("loadCocktails", "Error ${webResponse.code()}")
            }
        }
    }

    fun loadRandomCocktail() {

        scope.launch {
            val webResponse = WebAccess.cocktailsApi.getRandomDrink().await()
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
            val webResponse = WebAccess.cocktailsApi.getDrinkByID(cocktailId).await()
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