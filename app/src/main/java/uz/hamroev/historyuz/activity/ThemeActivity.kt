package uz.hamroev.historyuz.activity

import android.content.Context
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
    private var mMediaPlayer: MediaPlayer? = null
    private val TAG = "ThemeActivity"
    var isClick = false
    var isPlay = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Cache.init(this)

        toast("${Cache.isBlindActive}")

        binding.titleTv.text = "${Cache.themePosition}-Mavzu"
        loadTheme(this)

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
                lifecycleScope.launch(Dispatchers.Main) {
                    mavzuAdapter.textSize -= 1
                    mavzuAdapter.notifyDataSetChanged()
                }
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
                lifecycleScope.launch(Dispatchers.Main) {
                    mavzuAdapter.textSize += 1
                    mavzuAdapter.notifyDataSetChanged()
                }
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
            // updateSeekBar()

        }

        binding.playButton.setOnLongClickListener {
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
        binding.playButton.setOnClickListener {
            if (openWithTwoClick()) {
                if (isPlay) {
                    isPlay = false
                    binding.musicImage.setImageResource(R.drawable.fi_play)
                    pauseSound()
                } else {
                    isPlay = true
                    playSound()
                    binding.musicImage.setImageResource(R.drawable.fi_pause)
                }
            }
        }


    }

    private fun loadTheme(context: Context) {
        lifecycleScope.launch(Dispatchers.Main) {
            val listTheme = TarixDatabase.GET.getTarixDatabase().getTarixDao()
                .getThemeById(Cache.themePosition!!)

            mavzuAdapter = MavzuAdapter(context, listTheme)
            binding.rvMavzu.adapter = mavzuAdapter

        }


    }

    private fun setData(curPos: Int) {
        var currentTime = curPos / 1000

        lifecycleScope.launch(Dispatchers.Main) {
            val listTheme = TarixDatabase.GET.getTarixDatabase().getTarixDao()
                .getThemeById(Cache.themePosition!!)
            for (tarixEntity in listTheme) {
                if (currentTime == tarixEntity.time) {
                    mavzuAdapter.currentPosition = tarixEntity.id - 1
                    mavzuAdapter.color = resources.getColor(R.color.orange_blaze)

                    binding.rvMavzu.scrollToPosition(tarixEntity.id - 1)
                    mavzuAdapter.notifyItemChanged(tarixEntity.id - 1)
                    mavzuAdapter.notifyItemChanged(tarixEntity.id - 2)

                }

            }
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

    /*

    For music

    * */

    fun updateTime() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    val curPos = mMediaPlayer?.currentPosition!!
                    setData(curPos)
                    handler.postDelayed(this, 1000)
                } catch (e: Exception) {

                }
            }
        }, 2000)
    }

    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

    fun playSound() {
        if (mMediaPlayer == null) {
            when (Cache.themePosition) {
                1 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu1)
                }
                2 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu2)
                }
                3 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu3)
                }
                4 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu4)
                }
                5 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu5)
                }
                6 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu6)
                }
                7 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu7)
                }
                8 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu8)
                }
                9 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu9)
                }
                10 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu10)
                }
                11 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu11)
                }
                12 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu12)
                }
                13 -> {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.mavzu13)
                }
            }
            updateTime()
            mMediaPlayer!!.start()

        } else mMediaPlayer!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSound()
        stopMediaPlayer()
    }





}