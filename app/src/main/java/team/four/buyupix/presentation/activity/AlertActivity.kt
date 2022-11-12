package team.four.buyupix.presentation.activity

import team.four.buyupix.presentation.adapters.AlertAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.buyupix.R
import team.four.buyupix.data.db.Preferences
import team.four.buyupix.data.repository.AlertData.alert
import team.four.buyupix.databinding.ActivityAlertBinding
import team.four.buyupix.domain.models.SetNavigationBarParam
import team.four.buyupix.domain.models.SetStatusBarParam
import team.four.buyupix.domain.usecases.SetNavigationBarUseCase
import team.four.buyupix.domain.usecases.SetStatusBarUseCase

class AlertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlertBinding

    private lateinit var adapterAlert: AlertAdapter

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
        SetStatusBarUseCase().setStatusBar(
            SetStatusBarParam(
                this,
                this,
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        SetNavigationBarUseCase().setNavigationBar(
            SetNavigationBarParam(
                this,
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )
    }

    private fun adapter() {
        adapterAlert =
            AlertAdapter(
                this,
                alert,
                Preferences.getSettings("Alert")
            ) { alertClick ->
                Preferences.setSettings("Alert", alertClick.name.toString())
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterAlert
    }
}