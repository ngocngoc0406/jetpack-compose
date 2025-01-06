package com.example.thirtydaysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    val dayNum: Int,
    @StringRes val dayTitleRes: Int,
    @StringRes val dayDescRes: Int,
    @DrawableRes val dayImageRes: Int,
)
