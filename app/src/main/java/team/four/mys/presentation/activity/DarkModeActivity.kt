package team.four.mys.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.data.repository.DarkModeData.darkMode
import team.four.mys.data.repository.SettingsRepositoryImpl
import team.four.mys.data.storage.SettingsPreferences
import team.four.mys.databinding.ActivityDarkModeBinding
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.SetNavigationBarUseCase
import team.four.mys.domain.usecases.SetSettingsUseCase
import team.four.mys.presentation.other.SetStatusBarColor
import team.four.mys.presentation.adapters.DarkModeAdapter
import team.four.mys.presentation.viewmodelsactivity.DarkModeViewModel

class DarkModeActivity : AppCompatActivity() {

    private val settingsStorage by lazy { SettingsPreferences(context = applicationContext) }
    private val settingsRepository by lazy { SettingsRepositoryImpl(settingsStorage) }
    private val getSettingsUseCase by lazy { GetSettingsUseCase(settingsRepository) }
    private val setSettingsUseCase by lazy { SetSettingsUseCase(settingsRepository) }

    private lateinit var binding: ActivityDarkModeBinding

    private val viewModel by viewModel<DarkModeViewModel>()

    private lateinit var adapterDarkMode: DarkModeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDarkModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter()

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = this,
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
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
                darkMode,
                getSettingsUseCase.execute(SettingsPreferencesParam(key = "DarkMode")).value
            ) { darkModeClick ->
                setSettingsUseCase.execute(
                    SettingsPreferencesParam(
                        key = "DarkMode",
                        value = darkModeClick.name
                    )
                )
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterDarkMode
    }
}