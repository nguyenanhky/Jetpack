package kynv1.it.fsoft.learnjetpack.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_music.view.img
import kotlinx.android.synthetic.main.item_music.view.tv_music_name
import kotlinx.android.synthetic.main.item_music.view.tv_singer_name
import kynv1.it.fsoft.learnjetpack.R
import kynv1.it.fsoft.learnjetpack.models.Music

class MusicAdapter(val onClick:(music: Music, position:Int)->Unit): RecyclerView.Adapter<MusicAdapter.MusicHolder>() {
    lateinit var listMusic:MutableList<Music>
    inner class MusicHolder(val view: View): RecyclerView.ViewHolder(view){
        fun onBind(music:Music, position: Int){
            view.tv_music_name.text = music.musicName
            view.tv_singer_name.text = music.singerName
            view.img.setImageResource(music.imgRs)
            view.setOnClickListener {
                onClick(music, position)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder {
        return MusicHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false))
    }
    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.onBind(listMusic[position], position)
    }

    override fun getItemCount(): Int {
        return listMusic?.size?:0
    }
    fun setData(listMusic:MutableList<Music>){
        this.listMusic = listMusic
    }

}