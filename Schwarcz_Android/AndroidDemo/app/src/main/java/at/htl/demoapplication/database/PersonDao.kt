package at.htl.demoapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Query("select * from databaseperson")
    fun getPersons(): LiveData<List<DatabasePerson>>
    @Insert
    fun insertAll(vararg persons: DatabasePerson)
}