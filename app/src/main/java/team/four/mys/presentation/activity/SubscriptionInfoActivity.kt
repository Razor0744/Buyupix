package team.four.mys.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.ActivitySubscriptionInfoBinding
import team.four.mys.domain.models.DeleteSubscriptionParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SubscriptionInfoParam
import team.four.mys.domain.usecases.GetUIDUseCase
import team.four.mys.presentation.viewmodelsactivity.SubscriptionInfoViewModel

class SubscriptionInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySubscriptionInfoBinding

    private val viewModel by viewModel<SubscriptionInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.delete.setOnClickListener {
            viewModel.deleteSubscription(
                DeleteSubscriptionParam(
                    GetUIDUseCase().execute(),
                    intent.getStringExtra("date").toString(),
                    intent.getStringExtra("dateType").toString(),
                    intent.getStringExtra("name").toString()
                )
            )
            startActivity(Intent(this, MainActivity::class.java))
        }

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = this,
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        getSubscriptionInfo()
        setTextFromInfo()
    }

    private fun getSubscriptionInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getSubscriptionInfo(
                SubscriptionInfoParam(
                    GetUIDUseCase().execute(),
                    intent.getStringExtra("date").toString(),
                    intent.getStringExtra("dateType").toString(),
                    intent.getStringExtra("name").toString()
                )
            )
        }
    }

    private fun setTextFromInfo() {
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
    }
}