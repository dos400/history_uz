package uz.hamroev.historyuz.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TarixDao {

    @Query("SELECT * FROM tarix")
    fun getAllWords(): List<TarixEntity>

    @Query("select * from tarix where theme=:idTheme")
    fun getThemeById(idTheme: Int): List<TarixEntity>




}