package com.technosudo.gymfollower.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.technosudo.gymfollower.R

sealed class Screen(
    val path: String,
    @DrawableRes val icon: Int? = null,
    @StringRes val description: Int? = null,
    @StringRes val label: Int? = null,
    val tag: String = ""
) {
    object Main : Screen(
        path = "main",
        icon = R.drawable.ic_main,
        description = R.string.bottom_bar_main_description,
        label = R.string.bottom_bar_main,
        tag = "main"
    )

    object Progress : Screen(
        path = "progress",
        icon = R.drawable.ic_progress,
        description = R.string.bottom_bar_progress_description,
        label = R.string.bottom_bar_progress,
        tag = "progress"
    )

    object Settings : Screen(
        path = "settings",
        icon = R.drawable.ic_settings,
        description = R.string.bottom_bar_settings_description,
        label = R.string.bottom_bar_settings,
        tag = "settings"
    )

}