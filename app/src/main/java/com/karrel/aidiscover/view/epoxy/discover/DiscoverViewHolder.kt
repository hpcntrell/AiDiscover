package com.karrel.aidiscover.view.epoxy.discover

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.karrel.aidiscover.R
import com.karrel.aidiscover.ext.setOnDebouncedClickListener
import kotlinx.android.synthetic.main.viewholder_ai_discover.view.*

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_MATCH_HEIGHT)
class DiscoverViewHolder @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.viewholder_ai_discover, this)
    }

    @ModelProp
    fun setWidth(width: Int) {
        layoutParams.width = width

        requestLayout()
    }

    @ModelProp
    fun setImage(@DrawableRes resImg: Int) {
        ivDiscover.setActualImageResource(resImg)
    }

    @ModelProp
    fun setName(text: String) {
        tvName.text = text
    }

    @ModelProp(ModelProp.Option.DoNotHash)
    fun setOnClickListener(f : () -> Unit) {
        ivDiscover.setOnDebouncedClickListener { f() }
    }
}