package de.twosoulsmedia.easycocktails.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Drinks(@field:Json(name = "drinks") val drinks: List<Drink>)

data class Drink(@field:Json(name = "idDrink") val id: String,
                 @field:Json(name = "strDrink") val name: String,
                 @field:Json(name = "strTags") val tags: String?,
                 @field:Json(name = "strCategory") val category: String?,
                 @field:Json(name = "strGlass") val glass: String?,
                 @field:Json(name = "strInstructions") val instructions: String?,
                 @field:Json(name = "strIngredient1") val ingredient1: String?,
                 @field:Json(name = "strMeasure1") val measure1: String?)