package kynv1.it.fsoft.learnjetpack

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.RequiresApi
import kynv1.it.fsoft.learnjetpack.fragments.PlayMusicFragment
import kynv1.it.fsoft.learnjetpack.models.Music

class ForegroundService: Service() {
    lateinit var listMusic:ArrayList<Music>
    val handler = Handler()
    var position: Int = 0
    var mediaPlayer = MediaPlayer()
    var binder=LocalBinder()
    var mediaListener: ((positionProgess:Int, curTimeMillis:Int) -> Unit?)? =null
    val runable = Runnable {
        updatePositionSeekbar()
    }
    override fun onCreate() {
        super.onCreate()
    }
    inner class LocalBinder: Binder(){
        val service: ForegroundService
            get() = this@ForegroundService
    }
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let{
            listMusic = intent.getSerializableExtra("listMusic") as ArrayList<Music>
        }
        startForeground()
        return START_NOT_STICKY
    }
    override fun onDestroy() {
        super.onDestroy()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun startForeground(){
        val pendingIntent = Intent(this, MainActivity::class.java).let { it->
            PendingIntent.getActivity(this, 0, it, PendingIntent.FLAG_IMMUTABLE)
        }
        val notification = Notification.Builder(this, MainActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_library_music_24)
            .setContentTitle("foreground service started")
            .build()
        startForeground(1, notification)
    }
    private fun updatePositionSeekbar(){
        if(mediaPlayer.isPlaying){
            val cur:Double = mediaPlayer.currentPosition/(1.0*mediaPlayer.duration)
            mediaListener?.let { it((cur*100).toInt(), mediaPlayer.currentPosition) }
            handler.postDelayed(runable,1000)
        }
    }
    fun start(position:Int):Int{
        Toast.makeText(applicationContext, "play music", Toast.LENGTH_SHORT).show()
        mediaPlayer.stop()
        mediaPlayer.reset()
        this.position = position
        return mediaPlayer.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(applicationContext, Uri.parse("android.resource://${packageName}/${listMusic[position].music}"))
            prepare()
            start()
            updatePositionSeekbar()
        }.duration
    }
    fun play_pause():String{
        mediaPlayer.let {
            return if(it.isPlaying){
                Toast.makeText(applicationContext, "pause music", Toast.LENGTH_SHORT).show()
                handler.removeCallbacks(runable)
                it.pause()
                PlayMusicFragment.ACTION_PLAY
            } else{
                Toast.makeText(applicationContext, "play music", Toast.LENGTH_SHORT).show()
                updatePositionSeekbar()
                it.start()
                PlayMusicFragment.ACTION_PAUSE
            }
        }
    }
    fun previous():Music?{
        Toast.makeText(applicationContext, "previous", Toast.LENGTH_SHORT).show()
        position = if(position>0) position-1 else listMusic.size-1
        start(position)
        return listMusic[position].apply { totalMillisecond = mediaPlayer.duration }
    }
    fun next():Music?{
        Toast.makeText(applicationContext, "next music", Toast.LENGTH_SHORT).show()
        position = if(position<listMusic.size-1) position+1 else 0
        start(position)
        return listMusic[position].apply { totalMillisecond = mediaPlayer.duration }
    }
    fun startAt(millisecond:Int){

    }
    fun setListener(onChange:(curProgess:Int, curMillis:Int)->Unit){
        mediaListener = onChange
    }

}