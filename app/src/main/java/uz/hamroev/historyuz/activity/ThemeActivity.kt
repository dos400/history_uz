package uz.hamroev.historyuz.activity

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.hamroev.historyuz.R
import uz.hamroev.historyuz.adapters.mavzu.MavzuAdapter
import uz.hamroev.historyuz.cache.Cache
import uz.hamroev.historyuz.database.TarixDatabase
import uz.hamroev.historyuz.databinding.ActivityThemeBinding
import uz.hamroev.historyuz.utils.toast

class ThemeActivity : AppCompatActivity() {
    lateinit var binding: ActivityThemeBinding
    lateinit var mavzuAdapter: MavzuAdapter

    private var mediaPlayer: MediaPlayer? = null
    private val TAG = "ThemeActivity"
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
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: ${e.message}")
            }
            return@setOnLongClickListener true
        }

        //zoom out #### need kattalashtirish audio ###
        binding.zoomOutButton.setOnClickListener {
            if (openWithTwoClick()) {
                mavzuAdapter.textSize -=1
                mavzuAdapter.notifyDataSetChanged()
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
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: ${e.message}")
            }
            return@setOnLongClickListener true
        }

        //zoom in  #### need kichiklashtirdh audio ####
        binding.zoomInButton.setOnClickListener {
            if (openWithTwoClick()) {
                mavzuAdapter.textSize +=1
                mavzuAdapter.notifyDataSetChanged()
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
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: ${e.message}")
            }
            return@setOnLongClickListener true
        }

        //font   need shrift audio ####
//        binding.fontButton.setOnClickListener {
//            if (openWithTwoClick()) {
//
//            }
//        }
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
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: ${e.message}")
            }
            return@setOnLongClickListener true
        }

        binding.fontButton.setOnClickListener {
//            if (mediaPlayer == null) {
//                mediaPlayer = MediaPlayer.create(this, R.raw.mavzu1)
//            }
//            mediaPlayer?.start()

            try {
                stopMediaPlayer()
                mediaPlayer = MediaPlayer.create(this, R.raw.mavzu1)
                mediaPlayer?.start()

                mediaPlayer?.setOnCompletionListener {
                    if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "onCreate: ${e.message}")
            }
            updateSeekBar()

        }



    }

    private fun loadTheme() {
        when (Cache.themePosition) {
            1 -> {
                val listTheme = TarixDatabase.GET.getTarixDatabase().getTarixDao().getThemeById(1)
                mavzuAdapter = MavzuAdapter(this, listTheme)
                binding.rvMavzu.adapter = mavzuAdapter

            }
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

        return click
    }

    private fun stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    private fun updateSeekBar() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    val curPos = mediaPlayer?.currentPosition!!
                    Log.d(TAG, "run: ${curPos}")
                    Log.d(TAG, "run: ${curPos / 1000}")
                    setDataText(curPos)
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {

                }
            }
        }, 1000)
    }

    private fun setDataText(curPos: Int) {
        var currentTime = curPos / 1000

        lifecycleScope.launch(Dispatchers.Main) {
            val listTheme = TarixDatabase.GET.getTarixDatabase().getTarixDao().getThemeById(1)

            for (tarixEntity in listTheme) {
                if (currentTime == tarixEntity.time){
                    toast("${tarixEntity.time}")
                    mavzuAdapter.currentPosition = tarixEntity.id
                    Log.d(TAG, "setDataText: word id = ${tarixEntity.word}")
                    mavzuAdapter.color = resources.getColor(R.color.orange)
                    mavzuAdapter.notifyDataSetChanged()
                    mavzuAdapter = MavzuAdapter(this@ThemeActivity, listTheme)
                    binding.rvMavzu.adapter = mavzuAdapter

                }
            }
        }




    }


}