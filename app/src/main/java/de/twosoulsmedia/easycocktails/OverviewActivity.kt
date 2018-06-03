package de.twosoulsmedia.easycocktails

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.*

class OverviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDatabase()

        button_add_ingrediant.setOnClickListener {
            startActivity( Intent(this, AddIngrediantActivity::class.java))
        }

        button_add_cocktail.setOnClickListener {
            startActivity( Intent(this, AddCocktailActivity::class.java))
        }

        recyclerview_cocktails.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        recyclerview_cocktails.adapter = CocktailAdapter(getCocktails(), this)
    }

    fun getCocktails(): ArrayList<String> {

        val array = ArrayList<String>()

        val cocktails = HashMap<String, String>()

        database.use {
            select("Cocktails", "name").exec {
                parseList(StringParser).forEach {
                    Log.d("test", "TADAAA: " + it)
                    array.add(it)
                }
            }
        }

        return array
    }

    fun initDatabase() {

        database.use {

            createTable("Cocktails", true,
                    "id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                    "name" to TEXT
            )

            createTable("Ingrediant", true,
                    "id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                    "name" to TEXT
            )

            createTable("CocktailIngrediants", true,
                    "id" to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                    "cocktail_id" to INTEGER,
                    "ingrediant_id" to INTEGER,
                    FOREIGN_KEY("cocktail_id", "Cocktails", "id"),
                    FOREIGN_KEY("ingrediant_id", "Ingrediant", "id")
            )

        }

    }
}
