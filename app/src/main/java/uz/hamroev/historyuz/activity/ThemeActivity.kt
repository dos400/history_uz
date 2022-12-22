package uz.hamroev.historyuz.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.hamroev.historyuz.R
import uz.hamroev.historyuz.adapters.FontAdapter
import uz.hamroev.historyuz.adapters.mavzu.MavzuAdapter
import uz.hamroev.historyuz.cache.Cache
import uz.hamroev.historyuz.database.TarixDatabase
import uz.hamroev.historyuz.database.TarixEntity
import uz.hamroev.historyuz.databinding.ActivityThemeBinding
import uz.hamroev.historyuz.databinding.DialogFontBinding
import uz.hamroev.historyuz.models.Font
import uz.hamroev.historyuz.utils.toast

class ThemeActivity : AppCompatActivity() {
    lateinit var binding: ActivityThemeBinding
    lateinit var mavzuAdapter: MavzuAdapter

    private var mediaPlayer: MediaPlayer? = null
    private var mMediaPlayer: MediaPlayer? = null
    private val TAG = "ThemeActivity"
    var isClick = false
    var isPlay = false
    lateinit var listTheme: List<TarixEntity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Cache.init(this)


        binding.titleTv.text = "${Cache.themePosition}-Mavzu"
        loadTheme(this)


        //back
        binding.backButton.setOnClickListener {
            if (Cache.isBlindActive!!) {
                vibrate()
                finish()
            } else {
                if (openWithTwoClick()) {
                    vibrate()
                    finish()
                }
            }

        }
        binding.backButton.setOnLongClickListener {
            vibrate()
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
            if (Cache.isBlindActive!!) {
                vibrate()
                lifecycleScope.launch(Dispatchers.Main) {
                    mavzuAdapter.textSize -= 1.0f
                    Cache.textSize = mavzuAdapter.textSize
                    mavzuAdapter.notifyDataSetChanged()
                }
            } else {
                if (openWithTwoClick()) {
                    vibrate()
                    lifecycleScope.launch(Dispatchers.Main) {
                        mavzuAdapter.textSize -= 1.0f
                        Cache.textSize = mavzuAdapter.textSize
                        mavzuAdapter.notifyDataSetChanged()
                    }
                }
            }

        }
        binding.zoomOutButton.setOnLongClickListener {
            vibrate()
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

            if (Cache.isBlindActive!!) {
                vibrate()
                lifecycleScope.launch(Dispatchers.Main) {
                    mavzuAdapter.textSize += 1.0f
                    Cache.textSize = mavzuAdapter.textSize
                    mavzuAdapter.notifyDataSetChanged()
                }
            } else {

                if (openWithTwoClick()) {
                    vibrate()
                    lifecycleScope.launch(Dispatchers.Main) {
                        mavzuAdapter.textSize += 1.0f
                        Cache.textSize = mavzuAdapter.textSize
                        mavzuAdapter.notifyDataSetChanged()
                    }
                }
            }

        }
        binding.zoomInButton.setOnLongClickListener {
            vibrate()
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


        //font
        binding.fontButton.setOnLongClickListener {
            vibrate()
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
            if (openWithTwoClick()) {
                val listFont = ArrayList<Font>()
                listFont.clear()
                listFont.add(Font("Roboto Regular", R.font.main_roboto_regular))
                listFont.add(Font("Roboto Mono", R.font.roboto_mono))
                listFont.add(Font("Playball", R.font.playball))
                listFont.add(Font("Caveat", R.font.caveat))
                listFont.add(Font("Comfortaa", R.font.comforta))
                listFont.add(Font("Roboto Slab", R.font.roboto_slab))

                val alertDialog = android.app.AlertDialog.Builder(binding.root.context)
                val dialog = alertDialog.create()
                val bindingFont =
                    DialogFontBinding.inflate(LayoutInflater.from(this))
                dialog.setView(bindingFont.root)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCancelable(true)

                bindingFont.backButtonDialog.setOnLongClickListener {
                    vibrate()
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

                bindingFont.backButtonDialog.setOnClickListener {
                    if (Cache.isBlindActive!!) {
                        vibrate()
                        dialog.dismiss()
                    } else {
                        if (openWithTwoClick()) {
                            vibrate()
                            dialog.dismiss()
                        }
                    }
                }

                val fontAdapter =
                    FontAdapter(this, listFont, object : FontAdapter.OnFontClickListener {
                        override fun onClick(font: Font, position: Int) {
                            vibrate()
                            Cache.textFont = font.fontResource
                            Log.d(TAG, "onBind: Fontga ${font.fontText} - ${font.fontResource}")
                            mavzuAdapter.font = Cache.textFont!!
                            mavzuAdapter.list = listTheme
                            mavzuAdapter.notifyDataSetChanged()
                            dialog.dismiss()
                        }
                    })
                bindingFont.rvFont.adapter = fontAdapter

                dialog.show()


            }
        }

        //play
        binding.playButton.setOnLongClickListener {
            vibrate()
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
            if (Cache.isBlindActive!!){
                vibrate()
                if (isPlay) {
                    isPlay = false
                    binding.musicImage.setImageResource(R.drawable.fi_play)
                    pauseSound()
                } else {
                    isPlay = true
                    playSound()
                    binding.musicImage.setImageResource(R.drawable.fi_pause)
                }
            } else {
                if (openWithTwoClick()) {
                    vibrate()
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




    }

    private fun loadTheme(context: Context) {
        lifecycleScope.launch(Dispatchers.Main) {
            listTheme = TarixDatabase.GET.getTarixDatabase().getTarixDao()
                .getThemeById(Cache.themePosition!!)

            mavzuAdapter = MavzuAdapter(context, listTheme, object : MavzuAdapter.OnTextClickListener{
                override fun onClick() {
                    if (Cache.isBlindActive!!){
                        vibrate()
                        if (isPlay) {
                            isPlay = false
                            binding.musicImage.setImageResource(R.drawable.fi_play)
                            pauseSound()
                        } else {
                            isPlay = true
                            playSound()
                            binding.musicImage.setImageResource(R.drawable.fi_pause)
                        }
                    } else {
                        if (openWithTwoClick()) {
                            vibrate()
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
            })
            mavzuAdapter.textSize = Cache.textSize!!
            mavzuAdapter.font = Cache.textFont!!
            binding.rvMavzu.adapter = mavzuAdapter

        }


    }

    private fun setData(curPos: Int) {
        var currentTime = curPos / 1000

        lifecycleScope.launch(Dispatchers.Main) {
            for (i in listTheme.indices) {

                if (currentTime == listTheme[i].time) {

                    mavzuAdapter.currentPosition = i
                    mavzuAdapter.color = resources.getColor(R.color.orange_blaze)

                    binding.rvMavzu.scrollToPosition(i)
                    mavzuAdapter.notifyItemChanged(i)
                    mavzuAdapter.notifyItemChanged(i - 1)

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
        lifecycleScope.launch {
            delay(1000)
            isClick = false
        }
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
        lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                delay(1000)
                val curPos = mMediaPlayer?.currentPosition!!
                setData(curPos)
            }
        }
//        val handler = Handler(Looper.getMainLooper())
//        handler.postDelayed(object : Runnable {
//            override fun run() {
//                try {
//
//                    handler.postDelayed(this, 1000)
//                } catch (e: Exception) {
//
//                }
//            }
//        }, 1000)

    }

    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        isPlay = false
    }

    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) {
            isPlay = false
            mMediaPlayer?.pause()
        }
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

        } else
            mMediaPlayer!!.start()
        isPlay = true
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSound()
        stopMediaPlayer()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
        pauseSound()
        binding.musicImage.setImageResource(R.drawable.fi_play)
        stopMediaPlayer()
    }

    fun vibrate() {
        val vib = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vib.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vib.vibrate(300)
        }
    }


}