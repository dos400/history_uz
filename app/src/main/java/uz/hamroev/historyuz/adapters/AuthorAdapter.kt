package uz.hamroev.historyuz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.historyuz.databinding.ItemAuthorBinding
import uz.hamroev.historyuz.models.Author

class AuthorAdapter(var context: Context, var list: ArrayList<Author>) :
    RecyclerView.Adapter<AuthorAdapter.VhAuthor>() {

    inner class VhAuthor(var itemAuthorBinding: ItemAuthorBinding) :
        RecyclerView.ViewHolder(itemAuthorBinding.root) {

        fun onBind(author: Author, position: Int) {
            itemAuthorBinding.authorName.text = author.authorName.trim()
            itemAuthorBinding.authorAbout.text = author.authorAbout.trim()
            itemAuthorBinding.authorImage.setImageResource(author.authorImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhAuthor {
        return VhAuthor(
            ItemAuthorBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VhAuthor, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}