package team.four.mys.presentation.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.R
import team.four.mys.data.repository.AlertData.alert
import team.four.mys.data.repository.SettingsRepositoryImpl
import team.four.mys.data.storage.SettingsPreferences
import team.four.mys.databinding.ActivityAlertBinding
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.SetNavigationBarUseCase
import team.four.mys.domain.usecases.SetSettingsUseCase
import team.four.mys.domain.usecases.SetStatusBarUseCase
import team.four.mys.presentation.adapters.AlertAdapter

class AlertActivity : AppCompatActivity() {

    private val settingsStorage by lazy { SettingsPreferences(context = applicationContext) }
    private val settingsRepository by lazy { SettingsRepositoryImpl(settingsStorage) }
    private val getSettingsUseCase by lazy { GetSettingsUseCase(settingsRepository) }
    private val setSettingsUseCase by lazy { SetSettingsUseCase(settingsRepository) }

    private lateinit var binding: ActivityAlertBinding

    private lateinit var adapterAlert: AlertAdapter

    val CHANNEL_ID = "425"
    val CHANNEL_NAME = "Buyupix"
    val NOTIF_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonArrowLeft.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragment", "SettingsFragment")
            startActivity(intent)
        }

        notification()
        adapter()
        SetStatusBarUseCase().execute(
            SetStatusBarParam(
                this,
                this,
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        SetNavigationBarUseCase().execute(
            SetNavigationBarParam(
                this,
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )
    }

    private fun adapter() {
        adapterAlert =
            AlertAdapter(
                this,
                alert,
                getSettingsUseCase.execute(SettingsPreferencesParam(key = "Alert")).value
            ) { alertClick ->
                setSettingsUseCase.execute(
                    SettingsPreferencesParam(
                        key = "Alert",
                        value = alertClick.name
                    )
                )
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapterAlert
    }

    private fun notification() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            lightColor = Color.BLUE
            enableLights(true)
        }
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        val notify = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Для Юры")
            .setContentText("Если ты это читаешь значет ты лох который не сделал мне правки")
            .setSmallIcon(R.drawable.splash_screen)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle().bigText(""))
            .build()

        val notifyManger = NotificationManagerCompat.from(this)

        binding.good.setOnClickListener { notifyManger.notify(NOTIF_ID, notify) }
    }
}