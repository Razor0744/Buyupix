package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import models.Alert
import team.four.mys.R

class CustomRecyclerAdapterAlert(
    private val context: Context,
    private val alert: List<Alert>,
    private val alertDay: String,
    private val itemClick: (Alert) -> Unit
) :
    RecyclerView.Adapter<CustomRecyclerAdapterAlert.MyViewHolder>() {

    class MyViewHolder(itemView: View, private val itemClick: (Alert) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val textAlert = itemView.findViewById<TextView>(R.id.textAlert)
        private val imageButton = itemView.findViewById<ImageView>(R.id.imageAlert)

        fun bindSubscription(alert: Alert, context: Context, alertDay: String) {

            textAlert.text = alert.name

            val resourceIdImage =
                context.resources.getIdentifier(
                    "ic_radio_button_click",
                    "drawable",
                    context.packageName
                )
            when (alertDay) {
                "The day before the write-off" -> if (alert.name == "The day before the write-off") {
                    imageButton.setImageResource(resourceIdImage)
                }
                "Two days before cancellation" -> if (alert.name == "Two days before cancellation") {
                    imageButton.setImageResource(resourceIdImage)
                }
                "Three days before cancellation" -> if (alert.name == "Three days before cancellation") {
                    imageButton.setImageResource(resourceIdImage)
                }
                else -> if (alert.name == "The day before the write-off") {
                    imageButton.setImageResource(resourceIdImage)
                }
            }

            itemView.setOnClickListener {
                itemClick(alert)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_alert, parent, false)
        return MyViewHolder(itemView, itemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindSubscription(alert[position], context, alertDay)
    }

    override fun getItemCount() = alert.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.recyclerview_item_alert
    }
}