package team.four.mys

import adapters.CustomRecyclerAdapterDarkMode
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.data.Preferences
import team.four.mys.domain.models.DarkMode
import team.four.mys.databinding.ActivityDarkModeBinding

class DarkModeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDarkModeBinding

    private lateinit var adapterDarkMode: CustomRecyclerAdapterDarkMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Preferences.init(this)
        binding = ActivityDarkModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter()
    }

    private fun adapter() {
        adapterDarkMode =
            CustomRecyclerAdapterDarkMode(this, DataDarkMode.darkMode, Preferences.getSettings("DarkMode")) { alertClick ->
                Preferences.setSettings("DarkMode", alertClick.name.toString())
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterDarkMode
    }

//    private fun onLoadDarkModeAdapter(): String {
//        val preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
//        val darkMode = preferences.getString("DarkMode", "System Theme")
//        return darkMode.toString()
//    }

//    private fun onSaveDarkMode(string: String) {
//        val preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
//        val editor = preferences.edit()
//        editor.putString("DarkMode", string)
//        editor.apply()
//    }
}

object DataDarkMode {
    val darkMode = arrayListOf(
        DarkMode("Dark Theme"),
        DarkMode("Light Theme"),
        DarkMode("System Theme")
    )
}