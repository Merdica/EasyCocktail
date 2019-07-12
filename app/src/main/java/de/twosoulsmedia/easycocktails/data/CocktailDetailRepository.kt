package de.twosoulsmedia.easycocktails.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CocktailDetailRepository {

    private val TAG = "CocktailDetailRepo"

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val cocktail = MutableLiveData<Drink>()

    fun loadCocktailById(cocktailId: String) {

        scope.launch {
            try {
                val webResponse = CocktailApiCalls.cocktailsApi.getDrinkByIdAsync(cocktailId)
                if (webResponse.isSuccessful) {
                    val response: Drinks? = webResponse.body()
                    Log.d(TAG, response?.toString())
                    cocktail.postValue(response?.drinks?.first())
                } else {
                    handleHTTPException(webResponse.code())
                }
            } catch (e: Exception) {
                handleExceptions(e)
            }
        }
    }
}