package team.four.mys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import team.four.mys.databinding.ActivitySubscriptionInfoBinding

class SubscriptionInfo : AppCompatActivity() {

    private lateinit var binding : ActivitySubscriptionInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubscriptionInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide
            .with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/my-subscriptions-96306.appspot.com/o/Spotify.png?alt=media&token=89cd172f-201d-4a5e-acc6-e0da3344c26e")
            .into(binding.imageOfSubscription)

    }
}