package de.twosoulsmedia.easycocktails

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCocktails()
    }

    private fun loadCocktails() {
        GlobalScope.launch(Dispatchers.Main) {
            val webResponse = WebAccess.cocktailsApi.getDrinksAsync().await()
            if (webResponse.isSuccessful) {
                val partList : List<Drinks>? = webResponse.body()
                Log.d("BLAAA","BLA")
                Log.d("cocktail", partList?.toString())
            } else {
                // Print error information to the console
                Log.d("BLAAA", "Error ${webResponse.code()}")
            }
        }
    }
}
