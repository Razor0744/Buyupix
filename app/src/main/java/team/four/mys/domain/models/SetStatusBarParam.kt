package team.four.mys.domain.models

import android.app.Activity
import android.content.Context

data class SetStatusBarParam(
    val context: Context,
    val activity: Activity,
    val color: Int
)
