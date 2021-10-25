package com.example.mys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var image = intArrayOf(R.drawable.img, R.drawable.img_1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomRecyclerAdapter(
            getNamesList(),
            getCostList(),
            getImageList()
        )
        var itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(getDrawable(R.drawable.divider_adapter)!!)
        recyclerView.addItemDecoration(itemDecoration)
        val buttonCreate: ImageButton = findViewById(R.id.plus)
        buttonCreate.setOnClickListener{
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
        val textViewSearch: TextView = findViewById(R.id.search)
        textViewSearch.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        val textViewAnalytic: TextView = findViewById(R.id.analytic)
        textViewAnalytic.setOnClickListener{
            val intent = Intent(this, AnalyticActivity::class.java)
            startActivity(intent)
        }
        val textViewProfile: TextView = findViewById(R.id.profile)
        textViewProfile.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getNamesList(): List<String> {
        return this.resources.getStringArray(R.array.names).toList()
    }

    private fun getCostList(): List<String> {
        return this.resources.getStringArray(R.array.cost).toList()
    }

    private fun getImageList(): List<Int> {
        return image.toList()
    }
}