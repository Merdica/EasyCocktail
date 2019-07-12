package de.twosoulsmedia.easycocktails.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CocktailRepository {

    private val TAG = "CocktailRepository"

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val cocktails = MutableLiveData<List<Drink>>()

    val cocktail = MutableLiveData<Drink>()

    fun loadCocktails() {

        scope.launch(getJobErrorHandler()) {
            try {
                val webResponse = CocktailApiCalls.cocktailsApi.getDrinksFromTypeCocktailAsync()
                if (webResponse.isSuccessful) {
                    val response: Drinks? = webResponse.body()
                    Log.d(TAG, response?.toString())
                    cocktails.postValue(response?.drinks)
                } else {
                    handleHTTPException(webResponse.code())
                }
            } catch (e: Exception) {
                handleExceptions(e)
            }
        }
    }

    fun loadRandomCocktail() {

        scope.launch {
            try {
                val webResponse = CocktailApiCalls.cocktailsApi.getRandomDrinkAsync()
                if (webResponse.isSuccessful) {
                    val response: Drinks? = webResponse.body()
                    Log.d(TAG, response?.toString())
                    cocktails.postValue(response?.drinks)
                } else {
                    handleHTTPException(webResponse.code())
                }
            } catch (e: Exception) {
                handleExceptions(e)
            }
        }
    }

    fun loadCocktailById(cocktailId: String) {

        scope.launch {
            try {
                val webResponse = CocktailApiCalls.cocktailsApi.getDrinkByIdAsync(cocktailId)
                if (webResponse.isSuccessful) {
                    val response: Drinks? = webResponse.body()
                    Log.d("loadCocktailById", response?.toString())
                    cocktails.postValue(response?.drinks)
                } else {
                    handleHTTPException(webResponse.code())
                }
            } catch (e: Exception) {
                handleExceptions(e)
            }
        }
    }
}