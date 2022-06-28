package team.four.mys

import adapters.CustomRecyclerAdapterLanguage
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ather.LocaleHelper
import models.Language
import team.four.mys.DataLanguage.language
import team.four.mys.databinding.ActivityLanguageBinding

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding
    private lateinit var adapterLanguage: CustomRecyclerAdapterLanguage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val locale = intent.getStringExtra("locale").toString()
        adapterLanguage = CustomRecyclerAdapterLanguage(this, language, locale) { language ->
            when (language.name) {
                "USA" -> LocaleHelper().setLocale(this, "en")
                "Russia" -> LocaleHelper().setLocale(this, "ru")
            }
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterLanguage

        binding.buttonArrowLeft.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }
}

object DataLanguage {
    val language = arrayListOf(
        Language("language_usa", "USA"),
        Language("language_russia", "Russia")
    )
}