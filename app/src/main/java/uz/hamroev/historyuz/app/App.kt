package uz.hamroev.historyuz.app

import android.app.Application
import uz.hamroev.historyuz.database.TarixDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        TarixDatabase.init(this)
    }
}