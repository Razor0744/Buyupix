package team.four.mys

import adapters.CustomRecyclerAdapterAlert
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Alert
import modelsRoom.AlertRoom
import room.AppDatabase
import team.four.mys.databinding.ActivityAlertBinding

class AlertActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlertBinding
    private lateinit var adapterAlert: CustomRecyclerAdapterAlert

    override fun onCreate(savedInstanceState: Bundle?) {
        onLoadDarkMode()
        super.onCreate(savedInstanceState)
        binding = ActivityAlertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonArrowLeft.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "SettingsFragment")
            startActivity(intent)
        }

        adapter()
    }

    private fun adapter() {
        adapterAlert =
            CustomRecyclerAdapterAlert(this, DataAlert.alert, onLoadAlert()) { alertClick ->
                onSaveAlert(alertClick.name.toString())
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterAlert
    }

    private fun onSaveAlert(string: String) {
        val preferences = getSharedPreferences("Alert", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("Alert", string)
        editor.apply()
    }

    private fun onLoadAlert(): String {
        val preferences = getSharedPreferences("Alert", Context.MODE_PRIVATE)
        val alert = preferences.getString("Alert", "The day before the write-off")
        return alert.toString()
    }

    private fun onLoadDarkMode() {
        val preferences = getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
        val darkMode = preferences?.getBoolean("DarkMode", false)
        if (darkMode == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}

object DataAlert {
    val alert = arrayListOf(
        Alert("The day before the write-off"),
        Alert("Two days before cancellation"),
        Alert("Three days before cancellation")
    )
}