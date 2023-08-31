package team.four.mys.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.ActivityAlertBinding
import team.four.mys.domain.models.Alert
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.presentation.adapters.AlertAdapter
import team.four.mys.presentation.viewmodelsactivity.AlertViewModel

class AlertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlertBinding

    private val viewModel by viewModel<AlertViewModel>()

    private lateinit var adapterAlert: AlertAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonArrowLeft.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "SettingsFragment")
            startActivity(intent)
        }

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = this,
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )
        viewModel.setNavigationColor(
            SetNavigationBarParam(
                activity = this,
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        adapter()
    }

    private fun adapter() {
        adapterAlert =
            AlertAdapter(
                this,
                alert,
                viewModel.getSettings()
            ) { alertClick ->
                viewModel.setSettings(value = alertClick.name)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterAlert
    }

    companion object {
        val alert = listOf(
            Alert("The day before the write-off"),
            Alert("Two days before cancellation"),
            Alert("Three days before cancellation")
        )
    }
}