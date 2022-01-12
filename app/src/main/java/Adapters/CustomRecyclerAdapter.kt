package Adapters

import Model.Subscription
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mys.R


class CustomRecyclerAdapter(
    private val context: Context,
    private val subscriptions: List<Subscription>
) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameSubscription = itemView.findViewById<TextView>(R.id.names)
        private val costSubscription = itemView.findViewById<TextView>(R.id.cost)
        private val imageSubscription = itemView.findViewById<ImageView>(R.id.imageView)
        private val categorySubscription = itemView.findViewById<TextView>(R.id.category)
        private val dateSubscription = itemView.findViewById<TextView>(R.id.date)

        @SuppressLint("SetTextI18n")
        fun bindSubscription(subscription: Subscription, context: Context){
            val resourceId = context.resources.getIdentifier(subscription.image, "drawable", context.packageName)
            imageSubscription?.setImageResource(resourceId)

            nameSubscription?.text = subscription.name

            costSubscription?.text = subscription.cost + subscription.costSpinner

            categorySubscription?.text = subscription.category

            dateSubscription?.text = subscription.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindSubscription(subscriptions[position],  context)
    }

    override fun getItemCount() = subscriptions.size
}