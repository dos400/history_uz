package uz.hamroev.historyuz.adapters.mavzu

import android.content.Context
import android.graphics.Typeface
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.hamroev.historyuz.R
import uz.hamroev.historyuz.database.TarixEntity
import uz.hamroev.historyuz.databinding.ItemMavzuBinding

class MavzuAdapter(var context: Context, var list: List<TarixEntity>) : RecyclerView.Adapter<MavzuAdapter.VhMavzu>() {


    var textSize = 12.0f

    var font = 0
    var style = 0
    //color
    var color = context.resources.getColor(R.color.orange_blaze)
    var black = context.resources.getColor(R.color.black)
    var currentPosition = 0
    private  val TAG = "MavzuAdapter"


    inner class VhMavzu(var itemMavzuBinding: ItemMavzuBinding) :
        RecyclerView.ViewHolder(itemMavzuBinding.root) {

        fun onBind(tarixEntity: TarixEntity, position: Int) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                itemMavzuBinding.wordTv.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }



            Log.d(TAG, "onBind: Currentposition = ${currentPosition}")
            Log.d(TAG, "onBind: position = ${position}")

            if (currentPosition == position) {
                itemMavzuBinding.wordTv.setTextColor(color)
                itemMavzuBinding.wordTv.setTypeface(null, Typeface.BOLD)
                itemMavzuBinding.wordTv.textSize = textSize+5.0f

            } else {
                itemMavzuBinding.wordTv.setTextColor(black)
            }

            itemMavzuBinding.wordTv.text = tarixEntity.word
            itemMavzuBinding.wordTv.textSize = textSize

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VhMavzu {
        return VhMavzu(ItemMavzuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VhMavzu, position: Int) {
        return holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size


    interface OnStateListener {
        fun onZoomIn()
        fun onZoomOut()
        fun onChangeFont()
        fun onChangeColor()
    }

}