package at.htl.demoapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.htl.demoapplication.R
import at.htl.demoapplication.databinding.FragmentDemoBinding
import at.htl.demoapplication.domain.Person
import at.htl.demoapplication.viewmodels.DemoViewModel

class DemoFragment : Fragment() {

    private val viewModel: DemoViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, DemoViewModel.Factory(activity.application))
            .get(DemoViewModel::class.java)
    }

    private var viewModelAdapter: DemoAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.persons.observe(viewLifecycleOwner, Observer<List<Person>> { persons ->
            persons.apply {
                viewModelAdapter?.persons = persons
            }
        })
    }

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

        viewModelAdapter = DemoAdapter()
        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }

}