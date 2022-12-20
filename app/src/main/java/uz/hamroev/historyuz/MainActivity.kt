package uz.hamroev.historyuz

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.text.LineBreaker
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.accessibility.AccessibilityManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import uz.hamroev.historyuz.activity.HomeActivity
import uz.hamroev.historyuz.cache.Cache
import uz.hamroev.historyuz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Cache.init(this)

        /* Developer - Doston Hamroyev   iAndroid.uz */

        Cache.isBlindActive = this.isScreenReaderOn()

        if (Cache.textFont == 0) {
            Cache.textFont = R.font.main_roboto_regular
        }

        hideSystemBars()
        supportActionBar?.hide()
        startAnimation()
        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                finish()
            }
        }, 1500)


    }


    private fun Activity.hideSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val decorView = window.decorView
            val windowInsetsController = decorView.windowInsetsController ?: return
            windowInsetsController.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsets.Type.systemBars())
        } else {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    fun Context.isScreenReaderOn(): Boolean {
        val am = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        if (am.isEnabled) {
            val serviceInfoList =
                am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN)
            if (!serviceInfoList.isEmpty())
                return true
        }
        return false
    }


    private fun startAnimation() {
        val animTeam = AnimationUtils.loadAnimation(this, R.anim.anim_intro_team)
        val animVersion = AnimationUtils.loadAnimation(this, R.anim.anim_intro_version)
        val animImage = AnimationUtils.loadAnimation(this, R.anim.anim_intro_image)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.infoApp.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        }

        binding.titleAppName.animateText("History Uz")
        binding.titleAppName.setCharacterDeley(20)


        binding.teamTv.startAnimation(animTeam)
        binding.versionTv.startAnimation(animVersion)
        binding.image.startAnimation(animImage)
        binding.infoApp.startAnimation(animImage)


    }


}

