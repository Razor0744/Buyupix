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
            var url = ""
            when(binding.name.text.toString()){
                "Spotify" -> {url = "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Spotify.jpg?alt=media&token=e950e3b9-526b-4c81-9d0c-289d34c0f2c9"}
                "VK Combo" -> {url = "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/VK%20Combo.svg?alt=media&token=3915139a-f0f3-4294-9f77-d2cf977f05db"}
                "Netflix" -> {url = "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Vector.jpg?alt=media&token=28126d71-fa77-4d18-91f2-89e6163647bc"}
                "Apple Music" -> {url = "https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Apple%20Music.svg?alt=media&token=9473b8c5-bc2c-4505-95a2-9305e48ac915"}
            }
            val data = hashMapOf(
                "name" to "${binding.name.text}",
                "cost" to "${binding.costEditText.text}",
                "costSpinner" to "${binding.costSpinner.selectedItem}",
                "date" to  "${binding.name.text}",
                "image" to url
            )
            db.collection(uid()).document("${binding.name.text}")
                .set(data)
                .addOnSuccessListener {
                }
            val intent = Intent(this, SubscriptionsActivity::class.java)
            startActivity(intent)
            finish()
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

    private fun number(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = NavigationFragment()
        val bundle = Bundle()
        bundle.putInt("i", 3)
        fragment.arguments = bundle
        fragmentTransaction.add(R.id.fragment, fragment).commit()
    }

    private fun autoCompleteName(){
        val name = resources.getStringArray(R.array.autoName)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, name)
        binding.name.setAdapter(adapter)
    }
}