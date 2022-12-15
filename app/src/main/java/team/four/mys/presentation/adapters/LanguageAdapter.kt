package team.four.mys.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.four.mys.domain.models.Language
import team.four.mys.databinding.RecyclerviewItemLanguageBinding

class LanguageAdapter(
    private val context: Context,
    private val language: List<Language>,
    private val locale: String?,
    private val itemClick: (Language) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolderLanguage(
        private val binding: RecyclerviewItemLanguageBinding,
        private val itemClick: (Language) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSubscription(language: Language, context: Context, locale: String) {

            binding.textLanguage.text = language.name

            val resourceIdIcon =
                context.resources.getIdentifier(language.icon, "drawable", context.packageName)
            binding.iconLanguage.setImageResource(resourceIdIcon)

            val resourceIdImage =
                context.resources.getIdentifier(
                    "ic_radio_button_click",
                    "drawable",
                    context.packageName
                )
            when (locale) {
                "ru" -> if (language.name == "Russia") {
                    binding.imageLanguage.setImageResource(resourceIdImage)
                }
                "en" -> if (language.name == "USA") {
                    binding.imageLanguage.setImageResource(resourceIdImage)
                }
                else -> if (language.name == "USA") {
                    binding.imageLanguage.setImageResource(resourceIdImage)
                }
            }

            itemView.setOnClickListener {
                itemClick(language)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            RecyclerviewItemLanguageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolderLanguage(binding, itemClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderLanguage).bindSubscription(language[position], context, locale ?: "")
    }

    override fun getItemCount() = language.size
}