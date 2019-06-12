package de.twosoulsmedia.easycocktails.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.twosoulsmedia.easycocktails.data.CocktailDetailRepository
import de.twosoulsmedia.easycocktails.data.Drink

class CocktailDetailViewModel(private val cocktailId: String) : ViewModel() {

    private var repository: CocktailDetailRepository = CocktailDetailRepository()

    private var _cocktail = MutableLiveData<Drink>()
    val cocktail: LiveData<Drink>
        get() = _cocktail

    init {
        _cocktail = repository.cocktail
        getCocktailDetails()
    }

    fun getCocktailDetails() {
        repository.loadCocktailById(cocktailId)
    }
}