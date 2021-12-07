package com.example.mys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateSubscriptionActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creat_subscription)

        val buttonCreate: Button = findViewById(R.id.button_create)
        buttonCreate.setOnClickListener {
            val name: EditText = findViewById(R.id.name)
            val nameOfSubscription = name.text
            val cost: EditText = findViewById(R.id.cost_EditText)
            val costOfSubscription = cost.text
            val costMon: EditText = findViewById(R.id.cost_mon)
            val costMonOfSubscription = costMon.text
            val data = hashMapOf(
                "name" to "$nameOfSubscription",
                "cost" to "$costOfSubscription",
                "costMon" to "$costMonOfSubscription"
            )
            db.collection(uid()).document("$nameOfSubscription")
                .set(data)
                .addOnSuccessListener {

                }
                .addOnFailureListener {

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