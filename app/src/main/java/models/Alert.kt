package models

import androidx.room.Entity

@Entity(tableName = "Alert")
data class Alert(
    var name: String? = null
)
