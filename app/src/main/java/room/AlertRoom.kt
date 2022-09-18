package room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alert")
data class AlertRoom(
    @PrimaryKey val id: Long,
    val name: String
)