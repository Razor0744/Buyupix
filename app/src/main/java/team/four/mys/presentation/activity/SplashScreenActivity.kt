package team.four.mys.presentation.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.presentation.viewmodelsfragment.SplashScreenViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashScreenViewModel>()

    private var timeFirebase: String = ""
    private var timeSettings: String = ""
    private var checkTime: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.timeFirebase.observe(this) {
            if (it != null) {
                timeFirebase = it
                viewModel.getTimeSettings()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        viewModel.timeSettings.observe(this) {
            if (it == null) {
                timeSettings = it!!
                viewModel.checkTimeSync(timeSettings = timeSettings, timeFirebase = timeFirebase)
            } else {
                startActivityToNext()
            }
        }

        viewModel.checkTime.observe(this) {
            checkTime = it
            startActivityToNext()
        }
    }

    private fun startActivityToNext() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("time", checkTime)
        println(timeFirebase)
        println(timeSettings)
        println(checkTime)
        startActivity(intent)
    }
}