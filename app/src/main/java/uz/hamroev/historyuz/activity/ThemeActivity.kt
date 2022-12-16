package uz.hamroev.historyuz.activity

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import uz.hamroev.historyuz.R
import uz.hamroev.historyuz.cache.Cache
import uz.hamroev.historyuz.databinding.ActivityThemeBinding

class ThemeActivity : AppCompatActivity() {
    lateinit var binding: ActivityThemeBinding

    private var mediaPlayer: MediaPlayer? = null
    private  val TAG = "ThemeActivity"
    var isClick = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Cache.init(this)

        binding.titleTv.text = "${Cache.themePosition}-Mavzu"
        loadTheme()

        //back
        binding.backButton.setOnClickListener {
            if (openWithTwoClick()) {
                finish()
            }
        }
        binding.backButton.setOnLongClickListener {
            try {
                stopMediaPlayer()
                mediaPlayer = MediaPlayer.create(this, R.raw.orqaga)
                mediaPlayer?.start()

                mediaPlayer?.setOnCompletionListener {
                    if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                }
            }catch (e:Exception){
                Log.d(TAG, "onCreate: ${e.message}")
            }
            return@setOnLongClickListener true
        }

        //zoom out #### need kattalashtirish audio ###
        binding.zoomOutButton.setOnClickListener {
            if (openWithTwoClick()) {
                finish()
            }
        }
        binding.zoomOutButton.setOnLongClickListener {
            try {
                stopMediaPlayer()
                mediaPlayer = MediaPlayer.create(this, R.raw.orqaga)
                mediaPlayer?.start()

                mediaPlayer?.setOnCompletionListener {
                    if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                }
            }catch (e:Exception){
                Log.d(TAG, "onCreate: ${e.message}")
            }
            return@setOnLongClickListener true
        }

        //zoom in  #### need kichiklashtirdh audio ####
        binding.zoomInButton.setOnClickListener {
            if (openWithTwoClick()) {
                finish()
            }
        }
        binding.zoomInButton.setOnLongClickListener {
            try {
                stopMediaPlayer()
                mediaPlayer = MediaPlayer.create(this, R.raw.orqaga)
                mediaPlayer?.start()

                mediaPlayer?.setOnCompletionListener {
                    if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                }
            }catch (e:Exception){
                Log.d(TAG, "onCreate: ${e.message}")
            }
            return@setOnLongClickListener true
        }

        //font   need shrift audio ####
        binding.fontButton.setOnClickListener {
            if (openWithTwoClick()) {
                finish()
            }
        }
        binding.fontButton.setOnLongClickListener {
            try {
                stopMediaPlayer()
                mediaPlayer = MediaPlayer.create(this, R.raw.orqaga)
                mediaPlayer?.start()

                mediaPlayer?.setOnCompletionListener {
                    if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                }
            }catch (e:Exception){
                Log.d(TAG, "onCreate: ${e.message}")
            }
            return@setOnLongClickListener true
        }








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

    private fun openWithTwoClick(): Boolean {
        var click = false
        if (isClick) {
            //main code
            click = true
        }
        isClick = true
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            isClick = false
        }, 700)

        return true
    }

    private fun stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}