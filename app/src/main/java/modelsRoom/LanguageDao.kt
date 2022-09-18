package modelsRoom

import androidx.room.*

@Dao
interface LanguageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLanguage(language: LanguageRoom)

    @Query("SELECT * FROM alert WHERE id = :id")
    fun getById(id: Long): LanguageRoom

    @Update
    suspend fun updateLanguage(language: LanguageRoom)
}