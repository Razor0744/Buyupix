package team.four.mys.data.repository

import team.four.mys.domain.models.Alert

object AlertData {
    val alert = arrayListOf(
        Alert("The day before the write-off"),
        Alert("Two days before cancellation"),
        Alert("Three days before cancellation")
    )
}