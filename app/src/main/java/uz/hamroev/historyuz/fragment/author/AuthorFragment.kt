package uz.hamroev.historyuz.fragment.author

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import uz.hamroev.historyuz.R
import uz.hamroev.historyuz.adapters.AuthorAdapter
import uz.hamroev.historyuz.databinding.FragmentAuthorBinding
import uz.hamroev.historyuz.models.Author

class AuthorFragment : Fragment() {


    lateinit var binding: FragmentAuthorBinding
    lateinit var listAuthor: ArrayList<Author>

    private var mediaPlayer: MediaPlayer? = null
    private var mediaPlayer2: MediaPlayer? = null
    var isClick = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorBinding.inflate(layoutInflater)



        binding.backButton.setOnClickListener {
            if (isClick) {
                if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
                findNavController().popBackStack()
                stopMediaPlayer()
            }
            isClick = true
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                isClick = false
            }, 700)
        }

        binding.backButton.setOnLongClickListener {
            try {
                stopMediaPlayer()
                mediaPlayer = MediaPlayer.create(requireContext(), R.raw.orqaga)
                mediaPlayer?.start()

                mediaPlayer?.setOnCompletionListener {
                    if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                }
            } catch (e: Exception) {
            }
            return@setOnLongClickListener true
        }

        loadAuthors()
        val authorAdapter = AuthorAdapter(requireContext(), listAuthor)
        binding.rvAuthor.adapter = authorAdapter

        //On Back pressed Dispatcher
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }

            })


        return binding.root
    }

    private fun loadAuthors() {
        listAuthor = ArrayList()
        listAuthor.clear()
        listAuthor.add(Author("Hulkar", "Loyiha rahbari", R.drawable.ic_hulkar_opa))
        listAuthor.add(Author("Hulkar", "Loyiha rahbari", R.drawable.ic_hulkar_opa))
        listAuthor.add(Author("Hulkar", "Loyiha rahbari", R.drawable.ic_hulkar_opa))
        listAuthor.add(Author("Hulkar", "Loyiha rahbari", R.drawable.ic_hulkar_opa))
        listAuthor.add(Author("Hulkar", "Loyiha rahbari", R.drawable.ic_hulkar_opa))
        listAuthor.add(Author("Hulkar", "Loyiha rahbari", R.drawable.ic_hulkar_opa))
        listAuthor.add(Author("Hulkar", "Loyiha rahbari", R.drawable.ic_hulkar_opa))
        listAuthor.add(Author("Hulkar", "Loyiha rahbari", R.drawable.ic_hulkar_opa))
    }

    private fun stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    private fun stopMediaPlayer2() {
        if (mediaPlayer2 != null) {
            mediaPlayer2?.stop()
            mediaPlayer2?.release()
            mediaPlayer2 = null
        }
    }


}