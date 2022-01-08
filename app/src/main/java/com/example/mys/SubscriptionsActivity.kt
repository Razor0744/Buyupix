package com.example.mys

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mys.databinding.ActivitySubscriptionsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SubscriptionsActivity : AppCompatActivity() {

    private var image = intArrayOf(R.drawable.img, R.drawable.img_1)

    private val db = Firebase.firestore
    private lateinit var binding: ActivitySubscriptionsBinding

    @SuppressLint("UseCompatLoadingForDrawables")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gg()

        // all subscriptions
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CustomRecyclerAdapter(
            getNamesList(),
            getCostList(),
            getImageList()
        )
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(R.drawable.divider_adapter)!!)
        binding.recyclerView.addItemDecoration(itemDecoration)

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
    }

    private fun getNamesList(): List<String> {
        return this.resources.getStringArray(R.array.cost).toList()
    }

    private fun getCostList(): List<String> {
        return this.resources.getStringArray(R.array.cost).toList()
    }

    private fun getImageList(): List<Int> {
        return image.toList()
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }

    private fun gg() {
        db.collection("subscriptions")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val go = document["name"] as String
                }
            }
        val name = db.collection("subscriptions").whereEqualTo("name", "Spotify")
        Toast.makeText(applicationContext, name.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun makeText(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}