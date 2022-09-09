package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.four.mys.databinding.RecyclerviewItemCalendarBinding


class CustomRecyclerAdapterCalendar(
    private val days: List<String>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(private val binding: RecyclerviewItemCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(days: String) {

            binding.dayOfCalendar.text = days
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

        (holder as ViewHolder).bind(days[position])

    }

    override fun getItemCount() = days.size
}