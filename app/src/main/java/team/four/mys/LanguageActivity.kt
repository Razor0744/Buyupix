package team.four.mys

import adapters.CustomRecyclerAdapterLanguage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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

        adapterLanguage = CustomRecyclerAdapterLanguage(this, language) { language ->
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterLanguage
    }
}

object DataLanguage {
    val language = arrayListOf(
        Language("language_usa", "Usa"),
        Language("language_russia", "Russia")
    )
}