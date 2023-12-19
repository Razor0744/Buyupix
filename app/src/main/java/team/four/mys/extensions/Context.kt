package team.four.mys.extensions

import android.content.Context
import team.four.mys.app.App
import team.four.mys.di.AppComponent

fun Context.dpToPx(dp: Int): Float {
    return dp.toFloat() * this.resources.displayMetrics.density
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }
