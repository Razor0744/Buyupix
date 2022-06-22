package team.four.mys

import adapters.CustomRecyclerAdapterLanguage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import models.Language
import team.four.mys.DataLanguage.language
import team.four.mys.databinding.ActivityLanguageBinding

class LanguageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLanguageBinding
    private lateinit var adapterLanguage: CustomRecyclerAdapterLanguage
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()

        val locale = intent.getStringExtra("locale").toString()
        adapterLanguage = CustomRecyclerAdapterLanguage(this, language, locale) { language ->
            val data: MutableMap<String, String> = mutableMapOf("language" to locale)
            when (language.name) {
                "USA" -> {
                    data += "language" to "en"
                    db.collection(uid()).document("language").set(data)
                }
                "Russia" -> {
                    data += "language" to "ru"
                    db.collection(uid()).document("language").set(data)
                }
            }
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterLanguage
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }
}

object DataLanguage {
    val language = arrayListOf(
        Language("language_usa", "USA"),
        Language("language_russia", "Russia")
    )
}