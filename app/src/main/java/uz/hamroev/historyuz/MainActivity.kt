package uz.hamroev.historyuz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import uz.hamroev.historyuz.activity.HomeActivity
import uz.hamroev.historyuz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Doston Hamroyev*/
        /*Bismillahir rohmanir rohim Alloh o'zing madadkor bo'l */

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            }
        }, 1500)




    }
}

