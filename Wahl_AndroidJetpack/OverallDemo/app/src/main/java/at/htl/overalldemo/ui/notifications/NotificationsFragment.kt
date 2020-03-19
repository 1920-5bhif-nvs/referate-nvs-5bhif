package at.htl.overalldemo.ui.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import at.htl.overalldemo.R


class NotificationsFragment : Fragment() {
    lateinit var notTitle: EditText
    lateinit var notContent: EditText


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        notTitle = root.findViewById(R.id.not_title)
        notContent = root.findViewById(R.id.not_content)

        var btn: Button = root.findViewById(R.id.button_home)

        btn.setOnClickListener {
            sendNotification()
        }
        return root
    }


    private fun sendNotification() {
        //TODO 7 create Notification channel
        val name = "notchanneldemo"
        val descriptionText = "testing channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("demo_channel", name, importance).apply {
            description = descriptionText
        }
        //TODO 8 create Notification
        val notification = NotificationCompat.Builder(this.context!!, "demo_channel")
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(notTitle.text)
            .setContentText(notContent.text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        //TODO 9 send notification
        val notificationManager = this.context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager!!.createNotificationChannel(channel)
        notificationManager.notify(1, notification)
    }



}
