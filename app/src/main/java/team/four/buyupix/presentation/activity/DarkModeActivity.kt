package team.four.buyupix.presentation.activity

import team.four.buyupix.presentation.adapters.DarkModeAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.buyupix.R
import team.four.buyupix.data.db.Preferences
import team.four.buyupix.data.repository.DarkModeData.darkMode
import team.four.buyupix.databinding.ActivityDarkModeBinding
import team.four.buyupix.domain.models.SetNavigationBarParam
import team.four.buyupix.domain.models.SetStatusBarParam
import team.four.buyupix.domain.usecases.SetNavigationBarUseCase
import team.four.buyupix.domain.usecases.SetStatusBarUseCase

class DarkModeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDarkModeBinding

    private lateinit var adapterDarkMode: DarkModeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferences.init(this)
        binding = ActivityDarkModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        adapterDarkMode =
            DarkModeAdapter(
                this,
                darkMode, Preferences.getSettings("DarkMode")
            ) { alertClick ->
                Preferences.setSettings("DarkMode", alertClick.name.toString())
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterDarkMode
    }
}