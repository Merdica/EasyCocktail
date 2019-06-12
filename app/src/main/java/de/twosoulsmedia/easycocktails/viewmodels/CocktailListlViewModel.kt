package de.twosoulsmedia.easycocktails.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.twosoulsmedia.easycocktails.data.CocktailRepository
import de.twosoulsmedia.easycocktails.data.Drink

class CocktailListlViewModel : ViewModel(){

    private var repository: CocktailRepository = CocktailRepository()

    var cocktails: MutableLiveData<List<Drink>>

    init {
        cocktails = repository.cocktails
    }

    fun getCocktailList() {
        repository.loadCocktails()
    }
}