package com.karrel.aidiscover.view.epoxy.ai_recommend

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

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class AiRecommenedViewHolder @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.viewholder_ai_recommend, this)
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