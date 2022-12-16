package uz.hamroev.historyuz.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tarix")
class TarixEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var time: Int = 0
    var word: String = ""
    var theme: Int = 0


}
