package de.twosoulsmedia.easycocktails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_add_cocktail.*
import org.jetbrains.anko.db.StringParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select


class AddCocktailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cocktail)

        val categories = ArrayList<String>()

        database.use {

            select("Ingrediant", "name").exec {
                parseList(StringParser).forEach {
                    Log.d("test", "TADAAA: " + it)
                    categories.add(it)
                }
            }
        }

        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = spinner_cocktail_ingrediants
        spinner.adapter = dataAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                val item = spinner.adapter.getItem(pos)

                textview_ingrediant_name.text = item.toString()
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

            }

        }
        button_add_cocktail.setOnClickListener {

            val cocktailName = edittext_cocktail_name.text.toString()

            database.use {
                insert("Cocktails",
                        "name" to cocktailName
                )

                insert("CocktailIngrediants",
                        "cocktail_id" to select("Cocktails", "id").whereSimple("name = ?", cocktailName).toString(),
                        "ingrediant_id" to select("Ingrediant", "id").whereSimple("name = ?", textview_ingrediant_name.text.toString()).toString()
                )
            }

            finish()
        }
    }
}