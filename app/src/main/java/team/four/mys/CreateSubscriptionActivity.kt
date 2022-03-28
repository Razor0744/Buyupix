package team.four.mys

import Fragments.NavigationFragment
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
                db.collection(uid()).document("Date").collection(uid())
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
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Spotify.jpg?alt=media&token=e950e3b9-526b-4c81-9d0c-289d34c0f2c9"
            }
            "VK Combo" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/VK%20Combo.jpg?alt=media&token=3b953e5d-3eea-4622-9b70-b041cf8d66c1"
            }
            "Netflix" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Netflix.jpg?alt=media&token=49c9b12b-dca8-43b0-8d55-21dbfaee76ca"
            }
            "Apple Music" -> {
                url =
                    "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Apple%20Music.jpg?alt=media&token=ea4719ee-750e-41e3-8724-cfa8ddace378"
            }
        }
        return url
    }
}