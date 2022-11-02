package team.four.mys.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import team.four.mys.R
import team.four.mys.databinding.RecyclerviewItemSubscriptionsWithDateBinding
import team.four.mys.databinding.RecyclerviewItemSubscriptionsWithoutDateBinding
import team.four.mys.domain.models.Subscriptions

class CustomRecyclerAdapterSubscriptions(
    private val context: Context,
    private val subscriptions: List<Subscriptions>,
    private val month: String,
    private val itemClick: (Subscriptions) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolderWithDate(
        private val binding: RecyclerviewItemSubscriptionsWithDateBinding,
        private val itemClick: (Subscriptions) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(subscriptions: Subscriptions, context: Context) {

            Glide
                .with(context)
                .load(subscriptions.image)
                .into(binding.imageSubscription)

            binding.nameSubscription.text = subscriptions.name
            binding.priceSubscription.text = context.getString(
                R.string.priceAdapterSubscriptions,
                subscriptions.priceSpinner,
                subscriptions.price
            )
            binding.writeOffDateSubscription.text = context.getString(
                R.string.dateAdapterSubscriptions,
                subscriptions.writeOffDate,
                month
            )

            itemView.setOnClickListener {
                itemClick(subscriptions)
            }
        }
    }

    inner class ViewHolderWithoutDate(
        private val binding: RecyclerviewItemSubscriptionsWithoutDateBinding,
        private val itemClick: (Subscriptions) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(subscriptions: Subscriptions, context: Context) {

            Glide
                .with(context)
                .load(subscriptions.image)
                .into(binding.imageSubscription)

            binding.nameSubscription.text = subscriptions.name
            binding.priceSubscription.text = context.getString(
                R.string.priceAdapterSubscriptions,
                subscriptions.priceSpinner,
                subscriptions.price
            )

            itemView.setOnClickListener {
                itemClick(subscriptions)
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
            (holder as ViewHolderWithDate).bind(subscriptions[position], context)
        } else {
            (holder as ViewHolderWithoutDate).bind(subscriptions[position], context)
        }
    }

    override fun getItemCount() = subscriptions.size

    override fun getItemViewType(position: Int): Int {
        return if (subscriptions[position].writeOffDate != null) HASWRITEOFFDATE else NOWRITEOFFDATE
    }

    companion object Const {
        const val HASWRITEOFFDATE = 0
        const val NOWRITEOFFDATE = 1
    }
}
