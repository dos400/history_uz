package uz.hamroev.historyuz.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uz.hamroev.historyuz.R
import uz.hamroev.historyuz.activity.ThemeActivity
import uz.hamroev.historyuz.adapters.NavAdapter
import uz.hamroev.historyuz.adapters.ThemeAdapter
import uz.hamroev.historyuz.databinding.FragmentHomeBinding
import uz.hamroev.historyuz.models.Nav
import uz.hamroev.historyuz.models.Theme
import uz.hamroev.historyuz.utils.toast

class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var listTheme: ArrayList<Theme>
    lateinit var themeAdapter: ThemeAdapter

    private var mediaPlayer: MediaPlayer? = null
    private val TAG = "HomeFragment"
    var isClick = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.menuButton.setOnClickListener {
            binding.drawerLayout.open()
        }

        loadThemeData()
        loadNavData()
        themeAdapter =
            ThemeAdapter(requireContext(), listTheme, object : ThemeAdapter.OnThemeClickListener {
                override fun onLongClick(theme: Theme, position: Int) {
                    try {
                        stopMediaPlayer()
                        mediaPlayer = MediaPlayer.create(requireContext(), theme.musicId)
                        mediaPlayer?.start()

                        mediaPlayer?.setOnCompletionListener {
                            if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                                mediaPlayer?.release()
                                mediaPlayer = null
                            }
                        }
                    } catch (e: Exception) {
                        Log.d(TAG, "onPlay: ${e.message}")
                    }
                }

                override fun onClick(theme: Theme, position: Int) {
                    openWithTwoClick()
                }


            })
        binding.rvTheme.adapter = themeAdapter



        return binding.root
    }

    private fun loadNavData() {
        val listNav = ArrayList<Nav>()
        listNav.clear()
        listNav.add(Nav("Asosiy", R.drawable.ic_home_bold, R.raw.one))
        listNav.add(Nav("Ilova haqida", R.drawable.ic_info_bold, R.raw.one))
        listNav.add(Nav("Mualliflar", R.drawable.ic_users, R.raw.one))
        listNav.add(Nav("Yuborish", R.drawable.ic_share, R.raw.one))
        listNav.add(Nav("Baholash", R.drawable.ic_star, R.raw.one))
        listNav.add(Nav("Chiqish", R.drawable.ic_exit_bold, R.raw.one))
        val navAdapter = NavAdapter(requireContext(), listNav, object : NavAdapter.OnNavClickListener{
            override fun onLongClick(nav: Nav, position: Int) {

            }

            override fun onClick(nav: Nav, position: Int) {

            }

        })
        binding.rvNav.adapter = navAdapter
    }

    private fun openWithTwoClick() {
        if (isClick) {
            startActivity(Intent(requireContext(), ThemeActivity::class.java))
        }
        isClick = true
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            isClick = false
        }, 700)
    }

    private fun loadThemeData() {
        listTheme = ArrayList()
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme1), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme2), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme3), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme4), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme5), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme6), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme7), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme8), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme9), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme10), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme11), R.raw.one))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme12), R.raw.one))

    }


    private fun stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

}