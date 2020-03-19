package at.htl.newviewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import at.htl.newviewmodel.model.Case


class CaseAdapter(cases: MutableList<Case>) : RecyclerView.Adapter<ViewHolder?>() {

    private var cases: List<Case> = cases

    //TODO 07 OnCreateViewHolder erklären

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)
        val caseView: View = inflater.inflate(R.layout.case_layout, parent, false)
        return CaseViewHolder(caseView)
    }
    //TODO 08 OnBindViewHolder erklären

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val caseHolder = holder as CaseViewHolder
        val case: Case = this.cases[position]
        caseHolder.titleView.text = case.title
        caseHolder.dateView.text = case.solvingDate.toString()
    }

    override fun getItemCount(): Int {
        return cases.size
    }

    //TODO 06 ViewHolder definieren
    class CaseViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var titleView: TextView = itemView.findViewById<View>(R.id.case_title) as TextView
        var dateView: TextView = itemView.findViewById<View>(R.id.case_date) as TextView
    }
}