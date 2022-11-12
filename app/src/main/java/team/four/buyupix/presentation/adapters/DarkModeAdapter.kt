package team.four.buyupix.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.four.buyupix.domain.models.DarkMode
import team.four.buyupix.databinding.RecyclerviewItemDarkModeBinding

class DarkModeAdapter(
    private val context: Context,
    private val darkMode: List<DarkMode>,
    private val darkModeTheme: String,
    private val itemClick: (DarkMode) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolderDarkMode(private val binding: RecyclerviewItemDarkModeBinding, private val itemClick: (DarkMode) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSubscription(darkMode: DarkMode, context: Context, darkModeTheme: String) {

            binding.textDarkMode.text = darkMode.name

            val resourceIdImage =
                context.resources.getIdentifier(
                    "ic_radio_button_click",
                    "drawable",
                    context.packageName
                )
            when (darkModeTheme) {
                "System Theme" -> if (darkMode.name == "System Theme") {
                    binding.imageDarkMode.setImageResource(resourceIdImage)
                }
                "Dark Theme" -> if (darkMode.name == "Dark Theme") {
                    binding.imageDarkMode.setImageResource(resourceIdImage)
                }
                "Light Theme" -> if (darkMode.name == "Light Theme") {
                    binding.imageDarkMode.setImageResource(resourceIdImage)
                }
                else -> if (darkMode.name == "System Theme") {
                    binding.imageDarkMode.setImageResource(resourceIdImage)
                }
            }

            itemView.setOnClickListener {
                itemClick(darkMode)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            RecyclerviewItemDarkModeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolderDarkMode(binding, itemClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderDarkMode).bindSubscription(darkMode[position], context, darkModeTheme)
    }

    override fun getItemCount() = darkMode.size
}