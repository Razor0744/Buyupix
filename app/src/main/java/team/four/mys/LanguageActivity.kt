package team.four.mys

import adapters.CustomRecyclerAdapterLanguage
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import ather.LocaleHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Language
import modelsRoom.LanguageRoom
import room.AppDatabase
import team.four.mys.DataLanguage.language
import team.four.mys.databinding.ActivityLanguageBinding

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding
    private lateinit var adapterLanguage: CustomRecyclerAdapterLanguage

    //Room
    private val databaseLanguage by lazy { AppDatabase.getDatabase(this).languageDao() }
    private val databaseDarkMode by lazy { AppDatabase.getDatabase(this).darkModeDao() }


    override fun onCreate(savedInstanceState: Bundle?) {
        onLoadDarkMode()
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonArrowLeft.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "SettingsFragment")
            startActivity(intent)
        }

        CoroutineScope(Dispatchers.IO).launch {
            adapter()
            println(onLoadLanguage())
        }
    }

    private fun adapter(){
        adapterLanguage =
            CustomRecyclerAdapterLanguage(this, language, onLoadLanguage()) { language ->
                when (language.name) {
                    "USA" -> LocaleHelper().setLocale(this, "en")
                    "Russia" -> LocaleHelper().setLocale(this, "ru")
                }
                CoroutineScope(Dispatchers.IO).launch {
                    onSaveLanguage(language.name.toString())
                }
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterLanguage
    }

    private suspend fun onSaveLanguage(string: String) {
        val language = LanguageRoom(1, string)
        databaseLanguage.updateLanguage(LanguageRoom(1, string))
    }

    private fun onLoadLanguage(): String {
        val language = databaseLanguage.getById(1)
        return language.name
    }

    private fun onLoadDarkMode() {
        val darkMode = databaseDarkMode.getById(1)
        if (darkMode.mode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}

object DataLanguage {
    val language = arrayListOf(
        Language("language_usa", "USA"),
        Language("language_russia", "Russia")
    )
}