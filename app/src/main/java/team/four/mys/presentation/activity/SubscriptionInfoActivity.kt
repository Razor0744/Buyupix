package team.four.mys.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.ActivitySubscriptionInfoBinding
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.presentation.viewmodelsactivity.SubscriptionInfoViewModel

class SubscriptionInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubscriptionInfoBinding

    private val viewModel by viewModel<SubscriptionInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonArrowLeft.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = this,
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        viewModel.subscriptionInfo.observe(this){
            binding.name2.text = it.name
            binding.name.text = it.name
            binding.price2.text =
                getString(R.string.priceInfo, it.currencyIcon, it.price)
            binding.price.text =
                getString(R.string.priceInfo, it.currencyIcon, it.price)
            binding.description.text = it.description
            binding.category.text = it.category
            binding.switchReminder.isActivated = it.reminder
        }

        viewModel.getSubscriptionInfo(id = intent.getLongExtra("id", 1))
    }
}