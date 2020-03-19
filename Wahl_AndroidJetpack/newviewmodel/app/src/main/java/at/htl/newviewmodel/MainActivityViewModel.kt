package at.htl.newviewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.htl.newviewmodel.model.Case
import at.htl.newviewmodel.model.Detective
import java.time.Instant
import java.util.*


class MainActivityViewModel: ViewModel() {
    //TODO 03 ViewModel mit MutableLiveData befüllen
    private val _detective: MutableLiveData<Detective> = MutableLiveData()

    init {
        _detective.value = Detective("Sherlock Holmes")
    }

    fun getDetective(): LiveData<Detective>{
        return _detective;
    }

    fun setDetectiveName(){
        _detective.value!!.name.set(
            if (_detective.value!!.name.get() == "Sherlock Holmes") "Herlock Sholmes" else "Sherlock Holmes"
        )
    }
    fun addCases(){
        var caseList = mutableListOf<Case>()

        caseList.add(Case("A Study in Pink",Date.from(Instant.parse("2011-07-24T00:00:00.00Z"))))
        caseList.add(Case("The Blind Banker",Date.from(Instant.parse("2011-07-31T00:00:00.00Z"))))
        caseList.add(Case("The Great Game",Date.from(Instant.parse("2011-08-07T00:00:00.00Z"))))

        caseList.add(Case("A Scandal in Belgravia",Date.from(Instant.parse("2012-05-17T00:00:00.00Z"))))
        caseList.add(Case("The Hounds of Baskerville",Date.from(Instant.parse("2012-05-27T00:00:00.00Z"))))
        caseList.add(Case("The Reichenbach Fall",Date.from(Instant.parse("2012-05-28T00:00:00.00Z"))))

        caseList.add(Case("The Empty Hearse",Date.from(Instant.parse("2014-05-29T00:00:00.00Z"))))
        caseList.add(Case("The Sign of Three",Date.from(Instant.parse("2014-06-08T00:00:00.00Z"))))
        caseList.add(Case("His Last Vow",Date.from(Instant.parse("2014-06-09T00:00:00.00Z"))))
        caseList.add(Case("The Abominable Bride",Date.from(Instant.parse("2016-04-28T00:00:00.00Z"))))

        caseList.add(Case("The Six Thatchers",Date.from(Instant.parse("2017-06-04T00:00:00.00Z"))))
        caseList.add(Case("The Lying Detective",Date.from(Instant.parse("2017-06-05T00:00:00.00Z"))))
        caseList.add(Case("The Final Problem",Date.from(Instant.parse("2017-06-11T00:00:00.00Z"))))

        _detective.value!!.addCases(caseList)
    }

}