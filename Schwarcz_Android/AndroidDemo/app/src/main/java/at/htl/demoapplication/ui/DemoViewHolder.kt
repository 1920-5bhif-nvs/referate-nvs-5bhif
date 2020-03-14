package at.htl.demoapplication.ui

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import at.htl.demoapplication.R
import at.htl.demoapplication.databinding.DemoItemBinding

class DemoViewHolder(val viewDataBinding: DemoItemBinding)
    : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.demo_item
    }
}