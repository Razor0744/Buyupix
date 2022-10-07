package team.four.mys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.mys.databinding.ActivitySubscriptionInfoBinding

class SubscriptionInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubscriptionInfoBinding

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscriptionInfo()
    }

    private fun subscriptionInfo() {
        db.collection(uid()).document("7")
            .collection("date")
            .document("Spotify")
            .get()
            .addOnSuccessListener { document ->
                Glide
                    .with(this)
                    .load(document.get("image").toString())
                    .into(binding.imageOfSubscription)
                binding.name2.text = document.get("name").toString()
                binding.name.text = document.get("name").toString()
                binding.price2.text  = document.get("priceSpinner").toString() + document.get("price").toString()
                binding.price.text   = document.get("priceSpinner").toString() + document.get("price").toString()
                binding.description.text  = document.get("description").toString()
            }
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }
}