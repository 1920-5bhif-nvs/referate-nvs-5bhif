package at.htl.musicservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log

class PlaySongService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        Log.i("MyService", "onCreate")
        super.onCreate()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.paintitblack)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("MyService", "onStartCommand")
        mediaPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i("MyService", "onDestroy")
        mediaPlayer.release()
        super.onDestroy()
    }

    companion object {
        fun startService(context: Context) {
            val startIntent = Intent(context, PlaySongService::class.java)
            context.startService(startIntent)
        }
        fun stopService(context: Context) {
            val stopIntent = Intent(context, PlaySongService::class.java)
            context.stopService(stopIntent)
        }
    }
}
