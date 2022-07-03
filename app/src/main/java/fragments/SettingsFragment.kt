package fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import team.four.mys.AlertActivity
import team.four.mys.LanguageActivity
import team.four.mys.databinding.FragmentSettingsBinding
import java.util.*

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null
    private lateinit var db: FirebaseFirestore
    private lateinit var alert: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding?.alert?.setOnClickListener {
            val intent = Intent(context, AlertActivity::class.java)
            intent.putExtra("alert", alert)
            startActivity(intent)
        }

        binding?.switchDarkMode?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        binding?.language?.setOnClickListener {
            val locale: String = Locale.getDefault().language
            val intent = Intent(context, LanguageActivity::class.java)
            intent.putExtra("locale", locale)
            startActivity(intent)
        }

        alert()

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun alert() {
        db = FirebaseFirestore.getInstance()
        db.collection(uid()).document("alert").get()
            .addOnSuccessListener { document ->
                val alertDocument = document.get("alert")
                if (alertDocument == null) {
                    val data = hashMapOf("alert" to "The day before the write-off")
                    db.collection(uid()).document("alert").set(data)
                } else {
                    alert = alertDocument as String
                }
            }
    }
}