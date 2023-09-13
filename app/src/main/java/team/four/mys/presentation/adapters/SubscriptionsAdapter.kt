package team.four.mys.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import team.four.mys.R
import team.four.mys.databinding.RecyclerviewItemSubscriptionsWithDateBinding
import team.four.mys.databinding.RecyclerviewItemSubscriptionsWithoutDateBinding
import team.four.mys.domain.models.Subscription

class SubscriptionsAdapter(
    private val subscriptions: List<Subscription>,
    private val month: String,
    private val itemClick: (Subscription) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolderWithDate(
        private val binding: RecyclerviewItemSubscriptionsWithDateBinding,
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
            binding.writeOffDateSubscription.text = binding.root.context.getString(
                R.string.date_adapter_subscriptions,
                subscription.date.toString(),
                month
            )

            itemView.setOnClickListener {
                itemClick(subscription)
            }
        }
    }

    inner class ViewHolderWithoutDate(
        private val binding: RecyclerviewItemSubscriptionsWithoutDateBinding,
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

            itemView.setOnClickListener {
                itemClick(subscription)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HASWRITEOFFDATE) {
            val binding =
                RecyclerviewItemSubscriptionsWithDateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            ViewHolderWithDate(binding, itemClick)
        } else {
            val binding =
                RecyclerviewItemSubscriptionsWithoutDateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            ViewHolderWithoutDate(binding, itemClick)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HASWRITEOFFDATE) {
            (holder as ViewHolderWithDate).bind(subscriptions[position])
        } else {
            (holder as ViewHolderWithoutDate).bind(subscriptions[position])
        }
    }

    override fun getItemCount() = subscriptions.size

    override fun getItemViewType(position: Int): Int {
        return if (position - 1 != -1) {
            if (subscriptions[position].date != subscriptions[position - 1].date) HASWRITEOFFDATE else NOWRITEOFFDATE
        } else {
            HASWRITEOFFDATE
        }
    }

    companion object Const {
        const val HASWRITEOFFDATE = 0
        const val NOWRITEOFFDATE = 1
    }
}
