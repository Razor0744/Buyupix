package team.four.buyupix.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.buyupix.data.db.Preferences
import team.four.buyupix.data.repository.AlertData
import team.four.buyupix.data.repository.CountryData
import team.four.buyupix.databinding.ActivityCountryCodeBinding
import team.four.buyupix.presentation.adapters.AlertAdapter
import team.four.buyupix.presentation.adapters.CountryAdapter

class CountryCodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter()
    }

    private fun adapter() {
        val adapterAlert =
            CountryAdapter(
                CountryData.country
            ) { alertClick ->
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("icon", alertClick.icon)
                intent.putExtra("name", alertClick.name)
                intent.putExtra("number", alertClick.number)
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterAlert
    }
}