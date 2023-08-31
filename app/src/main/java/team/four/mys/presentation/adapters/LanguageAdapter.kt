package team.four.mys.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.four.mys.R
import team.four.mys.databinding.RecyclerviewItemLanguageBinding
import team.four.mys.domain.models.Language

class LanguageAdapter(
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

        fun bindSubscription(language: Language, locale: String) {

            binding.textLanguage.text = language.name

            binding.iconLanguage.setImageResource(language.icon)

            when (locale) {
                "ru" -> if (language.name == "Russia") {
                    binding.imageLanguage.setImageResource(R.drawable.ic_radio_button_click)
                }

                "en" -> if (language.name == "USA") {
                    binding.imageLanguage.setImageResource(R.drawable.ic_radio_button_click)
                }

                else -> if (language.name == "USA") {
                    binding.imageLanguage.setImageResource(R.drawable.ic_radio_button_click)
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
        (holder as ViewHolderLanguage).bindSubscription(language[position], locale ?: "")
    }

    override fun getItemCount() = language.size
}