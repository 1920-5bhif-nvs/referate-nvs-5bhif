package at.htl.demoapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import at.htl.demoapplication.databinding.DemoItemBinding
import at.htl.demoapplication.domain.Person

class DemoAdapter : RecyclerView.Adapter<DemoViewHolder>() {

    var persons: List<Person> = emptyList()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        val withDataBinding: DemoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DemoViewHolder.LAYOUT,
            parent,
            false
        )
        return DemoViewHolder(withDataBinding)
    }

    override fun getItemCount() = persons.size

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.person = persons[position]
        }
    }

}