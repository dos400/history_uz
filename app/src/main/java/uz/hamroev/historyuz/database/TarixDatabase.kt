package uz.hamroev.historyuz.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [TarixEntity::class], version = 1, exportSchema = false)
abstract class TarixDatabase : RoomDatabase() {

    abstract fun getTarixDao(): TarixDao

    companion object {
        @Volatile
        private var database: TarixDatabase? = null

        fun init(context: Context) {
            synchronized(this) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        TarixDatabase::class.java,
                        "tarix.db"
                    )
                        .createFromAsset("tarix.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }
    }

    object GET {
        fun getTarixDatabase(): TarixDatabase = database!!
    }

}