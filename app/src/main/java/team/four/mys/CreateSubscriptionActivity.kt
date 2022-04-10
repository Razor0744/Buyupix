package team.four.mys

import fragments.NavigationFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import team.four.mys.databinding.ActivityCreatSubscriptionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateSubscriptionActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var binding: ActivityCreatSubscriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatSubscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCreate.setOnClickListener {
            if (binding.date.text.trim().toString().isEmpty()) {
                val data = hashMapOf(
                    "name" to "${binding.name.text.trim()}",
                    "cost" to "${binding.costEditText.text.trim()}",
                    "costSpinner" to "${binding.costSpinner.selectedItem}",
                    "image" to icons()
                )
                db.collection(uid()).document("NoDate").collection(uid())
                    .document("${binding.name.text}")
                    .set(data)
                    .addOnSuccessListener {
                    }
                val intent = Intent(this, SubscriptionsActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val data = hashMapOf(
                    "name" to "${binding.name.text.trim()}",
                    "cost" to "${binding.costEditText.text.trim()}",
                    "costSpinner" to "${binding.costSpinner.selectedItem}",
                    "date" to "${binding.date.text.trim()}",
                    "image" to icons()
                )
                db.collection(uid()).document("Date").collection(uid())
                    .document("${binding.name.text}")
                    .set(data)
                    .addOnSuccessListener {
                    }
                val intent = Intent(this, SubscriptionsActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

        autoCompleteName()
        number()
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun number() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = NavigationFragment()
        val bundle = Bundle()
        bundle.putInt("i", 3)
        fragment.arguments = bundle
        fragmentTransaction.add(R.id.fragment, fragment).commit()
    }

    private fun autoCompleteName() {
        val name = resources.getStringArray(R.array.autoName)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, name)
        binding.name.setAdapter(adapter)
    }

    //url icons from storage firebase
    private fun icons(): String {
        var url = ""
        when (binding.name.text.toString()) {
            "Spotify" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Spotify.png?alt=media&token=89cd172f-201d-4a5e-acc6-e0da3344c26e"
            }
            "VK Combo" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/VK%20Kombo.png?alt=media&token=edc2d633-0e2b-4610-9fc0-301552bc679b"
            }
            "Netflix" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Netfix.png?alt=media&token=769f754e-3a71-44c1-ac59-86e59c7ef412"
            }
            "Apple Music" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Apple%20Music.png?alt=media&token=c4b5b8d5-4af6-4095-a332-2375daa55b8d"
            }
        }
        return url
    }
}