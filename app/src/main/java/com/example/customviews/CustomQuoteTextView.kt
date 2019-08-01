package com.example.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView

class CustomQuoteTextView(context: Context, attrs: AttributeSet?) : TextView(context, attrs) {

    private val leftBarColor: Int
    private val leftBarWidth: Float
    private val spaceBetween: Float
    private val circleRadius: Float
    private val shapeType: String?

    private val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.QuoteCustomView, 0, 0)
    private val p = Paint()


    init {
        try {
            leftBarColor = typedArray.getColor(R.styleable.QuoteCustomView_leftBarColor, resources.getColor(R.color.colorPrimary))
            leftBarWidth = typedArray.getDimension(R.styleable.QuoteCustomView_leftBarWidth, 10.0f)
            circleRadius = typedArray.getDimension(R.styleable.QuoteCustomView_circleRadius, 5.0f)
            spaceBetween = typedArray.getDimension(R.styleable.QuoteCustomView_spaceBetween, 15.0f)
            shapeType = typedArray.getString(R.styleable.QuoteCustomView_shapeType)

            Log.d("TAGGG", "Circle Radius $circleRadius")
        } finally {
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        p.color = leftBarColor
        if (shapeType.equals("rectangle")) {
            if(leftBarWidth >= spaceBetween) {
                this.setPadding(leftBarWidth.toInt() + spaceBetween.toInt(), this.paddingTop, this.paddingRight, this.paddingBottom)
            } else {
                this.setPadding(spaceBetween.toInt(), this.paddingTop, this.paddingRight, this.paddingBottom)
            }
            canvas?.drawRect(0.0f, 0.0f, leftBarWidth, this.height.toFloat() / this.lineCount, p)
        } else if (shapeType.equals("circle")) {
            if((circleRadius * 2) >= spaceBetween ) {
                this.setPadding((circleRadius * 2).toInt() + spaceBetween.toInt(), this.paddingTop, this.paddingRight, this.paddingBottom)
            } else {
                this.setPadding(spaceBetween.toInt() , this.paddingTop, this.paddingRight, this.paddingBottom)
            }
            canvas?.drawCircle( circleRadius, ((this.height.toFloat() / this.lineCount) / 2),circleRadius, p)
        }

        super.onDraw(canvas)
    }
}