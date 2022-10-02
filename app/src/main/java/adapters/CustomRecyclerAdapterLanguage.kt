package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import models.Language
import team.four.mys.R

class CustomRecyclerAdapterLanguage(
    private val context: Context,
    private val language: List<Language>,
    private val locale: String,
    private val itemClick: (Language) -> Unit
) :
    RecyclerView.Adapter<CustomRecyclerAdapterLanguage.MyViewHolder>() {

    class MyViewHolder(itemView: View, private val itemClick: (Language) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val iconLanguage = itemView.findViewById<ImageView>(R.id.iconLanguage)
        private val textLanguage = itemView.findViewById<TextView>(R.id.textLanguage)
        private val imageButton = itemView.findViewById<ImageView>(R.id.imageLanguage)

        fun bindSubscription(language: Language, context: Context, locale: String) {

            textLanguage.text = language.name

            val resourceIdIcon =
                context.resources.getIdentifier(language.icon, "drawable", context.packageName)
            iconLanguage.setImageResource(resourceIdIcon)

            val resourceIdImage =
                context.resources.getIdentifier(
                    "ic_radio_button_click",
                    "drawable",
                    context.packageName
                )
            when (locale) {
                "ru" -> if (language.name == "Russia") {
                    imageButton.setImageResource(resourceIdImage)
                }
                "en" -> if (language.name == "USA") {
                    imageButton.setImageResource(resourceIdImage)
                }
                else -> if (language.name == "USA") {
                    imageButton.setImageResource(resourceIdImage)
                }
            }

            itemView.setOnClickListener {
                itemClick(language)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_language, parent, false)
        return MyViewHolder(itemView, itemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindSubscription(language[position], context, locale)
    }

    override fun getItemCount() = language.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.recyclerview_item_language
    }
}