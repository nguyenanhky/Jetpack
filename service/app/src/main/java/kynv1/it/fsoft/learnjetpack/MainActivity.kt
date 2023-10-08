package kynv1.it.fsoft.learnjetpack

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kynv1.it.fsoft.learnjetpack.fragments.ListMusicFragment

class MainActivity : AppCompatActivity() {
    companion object{
        const val CHANNEL_ID = "1"
        const val ACTION_PAUSE = "ACTION_PAUSE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        createNotificationChannel()
        initViews()
    }

    private fun initViews() {
        val listMusicFragment = ListMusicFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.first_frame,listMusicFragment, ListMusicFragment::class.java.name)
            .addToBackStack(ListMusicFragment::class.java.name)
            .commit()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, "music notification channel", NotificationManager.IMPORTANCE_DEFAULT)
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)
        }
    }
}