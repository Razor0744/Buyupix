package team.four.mys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import team.four.mys.databinding.ActivityRecovery1Binding

class Recovery1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRecovery1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecovery1Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}