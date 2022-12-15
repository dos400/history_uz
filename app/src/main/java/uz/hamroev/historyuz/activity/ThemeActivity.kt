package uz.hamroev.historyuz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import uz.hamroev.historyuz.cache.Cache
import uz.hamroev.historyuz.databinding.ActivityThemeBinding

class ThemeActivity : AppCompatActivity() {
    lateinit var binding: ActivityThemeBinding
    var isClick = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Cache.init(this)

        binding.titleTv.text = "${Cache.themePosition}-Mavzu"
        loadTheme()




    }

    private fun loadTheme() {
        when (Cache.themePosition) {
            1 -> {}
            2 -> {}
            3 -> {}
            4 -> {}
            5 -> {}
            6 -> {}
            7 -> {}
            8 -> {}
            9 -> {}
            10 -> {}
            11 -> {}
            12 -> {}
            13 -> {}
        }
    }

    private fun openWithTwoClick() {
        if (isClick) {
            //main code
           
        }
        isClick = true
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            isClick = false
        }, 700)

    }
}