package modelsRoom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "darkMode")
data class DarkModeRoom(
    @PrimaryKey
    val id: Long,
    val mode: Boolean
)
