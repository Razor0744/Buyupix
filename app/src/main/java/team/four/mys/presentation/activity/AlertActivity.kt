package team.four.mys.presentation.activity

import team.four.mys.presentation.adapters.CustomRecyclerAdapterAlert
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.R
import team.four.mys.data.db.Preferences
import team.four.mys.data.repository.AlertData.alert
import team.four.mys.databinding.ActivityAlertBinding
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.SetNavigationBarUseCase
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
            CustomRecyclerAdapterAlert(
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