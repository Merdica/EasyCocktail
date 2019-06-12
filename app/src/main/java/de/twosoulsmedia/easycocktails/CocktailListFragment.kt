package de.twosoulsmedia.easycocktails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import de.twosoulsmedia.easycocktails.adapters.CocktailListAdapter
import de.twosoulsmedia.easycocktails.databinding.FragmentCocktailListBinding
import de.twosoulsmedia.easycocktails.viewmodels.CocktaiListlViewModelFactory
import de.twosoulsmedia.easycocktails.viewmodels.CocktailListlViewModel

class CocktailListFragment : Fragment() {

    private lateinit var viewModel: CocktailListlViewModel
    private lateinit var viewModelFactory: CocktaiListlViewModelFactory

    private var listAdapter: CocktailListAdapter = CocktailListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        activity?.let {
            viewModelFactory = CocktaiListlViewModelFactory()
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(CocktailListlViewModel::class.java)
        }

        val binding = DataBindingUtil.inflate<FragmentCocktailListBinding>(inflater, R.layout.fragment_cocktail_list, container, false)
        binding.viewModel = viewModel
        binding.recyclerviewCocktailList.adapter = listAdapter
        viewModel.cocktails.observe(this, Observer { drinks ->
            drinks?.let { listAdapter.drinks = it }
            listAdapter.notifyDataSetChanged()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCocktailList()
    }
}
