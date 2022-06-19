package adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import models.Language
import team.four.mys.R

class CustomRecyclerAdapterLanguage(
    private val context: Context,
    private val language: List<Language>
) :
    RecyclerView.Adapter<CustomRecyclerAdapterLanguage.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconLanguage = itemView.findViewById<ImageView>(R.id.iconLanguage)
        private val textLanguage = itemView.findViewById<TextView>(R.id.textLanguage)
        private val radioButton = itemView.findViewById<RadioButton>(R.id.radioButtonLanguage)

        @SuppressLint("SetTextI18n")
        fun bindSubscription(language: Language, context: Context) {

            textLanguage.text = language.name

            val resourceId =
                context.resources.getIdentifier(language.icon, "drawable", context.packageName)
            iconLanguage.setImageResource(resourceId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_language, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindSubscription(language[position], context)
    }

    override fun getItemCount() = language.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.recyclerview_item_language
    }
}