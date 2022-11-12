package team.four.buyupix.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.four.buyupix.databinding.RecyclerviewItemCurrenciesBinding
import team.four.buyupix.domain.models.Currencies

class CurrenciesAdapter(
    private val currencies: List<Currencies>,
    private val itemClick: (Currencies) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolderAlert(private val binding: RecyclerviewItemCurrenciesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSubscription(currencies: Currencies, itemClick: (Currencies) -> Unit) {

            binding.name.text = currencies.name

            itemView.setOnClickListener {
                itemClick(currencies)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            RecyclerviewItemCurrenciesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolderAlert(binding = binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderAlert).bindSubscription(
            currencies = currencies[position],
            itemClick = itemClick
        )
    }

    override fun getItemCount() = currencies.size
}