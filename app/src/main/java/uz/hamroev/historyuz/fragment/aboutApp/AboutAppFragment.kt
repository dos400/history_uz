package uz.hamroev.historyuz.fragment.aboutApp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import uz.hamroev.historyuz.R
import uz.hamroev.historyuz.activity.ThemeActivity
import uz.hamroev.historyuz.cache.Cache
import uz.hamroev.historyuz.databinding.FragmentAboutAppBinding


class AboutAppFragment : Fragment() {


    lateinit var binding: FragmentAboutAppBinding

    private var mediaPlayer: MediaPlayer? = null
    private var mediaPlayer2: MediaPlayer? = null
    var isClick = false
    var isClick2 = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutAppBinding.inflate(layoutInflater)


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

        binding.backButton.setOnClickListener {
            if (Cache.isBlindActive!!) {
                if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
                findNavController().popBackStack()
                stopMediaPlayer2()
            } else {
                if (isClick) {
                    if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                        mediaPlayer?.release()
                        mediaPlayer = null
                    }
                    findNavController().popBackStack()
                    stopMediaPlayer2()
                }
                isClick = true
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    isClick = false
                }, 700)
            }

        }

        binding.ilovaHaqidaFullTv.text = """
            Ilova haqida
            
            ???Oliy ta??lim muassasalarida tahsil olayotgan ko??zi ojiz va ko??rishda nuqsoni bo??lgan talabalar uchun tarix fanidan mobil o??quv ilovasini yaratish??? nomli loyiha oliy ta??lim muassasalari gumanitar yo??nalishidagi talabalarning 1-kursda o??tiladigan ???O??zbekistonning eng yangi tarixi??? majburiy fani doirasida ko??zi ojizlar uchun audiomaterial va zaif ko??ruvchilar o??qishi uchun moslashtirilgan matn ko??rinishidagi mobil ilova hisoblanadi.
             ???O??zbekistonning eng yangi tarixi??? fani doirasida tanlangan darslik asosida yaratilgan ushbu mobil ilova ko??zi ojizlar uchun bo??limlari va paragraflarini aniq ifodalovchi va izlashga qulay bo??lgan funktsiyalariga ega bo??lgan audiomateriallardan tarkib topgan. 
            Zaif ko??ruvchilar uchun esa qulaylik tarzida o??qilayotgan materialning kerakli qatorlari boshqa qatorlariga nisbatan kattalashadi va rangli tarzda ajralib turadi. 
            Har bir bo??limga maxsus tugmacha biriktirilgan va u yerga ovozlashtirilgan audiomaterial joylangan.
        """.trimIndent()

        binding.ilovaHaqidaFullTv.setOnClickListener {
            try {
                stopMediaPlayer2()
                mediaPlayer2 = MediaPlayer.create(requireContext(), R.raw.ilova_haqida_full)
                mediaPlayer2?.start()

                mediaPlayer2?.setOnCompletionListener {
                    if (mediaPlayer2 != null && mediaPlayer2!!.isPlaying) {
                        mediaPlayer2?.release()
                        mediaPlayer2 = null
                    }
                }
            } catch (e: Exception) {
            }
        }

        //On Back pressed Dispatcher
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    stopMediaPlayer2()
                    findNavController().popBackStack()
                }

            })



        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        stopMediaPlayer()
        stopMediaPlayer2()
    }

    override fun onStop() {
        super.onStop()
        stopMediaPlayer()
        stopMediaPlayer2()

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