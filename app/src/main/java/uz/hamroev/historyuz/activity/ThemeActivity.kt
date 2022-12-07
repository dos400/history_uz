package uz.hamroev.historyuz.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import uz.hamroev.historyuz.databinding.ActivityThemeBinding

class ThemeActivity : AppCompatActivity() {
    lateinit var binding: ActivityThemeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}