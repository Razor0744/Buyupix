package modelsRoom

import androidx.room.*

@Dao
interface DarkModeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDarkMode(darkMode: DarkModeRoom)

    @Query("SELECT * FROM darkMode WHERE id = :id")
    fun getById(id: Long): DarkModeRoom

    @Update
    suspend fun updateDarkMode(darkMode: DarkModeRoom)
}
