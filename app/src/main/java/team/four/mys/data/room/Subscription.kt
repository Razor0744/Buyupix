package team.four.mys.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriptions")
data class Subscription(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    val icon: String,
    val price: String,
    val currency: String,
    val currencyIcon: String,
    val description: String? = null,
    val date: Long,
    val reminder: Boolean,
    val category: String
)