package team.four.mys.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.data.repository.LanguageData.language
import team.four.mys.data.repository.SettingsRepositoryImpl
import team.four.mys.data.storage.SettingsPreferences
import team.four.mys.databinding.ActivityLanguageBinding
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.SetNavigationBarUseCase
import team.four.mys.presentation.adapters.LanguageAdapter
import team.four.mys.presentation.other.LocaleHelper
import team.four.mys.presentation.viewmodelsactivity.LanguageViewModel

class LanguageActivity : AppCompatActivity() {

    private val settingsStorage by lazy { SettingsPreferences(context = applicationContext) }
    private val settingsRepository by lazy { SettingsRepositoryImpl(settingsStorage) }
    private val getSettingsUseCase by lazy { GetSettingsUseCase(settingsRepository) }

    private lateinit var binding: ActivityLanguageBinding

    private val viewModel by viewModel<LanguageViewModel>()

    private lateinit var adapterLanguage: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonArrowLeft.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "SettingsFragment")
            startActivity(intent)
        }

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
        adapterLanguage =
            LanguageAdapter(
                this,
                language,
                getSettingsUseCase.execute(SettingsPreferencesParam(key = "Locale")).value
            ) { language ->
                when (language.name) {
                    "USA" -> LocaleHelper().setLocale(this, "en")
                    "Russia" -> LocaleHelper().setLocale(this, "ru")
                }
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterLanguage
    }
}