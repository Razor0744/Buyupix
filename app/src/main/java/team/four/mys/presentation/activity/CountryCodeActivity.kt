package team.four.mys.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.data.repository.CountryData
import team.four.mys.databinding.ActivityCountryCodeBinding
import team.four.mys.presentation.adapters.CountryAdapter

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