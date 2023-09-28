package team.four.mys.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import team.four.mys.R
import team.four.mys.data.room.Subscription
import team.four.mys.databinding.RecyclerviewItemSubscriptionsBinding

class SubscriptionsAdapter(
    private val subscriptions: List<Subscription>,
    private val month: String,
    private val itemClick: (Subscription) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class ViewHolderSubscription(
        private val binding: RecyclerviewItemSubscriptionsBinding,
        private val itemClick: (Subscription) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(subscription: Subscription) {

            Glide
                .with(binding.root)
                .load(subscription.icon)
                .into(binding.imageSubscription)

            binding.nameSubscription.text = subscription.name

            binding.priceSubscription.text = binding.root.context.getString(
                R.string.price_adapter_subscriptions,
                subscription.currencyIcon,
                subscription.price
            )

            binding.dateSubscription.text = binding.root.context.getString(
                R.string.date_adapter_subscriptions,
                subscription.date.toString(),
                month
            )

            itemView.setOnClickListener {
                itemClick(subscription)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            RecyclerviewItemSubscriptionsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolderSubscription(binding, itemClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderSubscription).bind(subscriptions[position])
    }

    override fun getItemCount() = subscriptions.size
}
