package de.twosoulsmedia.easycocktails.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CocktailDetailRepository {

    private val parentJob = Job()
    private val coroutineContext : CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val cocktail = MutableLiveData<Drink>()

    fun loadCocktailById(cocktailId: String) {

        scope.launch {
            val webResponse = WebAccess.cocktailsApi.getDrinkByID(cocktailId).await()
            if (webResponse.isSuccessful) {
                val response : Drinks? = webResponse.body()
                Log.d("loadCocktailById", response?.toString())
                cocktail.postValue(response?.drinks?.first())
            } else {
                // Print error information to the console
                Log.d("loadCocktailById", "Error ${webResponse.code()}")
            }
        }
    }
}