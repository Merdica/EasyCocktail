package de.twosoulsmedia.easycocktails.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CocktaiListlViewModelFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CocktailListlViewModel::class.java)) {
            return CocktailListlViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}