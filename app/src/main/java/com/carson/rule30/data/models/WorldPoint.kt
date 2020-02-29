package com.carson.rule30.data.models

import com.carson.rule30.ui.WorldView.Companion.DOT_RADIUS

data class WorldPoint(
    var xCoord: Float,
    var yCoord: Float,
    var radius: Float = DOT_RADIUS
)