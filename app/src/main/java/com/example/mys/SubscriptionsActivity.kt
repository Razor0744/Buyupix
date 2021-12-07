package com.example.mys

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SubscriptionsActivity : AppCompatActivity() {

    private var image = intArrayOf(R.drawable.img, R.drawable.img_1)

    private val db = Firebase.firestore

    @SuppressLint("UseCompatLoadingForDrawables")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscriptions)

        val names = intent.getStringArrayListExtra("names")
        Toast.makeText(applicationContext, names.toString(), Toast.LENGTH_SHORT).show()

        // all subscriptions
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomRecyclerAdapter(
            getNamesList(),
            getCostList(),
            getImageList()
        )
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(R.drawable.divider_adapter)!!)
        recyclerView.addItemDecoration(itemDecoration)

        // Go to other layouts
        val buttonCreate: ImageButton = findViewById(R.id.plus)
        buttonCreate.setOnClickListener {
            val intent = Intent(this, CreateSubscriptionActivity::class.java)
            startActivity(intent)
        }
        val textViewSearch: TextView = findViewById(R.id.search)
        textViewSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            finish()
        }
        val textViewAnalytic: TextView = findViewById(R.id.analytic)
        textViewAnalytic.setOnClickListener {
            val intent = Intent(this, AnalyticActivity::class.java)
            startActivity(intent)
            finish()
        }
        val textViewProfile: TextView = findViewById(R.id.profile)
        textViewProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
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
        val list: MutableList<String> = ArrayList()
        db.collection(uid())
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        list.add(document.id)
                    }
                    val intent = Intent(this, Data::class.java)
                }
            }
    }
}
//Toast.makeText(applicationContext, list.toString(), Toast.LENGTH_SHORT).show()