package at.htl.overalldemo.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import at.htl.overalldemo.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private val args: DashboardFragmentArgs by navArgs()
    lateinit var editText: EditText
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        editText = root.findViewById(R.id.text_dashboard)

        //TODO 5. navArgs deconstruction
        editText.setText(args.shareMsg)

        val btn: Button = root.findViewById(R.id.shareButton)

        btn.setOnClickListener {
            shareText()
        }
        return root
    }

    private fun shareText() {
       //TODO 6 share Text
        val send: Intent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,editText.text.toString())
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(send,"NVS")
        startActivity(send)
    }
}
