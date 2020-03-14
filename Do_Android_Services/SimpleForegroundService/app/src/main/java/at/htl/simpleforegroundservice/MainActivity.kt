package at.htl.simpleforegroundservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart.setOnClickListener(View.OnClickListener {
            ForegroundService.startService(this, "Foreground Service is running...")
            Toast.makeText(this, "Foreground Service started", Toast.LENGTH_SHORT).show()
        })
        buttonStop.setOnClickListener(View.OnClickListener {
            ForegroundService.stopService(this)
            Toast.makeText(this, "Foreground Service stopped", Toast.LENGTH_SHORT).show()
        })
    }
}
