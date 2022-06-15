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
import models.SubscriptionDate
import team.four.mys.R

class CustomRecyclerAdapterLanguage(
    private val context: Context,
    private val subscriptionDates: List<SubscriptionDate>
) :
    RecyclerView.Adapter<CustomRecyclerAdapterLanguage.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconLanguage = itemView.findViewById<ImageView>(R.id.iconLanguage)

        @SuppressLint("SetTextI18n")
        fun bindSubscription(subscriptionDate: SubscriptionDate, context: Context) {

            Glide
                .with(context)
                .load(subscriptionDate.image)
                .into(imageSubscription)

            nameSubscription?.text = subscriptionDate.name

            costSubscription?.text = subscriptionDate.cost + subscriptionDate.costSpinner

            dateSubscription?.text = subscriptionDate.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item_date, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindSubscription(subscriptionDates[position], context)
    }

    override fun getItemCount() = subscriptionDates.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.recyclerview_item_date
    }
}