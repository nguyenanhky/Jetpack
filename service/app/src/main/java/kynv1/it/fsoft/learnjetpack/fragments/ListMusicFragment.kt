package kynv1.it.fsoft.learnjetpack.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_music.rcv_music
import kynv1.it.fsoft.learnjetpack.ForegroundService
import kynv1.it.fsoft.learnjetpack.R
import kynv1.it.fsoft.learnjetpack.adapters.MusicAdapter
import kynv1.it.fsoft.learnjetpack.models.Music

class ListMusicFragment : Fragment() {
    var listMusic:ArrayList<Music> = ArrayList()
    val musicAdapter:MusicAdapter = MusicAdapter { it1, it2 -> onClickMusic(it1, it2) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataAdapter()
        val intent = Intent(context, ForegroundService::class.java)
        //start foreground service
        activity?.startService(Intent(context, ForegroundService::class.java).putExtra("listMusic", listMusic))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_music, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcv_music.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcv_music.adapter = musicAdapter
    }
    private fun initDataAdapter(){
        listMusic = arrayListOf(
            Music("Mama boy", "Amee",R.drawable.mamaboy, R.raw.mamaboy),
            Music("Co chac yeu la day", "Sơn Tùng",R.drawable.cochacyeuladay, R.raw.cochacyeuladay),
            Music("Tháng năm","Soobin Hoàng Sơn", R.drawable.thangnam, R.raw.thangnam),
            Music("Từ thích thích thành thương thương", "Amee",R.drawable.tuthichthichthanhthuongthuong, R.raw.tuthichthichthanhthuongthuong),
            Music("Yếu đuối", "Hoàng Dũng", R.drawable.yeuduoi, R.raw.yeuduoi),
            Music("Mama boy", "Amee",R.drawable.mamaboy, R.raw.mamaboy),
            Music("Muộn rồi mà sao còn", "Sơn Tùng",R.drawable.muonroimasaocon, R.raw.muonroimasaocon),
            Music("Tháng năm","Soobin Hoàng Sơn", R.drawable.thangnam, R.raw.thangnam),
            Music("Từ thích thích thành thương thương", "Amee",R.drawable.tuthichthichthanhthuongthuong, R.raw.tuthichthichthanhthuongthuong),
            Music("Yếu đuối", "Hoàng Dũng", R.drawable.yeuduoi, R.raw.yeuduoi)
        )
        musicAdapter.setData(listMusic)
    }
    private fun onClickMusic(music:Music, position:Int){
        parentFragmentManager.let {
            if(it.backStackEntryCount == 2) it.popBackStack()
            it.beginTransaction()
                ?.replace(R.id.second_frame, PlayMusicFragment.newInstance(music, position), PlayMusicFragment::class.java.name)
                ?.addToBackStack(PlayMusicFragment::class.java.name)
                ?.commit()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        activity?.stopService(Intent(context, ForegroundService::class.java))
    }
}