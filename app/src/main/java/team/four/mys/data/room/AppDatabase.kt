package team.four.mys.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import team.four.mys.domain.models.Subscription

@Database(
    version = 2,
    entities = [
        Subscription::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSubscriptionDao(): SubscriptionDao
}