package de.twosoulsmedia.easycocktails

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.squareup.picasso.Picasso
import de.twosoulsmedia.easycocktails.util.view.RoundedCornersTransformation

class CustomImageView(context: Context, attrs: AttributeSet): AppCompatImageView(context, attrs) {
    fun setImageURL(url: String){
        Picasso.get().load(url).transform(RoundedCornersTransformation(50f, 5, RoundedCornersTransformation.CornerType.ALL)).into(this)
    }
}