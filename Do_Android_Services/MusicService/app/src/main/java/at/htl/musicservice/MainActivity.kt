package at.htl.musicservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_play.setOnClickListener(View.OnClickListener {
            PlaySongService.startService(this)
            Log.i("MainActivity", "gestartet")
        })
        button_stop.setOnClickListener(View.OnClickListener {
            PlaySongService.stopService(this)
        })
    }
}
