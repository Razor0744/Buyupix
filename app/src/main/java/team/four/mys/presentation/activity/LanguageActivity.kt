package team.four.mys.presentation.activity

import team.four.mys.domain.adapters.CustomRecyclerAdapterLanguage
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.domain.ather.LocaleHelper
import team.four.mys.R
import team.four.mys.data.db.Preferences
import team.four.mys.data.repository.LanguageData.language
import team.four.mys.databinding.ActivityLanguageBinding
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.SetStatusBarUseCase

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding

    private lateinit var adapterLanguage: CustomRecyclerAdapterLanguage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferences.init(this)
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
                getColor(R.color.backgroundMain)
            )
        )
    }

    private fun adapter() {
        adapterLanguage =
            CustomRecyclerAdapterLanguage(
                this,
                language,
                Preferences.getSettings("Locale")
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