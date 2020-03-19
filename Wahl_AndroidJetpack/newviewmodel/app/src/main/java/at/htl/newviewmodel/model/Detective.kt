package at.htl.newviewmodel.model

import androidx.databinding.ObservableField


class Detective(name: String) {
    fun addCases(caseList: MutableList<Case>) {
        cases.addAll(caseList);
    }

    var name : ObservableField<String> = ObservableField(name)
     var cases: MutableList<Case> = mutableListOf()


}
