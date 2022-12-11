package uz.hamroev.historyuz.fragment.author

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.hamroev.historyuz.R
import uz.hamroev.historyuz.adapters.AuthorAdapter
import uz.hamroev.historyuz.databinding.FragmentAuthorBinding
import uz.hamroev.historyuz.models.Author

class AuthorFragment : Fragment() {


    lateinit var binding: FragmentAuthorBinding
    lateinit var listAuthor: ArrayList<Author>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorBinding.inflate(layoutInflater)


//        qilish kerak
        binding.backButton

        loadAuthors()
        val authorAdapter = AuthorAdapter(requireContext(), listAuthor)
        binding.rvAuthor.adapter = authorAdapter


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


}