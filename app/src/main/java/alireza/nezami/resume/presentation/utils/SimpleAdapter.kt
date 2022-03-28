package alireza.nezami.resume.presentation.utils

import alireza.nezami.resume.R
import alireza.nezami.resume.presentation.utils.SimpleAdapter.SimpleViewHolder
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SimpleAdapter(private val dataSource: MutableList<String>) :
    RecyclerView.Adapter<SimpleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view: View =
            TextView(parent.context).apply { setTextColor(parent.context.getColor(R.color.white)) }

        return SimpleViewHolder(view)
    }

    fun add(item: String) {
        dataSource.add(item)
    }

    class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView as TextView
    }

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        holder.textView.text = dataSource[position]
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}