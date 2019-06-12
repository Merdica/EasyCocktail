package de.twosoulsmedia.easycocktails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import de.twosoulsmedia.easycocktails.viewmodels.CocktailDetailViewModel
import de.twosoulsmedia.easycocktails.viewmodels.CocktailDetailViewModelFactory

class CocktailDetailFragment : Fragment() {

    private lateinit var viewModel: CocktailDetailViewModel
    private lateinit var viewModelFactory: CocktailDetailViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.let {
            viewModelFactory = CocktailDetailViewModelFactory(CocktailDetailFragmentArgs.fromBundle(arguments!!).drinkID)
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(CocktailDetailViewModel::class.java)
        }

        val binding = DataBindingUtil.inflate<de.twosoulsmedia.easycocktails.databinding.FragmentCocktailDetailBinding>(inflater, R.layout.fragment_cocktail_detail, container, false)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}
