package modelsRoom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "language")
data class LanguageRoom(
    @PrimaryKey
    val id: Long,
    val name: String
)
