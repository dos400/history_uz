package uz.hamroev.historyuz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.historyuz.databinding.ItemThemeBinding
import uz.hamroev.historyuz.models.Theme

class ThemeAdapter(var context: Context, var list: ArrayList<Theme>, var onThemeClickListener: OnThemeClickListener) :
    RecyclerView.Adapter<ThemeAdapter.VhTheme>() {

    inner class VhTheme(var itemThemeBinding: ItemThemeBinding) :
        RecyclerView.ViewHolder(itemThemeBinding.root) {

        fun onBind(theme: Theme, position: Int) {
            itemThemeBinding.themeNameTextView.text = theme.name

            itemThemeBinding.main.setOnClickListener {
                onThemeClickListener.onClick(theme, position)
            }

            itemThemeBinding.main.setOnLongClickListener {
                onThemeClickListener.onLongClick(theme, position)
                return@setOnLongClickListener true
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhTheme {
        return VhTheme(ItemThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhTheme, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnThemeClickListener {
        fun onLongClick(theme: Theme, position: Int)
        fun onClick(theme: Theme, position: Int)
    }
}