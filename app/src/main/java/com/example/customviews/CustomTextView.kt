package com.example.customviews

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.customview.view.*


class CustomTextView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {


    init {
        orientation = HORIZONTAL
        LayoutInflater.from(context).inflate(R.layout.customview, this, true)

        val title: String?
        val leftBarColor: Int
        val leftBarWidth: Int
        val textColor: Int
        val spaceBetween: Float
        val textSize: Float

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0)

        try {
            title           = typedArray.getString(R.styleable.CustomView_text)
            leftBarColor    = typedArray.getColor(R.styleable.CustomView_leftBarColor, resources.getColor(R.color.colorPrimary))
            leftBarWidth    = (typedArray.getDimension(R.styleable.CustomView_leftBarWidth, 10.0f) / resources.displayMetrics.density).toInt()
            textColor       = typedArray.getColor(R.styleable.CustomView_textColor, resources.getColor(R.color.black))
            spaceBetween    = typedArray.getDimension(R.styleable.CustomView_spaceBetween, 10.0f) / resources.displayMetrics.density
            textSize        = typedArray.getDimension(R.styleable.CustomView_textSize, 10.0f) / resources.displayMetrics.density
        } finally {
            typedArray .recycle()
        }


        init(title, leftBarColor, leftBarWidth, textColor,  spaceBetween, textSize)
    }

    private fun init(
        title: String?,
        leftBarColor: Int,
        leftBarWidth: Int,
        textColor: Int,
        spaceBetween: Float,
        textSize: Float
    ) {
          // Setting Text Properties
        text.text = title
        text.gravity = Gravity.START
        text.textSize = textSize
        text.setPadding(spaceBetween.toInt(), text.paddingTop, text.paddingRight, text.paddingBottom)
        text.setTextColor(textColor)


        // Left Bar Properties
        left_color_bar.setBackgroundColor(leftBarColor)
        left_color_bar.layoutParams = LayoutParams(leftBarWidth, text.lineHeight)
    }


}