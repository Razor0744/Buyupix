package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import models.Subscriptions
import team.four.mys.R

class CustomRecyclerAdapterSubscriptions(
    private val context: Context,
    private val subscriptions: List<Subscriptions>
) :
    RecyclerView.Adapter<CustomRecyclerAdapterSubscriptions.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.nameSubscription)
        private val image = itemView.findViewById<ImageView>(R.id.imageSubscription)
        private val cost = itemView.findViewById<TextView>(R.id.costSubscription)
        private val writeOffDate = itemView.findViewById<TextView>(R.id.writeOffDateSubscription)

        fun bindSubscription(subscriptions: Subscriptions, context: Context) {

            Glide
                .with(context)
                .load(subscriptions.image)
                .into(image)

            name.text = subscriptions.name
            cost.text = subscriptions.cost
            writeOffDate.text = subscriptions.writeOffDate

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_subscriptions, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindSubscription(subscriptions[position], context)
    }

    override fun getItemCount() = subscriptions.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.recyclerview_item_subscriptions
    }
}