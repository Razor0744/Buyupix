package com.example.mys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.mys.databinding.ActivityCreatSubscriptionBinding
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
            val data = hashMapOf(
                "name" to "${binding.name.text}",
                "cost" to "${binding.costEditText.text}",
                "costSpinner" to "${binding.costSpinner.selectedItem}",
                "category" to "${binding.categorySpinner.selectedItem}",
                "date" to  "${binding.name.text}",
                "image" to "${binding.name.text}"
            )
            db.collection(uid()).document("${binding.name.text}")
                .set(data)
                .addOnSuccessListener {
                }
            val intent = Intent(this, SubscriptionsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }
}