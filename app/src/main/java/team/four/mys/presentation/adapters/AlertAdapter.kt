package team.four.mys.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.four.mys.R
import team.four.mys.databinding.RecyclerviewItemAlertBinding
import team.four.mys.domain.models.Alert

class AlertAdapter(
    private val alert: List<Alert>,
    private val alertDay: String?,
    private val itemClick: (Alert) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolderAlert(
        private val binding: RecyclerviewItemAlertBinding,
        private val itemClick: (Alert) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSubscription(alert: Alert, alertDay: String) {

            binding.textAlert.text = alert.name

            when (alertDay) {
                "The day before the write-off" -> if (alert.name == "The day before the write-off") {
                    binding.imageAlert.setImageResource(R.drawable.ic_radio_button_click)
                }

                "Two days before cancellation" -> if (alert.name == "Two days before cancellation") {
                    binding.imageAlert.setImageResource(R.drawable.ic_radio_button_click)
                }

                "Three days before cancellation" -> if (alert.name == "Three days before cancellation") {
                    binding.imageAlert.setImageResource(R.drawable.ic_radio_button_click)
                }

                else -> if (alert.name == "The day before the write-off") {
                    binding.imageAlert.setImageResource(R.drawable.ic_radio_button_click)
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
        (holder as ViewHolderAlert).bindSubscription(alert[position], alertDay ?: "")
    }

    override fun getItemCount() = alert.size
}