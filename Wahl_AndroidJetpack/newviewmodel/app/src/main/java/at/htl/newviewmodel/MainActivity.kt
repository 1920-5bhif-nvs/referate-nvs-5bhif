package at.htl.newviewmodel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.htl.newviewmodel.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //TODO 04 ViewModel mit binding in Activity
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewmodel = viewModel
        //TODO 09 RecyclerView in Activity einbauen
        viewManager = LinearLayoutManager(this)
        viewAdapter = CaseAdapter(viewModel.getDetective().value!!.cases)
        recyclerView = findViewById<RecyclerView>(R.id.cases_view).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        btn_add_cases.setOnClickListener {
            viewModel.addCases()
            //TODO 10 notifyDataSetChanged erklären
            viewAdapter.notifyDataSetChanged()
        }

        Toast.makeText(this,
            "Detective "+viewModel.getDetective().value!!.name.get() +
                    " has solved "+viewModel.getDetective().value!!.cases.count() +" cases!",
            Toast.LENGTH_LONG).show()

    }
}
