package room

import androidx.room.*

@Dao
interface AlertDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAlert(alert: AlertRoom)

    @Query("SELECT * FROM alert WHERE id = :id")
    fun getById(id: Long): AlertRoom

    @Update
    suspend fun updateAlert(alert: AlertRoom)

    @Delete
    suspend fun deleteAlert(alert: AlertRoom)
}