package uz.hamroev.historyuz.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.historyuz.databinding.ItemFontBinding
import uz.hamroev.historyuz.models.Font

class FontAdapter(
    var context: Context,
    var list: ArrayList<Font>,
    var onFontClickListener: OnFontClickListener
) : RecyclerView.Adapter<FontAdapter.VhFont>() {

    inner class VhFont(var itemFontBinding: ItemFontBinding) :
        RecyclerView.ViewHolder(itemFontBinding.root) {

        fun onBind(font: Font, position: Int) {
            itemFontBinding.title.text = font.fontText
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                itemFontBinding.title.typeface = context.resources.getFont(font.fontResource)
            }

            itemFontBinding.main.setOnClickListener {
                onFontClickListener.onClick(font, position)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhFont {
        return VhFont(ItemFontBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhFont, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnFontClickListener {
        fun onClick(font: Font, position: Int)
    }
}