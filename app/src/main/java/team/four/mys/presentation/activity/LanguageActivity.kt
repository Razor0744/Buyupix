package team.four.mys.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.R
import team.four.mys.data.db.Preferences
import team.four.mys.data.repository.LanguageData.language
import team.four.mys.data.repository.SettingsRepositoryImpl
import team.four.mys.data.storage.SettingsPreferences
import team.four.mys.databinding.ActivityLanguageBinding
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.SetNavigationBarUseCase
import team.four.mys.domain.usecases.SetSettingsUseCase
import team.four.mys.domain.usecases.SetStatusBarUseCase
import team.four.mys.presentation.adapters.LanguageAdapter
import team.four.mys.presentation.other.LocaleHelper

class LanguageActivity : AppCompatActivity() {

    private val settingsStorage by lazy { SettingsPreferences(context = applicationContext) }
    private val settingsRepository by lazy { SettingsRepositoryImpl(settingsStorage) }
    private val getSettingsUseCase by lazy { GetSettingsUseCase(settingsRepository) }
    private val setSettingsUseCase by lazy { SetSettingsUseCase(settingsRepository) }

    private lateinit var binding: ActivityLanguageBinding

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