package at.htl.overalldemo.ui.home

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import at.htl.overalldemo.R

class HomeFragment : Fragment() {

    lateinit var editText: EditText;

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        editText = root.findViewById(R.id.text_home)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //TODO 4.Code im Home-Fragment
        view.findViewById<View>(R.id.button_home).setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDashboardFragment(editText.text.toString())
            NavHostFragment.findNavController(this@HomeFragment)
                    .navigate(action)
        }
    }
}
