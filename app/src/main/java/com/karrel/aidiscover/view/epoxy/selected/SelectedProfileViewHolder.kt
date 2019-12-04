package com.karrel.aidiscover.view.epoxy.selected

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.karrel.aidiscover.R
import kotlinx.android.synthetic.main.viewholder_ai_discover_selected_profile.view.*

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class SelectedProfileViewHolder @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr:Int = 0
) :ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.viewholder_ai_discover_selected_profile, this)
    }

    @ModelProp
    fun setImage(@DrawableRes resImg:Int) {
        ivCircle.setImageResource(resImg)
    }
}