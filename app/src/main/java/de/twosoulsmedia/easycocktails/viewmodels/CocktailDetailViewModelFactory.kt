package de.twosoulsmedia.easycocktails.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.twosoulsmedia.easycocktails.data.CocktailRepository
import java.lang.IllegalArgumentException

class CocktailDetailViewModelFactory(private val cocktailId: String) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CocktailDetailViewModel::class.java)) {
            return CocktailDetailViewModel(cocktailId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}