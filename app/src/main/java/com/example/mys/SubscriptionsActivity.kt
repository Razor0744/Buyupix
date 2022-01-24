package com.example.mys

import Adapters.CustomRecyclerAdapter
import Model.Subscription
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mys.databinding.ActivitySubscriptionsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class SubscriptionsActivity : AppCompatActivity() {

    private lateinit var subscriptions: ArrayList<Subscription>
    private lateinit var adapter: CustomRecyclerAdapter
    private lateinit var db : FirebaseFirestore
    private lateinit var binding: ActivitySubscriptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // all subscriptions
        subscriptions = arrayListOf()
        adapter = CustomRecyclerAdapter(this, subscriptions)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

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

    private fun fireStore() {
        db = FirebaseFirestore.getInstance()
        db.collection(uid())
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        return
                    }

                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            subscriptions.add(dc.document.toObject(Subscription::class.java))
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

            })
    }
}
