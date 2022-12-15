package uz.hamroev.historyuz.cache

import android.content.Context
import android.content.SharedPreferences

object Cache {

    private const val NAME = "historyuz"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }



    var themePosition: Int?
        get() = sharedPreferences.getInt("themeposition", 0)
        set(value) = sharedPreferences.edit() {
            if (value != null) {
                it.putInt("themeposition", value)
            }
        }


}
