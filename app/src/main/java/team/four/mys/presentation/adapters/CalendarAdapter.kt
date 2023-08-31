package team.four.mys.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.four.mys.databinding.RecyclerviewItemCalendarBinding

class CalendarAdapter(
    private val days: List<String>,
    private val itemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(
        private val binding: RecyclerviewItemCalendarBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(days: String, itemClick: (Int) -> Unit) {

            binding.dayOfCalendar.text = days

            binding.root.setOnClickListener { itemClick(absoluteAdapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding =
            RecyclerviewItemCalendarBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(days = days[position], itemClick = itemClick)
    }

    override fun getItemCount() = days.size
}