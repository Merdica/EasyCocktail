package de.twosoulsmedia.easycocktails

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Drinks(@field:Json(name = "drinks") val id: String)

data class Drink(@field:Json(name = "idDrink") val id: String,
                 @field:Json(name = "strDrink") val name: String)

