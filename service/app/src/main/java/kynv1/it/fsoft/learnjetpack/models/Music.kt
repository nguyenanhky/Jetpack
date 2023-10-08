package kynv1.it.fsoft.learnjetpack.models

import java.io.Serializable

class Music(
    val musicName:String,
    val singerName:String,
    val imgRs:Int,
    val music:Int
): Serializable {
    var totalMillisecond:Int? = null
    constructor(musicName: String, singerName: String, imgRs: Int, music:Int, totalTime:Int) : this(musicName,singerName,imgRs,music) {
        totalMillisecond = totalTime
    }
}