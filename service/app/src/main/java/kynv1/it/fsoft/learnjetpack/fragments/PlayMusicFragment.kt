package kynv1.it.fsoft.learnjetpack.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_play_music.btn_close
import kotlinx.android.synthetic.main.fragment_play_music.btn_next
import kotlinx.android.synthetic.main.fragment_play_music.btn_play_pause
import kotlinx.android.synthetic.main.fragment_play_music.btn_previous
import kotlinx.android.synthetic.main.fragment_play_music.seekbar
import kotlinx.android.synthetic.main.fragment_play_music.tv_current_time
import kotlinx.android.synthetic.main.fragment_play_music.tv_music_name
import kotlinx.android.synthetic.main.fragment_play_music.tv_total_time
import kynv1.it.fsoft.learnjetpack.ForegroundService
import kynv1.it.fsoft.learnjetpack.R
import kynv1.it.fsoft.learnjetpack.models.Music

class PlayMusicFragment : Fragment(), View.OnClickListener {
    lateinit var music: Music
    var mService: ForegroundService? = null
    private var position:Int = 0
    private var bound =false
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            bound = true
            mService = (service as ForegroundService.LocalBinder).service
            mService?.setListener { progess, curMillis->updateSeekBar(progess, curMillis) }
            tv_total_time.text =  millisecondToMinute(mService!!.start(position))
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }
    }
    private fun updateSeekBar(positionProgess:Int, millisecond: Int) {
//        Toast.makeText(context, position.toInt(),Toast.LENGTH_SHORT).show()
        seekbar?.progress = positionProgess
        tv_current_time?.text = millisecondToMinute(millisecond)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            music = it.getSerializable("music") as Music
            position = it.getInt("position")
        }
        //bind service when open screen 2
        activity?.bindService(Intent(context, ForegroundService::class.java), connection, Context.BIND_AUTO_CREATE)
    }
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_play_music, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_music_name.text = music.musicName
        seekbar.max = 100
        setActions()
    }
    private fun setActions() {
        btn_close.setOnClickListener(this)
        btn_play_pause.setOnClickListener(this)
        btn_next.setOnClickListener(this)
        btn_previous.setOnClickListener(this)
    }
    companion object{
        const val ACTION_PLAY = "ACTION_PLAY"
        const val ACTION_PAUSE = "ACTION_PAUSE"
        fun newInstance(music: Music, position:Int) =
            PlayMusicFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("music", music)
                    putInt("position",position)
                }
            }
    }
    override fun onStop() {
        super.onStop()
        if(bound){
            activity?.unbindService(connection)
            bound = false
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }
    private fun registerBroadcastReceiver(){
        //val intentFilter = IntentFilter(ACTION_MUSIC_INFO)
        //val receiver = Receiver(){ music -> updateView(music)}
        //activity?.registerReceiver(receiver, intentFilter)
    }
    private fun updateView(music:Music?, action:String?){
        music?.let {
            tv_music_name.text = music.musicName
            Log.d("time la", it.totalMillisecond.toString())
            it.totalMillisecond?.let { total->tv_total_time.text = millisecondToMinute(total) }
        }
        action?.let {
            when(action){
                ACTION_PLAY->btn_play_pause.setBackgroundResource(R.drawable.ic_play)
                ACTION_PAUSE->btn_play_pause.setBackgroundResource(R.drawable.ic_pause)
            }
        }
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_close ->
                parentFragmentManager.popBackStack()
            R.id.btn_next ->
                mService?.next()?.let {
                    updateView(it, null)
                }
            R.id.btn_previous ->
                mService?.previous()?.let {it
                    updateView(it, null)
                }
            R.id.btn_play_pause ->{
                when(mService?.play_pause()){
                    ACTION_PLAY->updateView(null, ACTION_PLAY)
                    ACTION_PAUSE->updateView(null, ACTION_PAUSE)
                }
            }
        }
    }
    private fun millisecondToMinute(millisecond:Int):String{
        val minute:Int = (millisecond/1000)/60
        val second:Int = (millisecond-minute*60*1000)/1000
        return "$minute:${if(second/10 == 0) ("0$second") else second}"
    }
}