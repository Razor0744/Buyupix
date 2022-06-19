package team.four.mys

import adapters.CustomRecyclerAdapterLanguage
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import models.Language
import team.four.mys.databinding.ActivityLanguageBinding

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding
    private lateinit var language: ArrayList<Language>
    private lateinit var adapterLanguage: CustomRecyclerAdapterLanguage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        language = arrayListOf(
            Language("language_usa", "Usa"),
            Language("language_russia", "Russia"),
            Language("language_usa", "Belarus")
        )
        adapterLanguage = CustomRecyclerAdapterLanguage(this, language)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterLanguage
    }
}