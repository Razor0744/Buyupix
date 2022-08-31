package adapters

import adapters.Const.HASWRITEOFFDATE
import adapters.Const.NOWRITEOFFDATE
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import models.Subscriptions
import team.four.mys.databinding.RecyclerviewItemSubscriptionsWithDateBinding
import team.four.mys.databinding.RecyclerviewItemSubscriptionsWithoutDateBinding

class CustomRecyclerAdapterSubscriptions(
    private val context: Context,
    private val subscriptions: List<Subscriptions>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolderWithDate(private val binding: RecyclerviewItemSubscriptionsWithDateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(subscriptions: Subscriptions, context: Context) {

            Glide
                .with(context)
                .load(subscriptions.image)
                .into(binding.imageSubscription)

            binding.nameSubscription.text = subscriptions.name
            binding.costSubscription.text = subscriptions.cost
            binding.writeOffDateSubscription.text = subscriptions.writeOffDate
        }
    }

    inner class ViewHolderWithoutDate(private val binding: RecyclerviewItemSubscriptionsWithoutDateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(subscriptions: Subscriptions, context: Context) {

            Glide
                .with(context)
                .load(subscriptions.image)
                .into(binding.imageSubscription)

            binding.nameSubscription.text = subscriptions.name
            binding.costSubscription.text = subscriptions.cost
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HASWRITEOFFDATE) {
            val binding =
                RecyclerviewItemSubscriptionsWithDateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            ViewHolderWithDate(binding)
        } else {
            val binding =
                RecyclerviewItemSubscriptionsWithoutDateBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            ViewHolderWithoutDate(binding)
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
}

private object Const {
    const val HASWRITEOFFDATE = 0
    const val NOWRITEOFFDATE = 1
}