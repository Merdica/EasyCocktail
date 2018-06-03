package de.twosoulsmedia.easycocktails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_ingrediant.*
import org.jetbrains.anko.db.insert

class AddIngrediantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_ingrediant)

        button_add_ingrediant.setOnClickListener {

            val newIngrediantName = edittext_ingrediant_name.text.toString()

            database.use {

                insert("Ingrediant",
                        "name" to newIngrediantName
                )
            }

            finish()
        }
    }
}