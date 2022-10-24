package team.four.mys.presentation.activity

import team.four.mys.presentation.adapters.CustomRecyclerAdapterDarkMode
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.R
import team.four.mys.data.db.Preferences
import team.four.mys.data.repository.DarkModeData.darkMode
import team.four.mys.databinding.ActivityDarkModeBinding
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.SetStatusBarUseCase

class DarkModeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDarkModeBinding

    private lateinit var adapterDarkMode: CustomRecyclerAdapterDarkMode

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
                getColor(R.color.backgroundMain)
            )
        )
    }

    private fun adapter() {
        adapterDarkMode =
            CustomRecyclerAdapterDarkMode(
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