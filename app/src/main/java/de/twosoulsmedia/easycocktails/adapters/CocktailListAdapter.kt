package de.twosoulsmedia.easycocktails.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import de.twosoulsmedia.easycocktails.CocktailListFragmentDirections
import de.twosoulsmedia.easycocktails.data.Drink
import de.twosoulsmedia.easycocktails.databinding.ListItemCocktailBinding

class CocktailListAdapter : RecyclerView.Adapter<CocktailListAdapter.ViewHolder>() {

    var drinks: List<Drink> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewHolder = ViewHolder(ListItemCocktailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        viewHolder.itemView.setOnClickListener {
            showItemDetails(drinks[viewHolder.adapterPosition].id, it)
        }
        return viewHolder
    }

    override fun getItemCount(): Int = drinks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name = drinks[position].name
        holder.binding.description = "Alcoholic, Ordinary Drink, Old-fashioned glass"
        holder.binding.thumbnail = drinks[position].thumbnail
    }

    private fun showItemDetails(drinkId: String, view: View) {
        val direction = CocktailListFragmentDirections.actionCocktailListFragmentToCocktailDetailFragment(drinkId)
        Navigation.findNavController(view).navigate(direction)
    }

    class ViewHolder(val binding: ListItemCocktailBinding) : RecyclerView.ViewHolder(binding.root)
}