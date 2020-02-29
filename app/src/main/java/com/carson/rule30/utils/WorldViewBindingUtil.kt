package com.carson.rule30.utils

import androidx.databinding.BindingAdapter
import com.carson.rule30.data.models.WorldPoint
import com.carson.rule30.ui.WorldView

object WorldViewBindingUtil {

    @JvmStatic
    @BindingAdapter("points")
    fun setPoints(worldView: WorldView, points: List<WorldPoint>?) {
        points?.let {
            worldView.setPoints(it)
        }
    }

}