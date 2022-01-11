package com.example.mys

import Adapters.CustomRecyclerAdapter
import Service.DataService
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mys.databinding.ActivitySubscriptionsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SubscriptionsActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private lateinit var binding: ActivitySubscriptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // all subscriptions
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CustomRecyclerAdapter(this, DataService.subscriptions)

        // Go to other layouts
        binding.plus.setOnClickListener {
            val intent = Intent(this, CreateSubscriptionActivity::class.java)
            startActivity(intent)
        }
        binding.search.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        binding.analytic.setOnClickListener {
            val intent = Intent(this, AnalyticActivity::class.java)
            startActivity(intent)
        }
        binding.profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        fireStore()
    }


    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun makeText(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun fireStore(){
        db.collection(uid())
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    makeText("${document.id} => ${document.data}")
                }
            }
    }
}