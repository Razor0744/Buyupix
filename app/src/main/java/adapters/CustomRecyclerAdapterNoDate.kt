package adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import models.SubscriptionNoDate
import team.four.mys.R


class CustomRecyclerAdapterNoDate(
    private val context: Context,
    private val subscriptionsNoDate: List<SubscriptionNoDate>
) :
    RecyclerView.Adapter<CustomRecyclerAdapterNoDate.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameSubscription = itemView.findViewById<TextView>(R.id.names)
        private val costSubscription = itemView.findViewById<TextView>(R.id.cost)
        private val imageSubscription = itemView.findViewById<ImageView>(R.id.imageView)

        @SuppressLint("SetTextI18n")
        fun bindSubscription(subscriptionNoDate: SubscriptionNoDate, context: Context) {

            Glide
                .with(context)
                .load(subscriptionNoDate.image)
                .into(imageSubscription)

            nameSubscription?.text = subscriptionNoDate.name

            costSubscription?.text = subscriptionNoDate.cost + subscriptionNoDate.costSpinner
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_no_date, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindSubscription(subscriptionsNoDate[position], context)
    }

    override fun getItemCount() = subscriptionsNoDate.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.recyclerview_item_no_date
    }
}