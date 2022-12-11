package uz.hamroev.historyuz.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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
        listNav.add(Nav("Asosiy", R.drawable.ic_home_bold, R.raw.orqaga))
        listNav.add(Nav("Ilova haqida", R.drawable.ic_info_bold, R.raw.ilova_haqida))
        listNav.add(Nav("Mualliflar", R.drawable.ic_users, R.raw.mualliflar))
        listNav.add(Nav("Ulashish", R.drawable.ic_share, R.raw.ulashish))
        listNav.add(Nav("Baholash", R.drawable.ic_star, R.raw.boholash))
        listNav.add(Nav("Chiqish", R.drawable.ic_exit_bold, R.raw.chiqish))
        val navAdapter =
            NavAdapter(requireContext(), listNav, object : NavAdapter.OnNavClickListener {
                override fun onLongClick(nav: Nav, position: Int) {
                    try {
                        stopMediaPlayer()
                        mediaPlayer = MediaPlayer.create(requireContext(), nav.navAudio)
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

                override fun onClick(nav: Nav, position: Int) {
                    if (isClick) {
                        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                            mediaPlayer?.release()
                            mediaPlayer = null
                        }
                        // any code here
                        when (position) {
                            0 -> {
                                binding.drawerLayout.closeDrawers()
                            }
                            1 -> {
                                findNavController().navigate(R.id.aboutAppFragment)
                                binding.drawerLayout.closeDrawers()
                            }
                            2 -> {
                                findNavController().navigate(R.id.authorFragment)
                                binding.drawerLayout.closeDrawers()
                            }
                            3 -> {

                                /*share*/
                                try {
                                    val intent = Intent(Intent.ACTION_SEND)
                                    intent.setType("text/plain")
                                    intent.putExtra(
                                        Intent.EXTRA_SUBJECT,
                                        "History Uz"
                                    )
                                    val shareMessage =
                                        "https://play.google.com/store/apps/details?id=${activity?.packageName}"
                                    intent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                                    startActivity(
                                        Intent.createChooser(
                                            intent,
                                            "Yuborish uchun tanlang..."
                                        )
                                    )
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                binding.drawerLayout.closeDrawers()
                            }
                            4 -> {
/*rate*/
                                try {
                                    val uri: Uri =
                                        Uri.parse("market://details?id=${activity?.packageName}")
                                    val intent = Intent(Intent.ACTION_VIEW, uri)
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                } catch (e: ActivityNotFoundException) {
                                    val uri: Uri =
                                        Uri.parse("http://play.google.com/store/apps/details?id=${activity?.packageName}")
                                    val intent = Intent(Intent.ACTION_VIEW, uri)
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    startActivity(intent)
                                }
                                binding.drawerLayout.closeDrawers()
                            }
                            5 -> {
                                activity?.finish()
                            }
                        }
                    }
                    isClick = true
                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        isClick = false
                    }, 700)
                }

            })
        binding.rvNav.adapter = navAdapter
    }

    private fun openWithTwoClick() {
        if (isClick) {
            if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                mediaPlayer?.release()
                mediaPlayer = null
            }
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
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme1), R.raw.theme1))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme2), R.raw.theme2))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme3), R.raw.theme3))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme4), R.raw.theme4))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme5), R.raw.theme5))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme6), R.raw.theme6))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme7), R.raw.theme7))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme8), R.raw.theme8))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme9), R.raw.theme9))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme10), R.raw.theme10))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme11), R.raw.theme11))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme12), R.raw.theme12))
        listTheme.add(Theme(activity?.resources!!.getString(R.string.theme13), R.raw.theme13))

    }


    private fun stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

}