package adapters

import model.SubscriptionDate
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import team.four.mys.R


class CustomRecyclerAdapterDate(
    private val context: Context,
    private val subscriptionDates: List<SubscriptionDate>
) :
    RecyclerView.Adapter<CustomRecyclerAdapterDate.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameSubscription = itemView.findViewById<TextView>(R.id.names)
        private val costSubscription = itemView.findViewById<TextView>(R.id.cost)
        private val imageSubscription = itemView.findViewById<ImageView>(R.id.imageView)
        private val dateSubscription = itemView.findViewById<TextView>(R.id.date)

        @SuppressLint("SetTextI18n")
        fun bindSubscription(subscriptionDate: SubscriptionDate, context: Context) {

            nameSubscription?.text = subscriptionDate.name

            costSubscription?.text = subscriptionDate.cost + subscriptionDate.costSpinner

            dateSubscription?.text = subscriptionDate.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindSubscription(subscriptionDates[position], context)
    }

    override fun getItemCount() = subscriptionDates.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.recyclerview_item
    }
}