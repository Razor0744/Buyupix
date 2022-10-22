package team.four.mys.presentation

import adapters.CustomRecyclerAdapterAlert
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.R
import team.four.mys.data.db.Preferences
import team.four.mys.domain.models.Alert
import team.four.mys.databinding.ActivityAlertBinding
import team.four.mys.domain.usecases.SetStatusBarUseCase

class AlertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlertBinding

    private lateinit var adapterAlert: CustomRecyclerAdapterAlert

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferences.init(this)
        binding = ActivityAlertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonArrowLeft.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "SettingsFragment")
            startActivity(intent)
        }

        adapter()
        SetStatusBarUseCase().execute(this, this, getColor(R.color.backgroundMain))
    }

    private fun adapter() {
        adapterAlert =
            CustomRecyclerAdapterAlert(this, DataAlert.alert, Preferences.getSettings("Alert")) { alertClick ->
                Preferences.setSettings("Alert", alertClick.name.toString())
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterAlert
    }
}

object DataAlert {
    val alert = arrayListOf(
        Alert("The day before the write-off"),
        Alert("Two days before cancellation"),
        Alert("Three days before cancellation")
    )
}