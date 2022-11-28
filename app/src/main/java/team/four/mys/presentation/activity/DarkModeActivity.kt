package team.four.mys.presentation.activity

import team.four.mys.presentation.adapters.DarkModeAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.R
import team.four.mys.data.db.Preferences
import team.four.mys.data.repository.DarkModeData.darkMode
import team.four.mys.databinding.ActivityDarkModeBinding
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.SetNavigationBarUseCase
import team.four.mys.domain.usecases.SetStatusBarUseCase

class DarkModeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDarkModeBinding

    private lateinit var adapterDarkMode: DarkModeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferences.init(this)
        binding = ActivityDarkModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter()
        SetStatusBarUseCase().execute(
            SetStatusBarParam(
                this,
                this,
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        SetNavigationBarUseCase().execute(
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