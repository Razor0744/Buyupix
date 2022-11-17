package team.four.buyupix.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.four.buyupix.databinding.RecyclerviewItemCountryBinding
import team.four.buyupix.domain.models.Country

class CountryAdapter(
    private val country: List<Country>,
    private val itemClick: (Country) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolderAlert(
        private val binding: RecyclerviewItemCountryBinding,
        private val itemClick: (Country) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSubscription(country: Country) {

            binding.text.text = country.name

            country.icon?.let { binding.icon.setImageResource(it) }

            binding.number.text = country.number

            itemView.setOnClickListener {
                itemClick(country)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            RecyclerviewItemCountryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolderAlert(binding, itemClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderAlert).bindSubscription(country[position])
    }

    override fun getItemCount() = country.size
}