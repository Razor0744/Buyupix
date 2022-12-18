package team.four.mys.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import team.four.mys.R
import team.four.mys.databinding.ActivitySubscriptionInfoBinding
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.Subscription
import team.four.mys.domain.usecases.GetUIDUseCase
import team.four.mys.domain.usecases.SetStatusBarUseCase
import team.four.mys.presentation.viewmodelsactivity.SubscriptionInfoViewModel

class SubscriptionInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubscriptionInfoBinding

    private val viewModel by viewModels<SubscriptionInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.delete.setOnClickListener {
            viewModel.deleteSubscription(
                Subscription(
                    GetUIDUseCase().execute(),
                    intent.getStringExtra("date").toString(),
                    intent.getStringExtra("dateType").toString(),
                    intent.getStringExtra("name").toString()
                )
            )
            startActivity(Intent(this, MainActivity::class.java))
        }

        viewModel.documentLiveData.observe(this) {
            println("nice")
            Glide
                .with(this)
                .load(it.get("image").toString())
                .into(binding.imageOfSubscription)
            binding.name2.text = it.get("name").toString()
            binding.name.text = it.get("name").toString()
            binding.price2.text =
                getString(R.string.priceInfo, it.get("priceSpinner"), it.get("price"))
            binding.price.text =
                getString(R.string.priceInfo, it.get("priceSpinner"), it.get("price"))
            binding.description.text = it.get("description").toString()
        }

        viewModel.subscriptionInfo(
            Subscription(
                GetUIDUseCase().execute(),
                intent.getStringExtra("date").toString(),
                intent.getStringExtra("dateType").toString(),
                intent.getStringExtra("name").toString()
            )
        )

        SetStatusBarUseCase(context = applicationContext).execute(
            SetStatusBarParam(
                this,
                this,
                getColor(R.color.backgroundMain)
            )
        )
    }
}