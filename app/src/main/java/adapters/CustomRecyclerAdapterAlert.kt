package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.four.mys.domain.models.Alert
import team.four.mys.databinding.RecyclerviewItemAlertBinding

class CustomRecyclerAdapterAlert(
    private val context: Context,
    private val alert: List<Alert>,
    private val alertDay: String,
    private val itemClick: (Alert) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolderAlert(private val binding: RecyclerviewItemAlertBinding, private val itemClick: (Alert) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSubscription(alert: Alert, context: Context, alertDay: String) {

            binding.textAlert.text = alert.name

            val resourceIdImage =
                context.resources.getIdentifier(
                    "ic_radio_button_click",
                    "drawable",
                    context.packageName
                )
            when (alertDay) {
                "The day before the write-off" -> if (alert.name == "The day before the write-off") {
                    binding.imageAlert.setImageResource(resourceIdImage)
                }
                "Two days before cancellation" -> if (alert.name == "Two days before cancellation") {
                    binding.imageAlert.setImageResource(resourceIdImage)
                }
                "Three days before cancellation" -> if (alert.name == "Three days before cancellation") {
                    binding.imageAlert.setImageResource(resourceIdImage)
                }
                else -> if (alert.name == "The day before the write-off") {
                    binding.imageAlert.setImageResource(resourceIdImage)
                }
            }

            itemView.setOnClickListener {
                itemClick(alert)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            RecyclerviewItemAlertBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolderAlert(binding, itemClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderAlert).bindSubscription(alert[position], context, alertDay)
    }

    override fun getItemCount() = alert.size
}