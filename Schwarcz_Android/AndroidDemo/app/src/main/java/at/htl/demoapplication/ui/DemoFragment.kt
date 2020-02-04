package at.htl.demoapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import at.htl.demoapplication.R
import at.htl.demoapplication.databinding.FragmentDemoBinding
import at.htl.demoapplication.viewmodels.DemoViewModel

class DemoFragment : Fragment() {

    private val viewModel: DemoViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, DemoViewModel.Factory(activity.application))
            .get(DemoViewModel::class.java)
    }

    // TODO Adapter

    // TODO OnActivityCreated

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDemoBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_demo,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        // TODO adapter
        // TODO recyclerview

        return binding.root
    }

}