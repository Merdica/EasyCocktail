package de.twosoulsmedia.easycocktails.util.view

import android.graphics.*
import com.squareup.picasso.Transformation

/**
 * Copyright (C) 2018 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class RoundedCornersTransformation(private val radius: Float, private val margin: Int, private val cornerType: CornerType) : Transformation {
    
    enum class CornerType {
        ALL,
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT,
        TOP, BOTTOM, LEFT, RIGHT,
        OTHER_TOP_LEFT, OTHER_TOP_RIGHT, OTHER_BOTTOM_LEFT, OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT, DIAGONAL_FROM_TOP_RIGHT
    }

    private var diameter: Int = (radius*2).toInt()

    override fun transform(source: Bitmap): Bitmap {

        val width = source.width
        val height = source.height

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        drawRoundRect(canvas, paint, width.toFloat(), height.toFloat())
        source.recycle()

        return bitmap
    }

    private fun drawRoundRect(canvas: Canvas, paint: Paint, width: Float, height: Float) {
        val right = width - margin
        val bottom = height - margin

        when (cornerType) {
            CornerType.ALL -> canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), right, bottom), radius, radius, paint)
            CornerType.TOP_LEFT -> drawTopLeftRoundRect(canvas, paint, right, bottom)
            CornerType.TOP_RIGHT -> drawTopRightRoundRect(canvas, paint, right, bottom)
            CornerType.BOTTOM_LEFT -> drawBottomLeftRoundRect(canvas, paint, right, bottom)
            CornerType.BOTTOM_RIGHT -> drawBottomRightRoundRect(canvas, paint, right, bottom)
            CornerType.TOP -> drawTopRoundRect(canvas, paint, right, bottom)
            CornerType.BOTTOM -> drawBottomRoundRect(canvas, paint, right, bottom)
            CornerType.LEFT -> drawLeftRoundRect(canvas, paint, right, bottom)
            CornerType.RIGHT -> drawRightRoundRect(canvas, paint, right, bottom)
            CornerType.OTHER_TOP_LEFT -> drawOtherTopLeftRoundRect(canvas, paint, right, bottom)
            CornerType.OTHER_TOP_RIGHT -> drawOtherTopRightRoundRect(canvas, paint, right, bottom)
            CornerType.OTHER_BOTTOM_LEFT -> drawOtherBottomLeftRoundRect(canvas, paint, right, bottom)
            CornerType.OTHER_BOTTOM_RIGHT -> drawOtherBottomRightRoundRect(canvas, paint, right, bottom)
            CornerType.DIAGONAL_FROM_TOP_LEFT -> drawDiagonalFromTopLeftRoundRect(canvas, paint, right, bottom)
            CornerType.DIAGONAL_FROM_TOP_RIGHT -> drawDiagonalFromTopRightRoundRect(canvas, paint, right, bottom)
        }
    }

    private fun drawTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), (margin + diameter).toFloat(), (margin + diameter).toFloat()),
                radius, radius, paint)
        canvas.drawRect(RectF(margin.toFloat(), (margin + radius), (margin + radius), bottom), paint)
        canvas.drawRect(RectF((margin + radius), margin.toFloat(), right, bottom), paint)
    }

    private fun drawTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(right - diameter, margin.toFloat(), right, (margin + diameter).toFloat()), radius,
                radius, paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom), paint)
        canvas.drawRect(RectF(right - radius, (margin + radius), right, bottom), paint)
    }

    private fun drawBottomLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(margin.toFloat(), bottom - diameter, (margin + diameter).toFloat(), bottom),
                radius, radius, paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), (margin + diameter).toFloat(), bottom - radius), paint)
        canvas.drawRect(RectF((margin + radius), margin.toFloat(), right, bottom), paint)
    }

    private fun drawBottomRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(right - diameter, bottom - diameter, right, bottom), radius,
                radius, paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom), paint)
        canvas.drawRect(RectF(right - radius, margin.toFloat(), right, bottom - radius), paint)
    }

    private fun drawTopRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), right, (margin + diameter).toFloat()), radius, radius,
                paint)
        canvas.drawRect(RectF(margin.toFloat(), (margin + radius), right, bottom), paint)
    }

    private fun drawBottomRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(margin.toFloat(), bottom - diameter, right, bottom), radius, radius,
                paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right, bottom - radius), paint)
    }

    private fun drawLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), (margin + diameter).toFloat(), bottom), radius, radius,
                paint)
        canvas.drawRect(RectF((margin + radius), margin.toFloat(), right, bottom), paint)
    }

    private fun drawRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(right - diameter, margin.toFloat(), right, bottom), radius, radius,
                paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom), paint)
    }

    private fun drawOtherTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(margin.toFloat(), bottom - diameter, right, bottom), radius, radius,
                paint)
        canvas.drawRoundRect(RectF(right - diameter, margin.toFloat(), right, bottom), radius, radius,
                paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom - radius), paint)
    }

    private fun drawOtherTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), (margin + diameter).toFloat(), bottom), radius, radius,
                paint)
        canvas.drawRoundRect(RectF(margin.toFloat(), bottom - diameter, right, bottom), radius, radius,
                paint)
        canvas.drawRect(RectF((margin + radius), margin.toFloat(), right, bottom - radius), paint)
    }

    private fun drawOtherBottomLeftRoundRect(canvas: Canvas, paint: Paint, right: Float, bottom: Float) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), right, (margin + diameter).toFloat()), radius, radius,
                paint)
        canvas.drawRoundRect(RectF(right - diameter, margin.toFloat(), right, bottom), radius, radius,
                paint)
        canvas.drawRect(RectF(margin.toFloat(), (margin + radius), right - radius, bottom), paint)
    }

    private fun drawOtherBottomRightRoundRect(canvas: Canvas, paint: Paint, right: Float,
                                              bottom: Float) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), right, (margin + diameter).toFloat()), radius, radius,
                paint)
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), (margin + diameter).toFloat(), bottom), radius, radius,
                paint)
        canvas.drawRect(RectF((margin + radius), (margin + radius), right, bottom), paint)
    }

    private fun drawDiagonalFromTopLeftRoundRect(canvas: Canvas, paint: Paint, right: Float,
                                                 bottom: Float) {
        canvas.drawRoundRect(RectF(margin.toFloat(), margin.toFloat(), (margin + diameter).toFloat(), (margin + diameter).toFloat()),
                radius, radius, paint)
        canvas.drawRoundRect(RectF(right - diameter, bottom - diameter, right, bottom), radius,
                radius, paint)
        canvas.drawRect(RectF(margin.toFloat(), (margin + radius), right - diameter, bottom), paint)
        canvas.drawRect(RectF((margin + diameter).toFloat(), margin.toFloat(), right, bottom - radius), paint)
    }

    private fun drawDiagonalFromTopRightRoundRect(canvas: Canvas, paint: Paint, right: Float,
                                                  bottom: Float) {
        canvas.drawRoundRect(RectF(right - diameter, margin.toFloat(), right, (margin + diameter).toFloat()), radius,
                radius, paint)
        canvas.drawRoundRect(RectF(margin.toFloat(), bottom - diameter, (margin + diameter).toFloat(), bottom),
                radius, radius, paint)
        canvas.drawRect(RectF(margin.toFloat(), margin.toFloat(), right - radius, bottom - radius), paint)
        canvas.drawRect(RectF((margin + radius), (margin + radius), right, bottom), paint)
    }

    override fun key(): String {
        return ("RoundedTransformation(radius=" + radius + ", margin=" + margin + ", diameter="
                + diameter + ", cornerType=" + cornerType.name + ")")
    }
}