package at.htl.demoapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import at.htl.demoapplication.converters.PersonConverter
import at.htl.demoapplication.database.PersonDatabase
import at.htl.demoapplication.domain.Person
import at.htl.demoapplication.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mapstruct.factory.Mappers

class PersonRepository(private val database: PersonDatabase) {

    val persons: LiveData<List<Person>> = Transformations.map(database.personDao.getPersons()) { list ->
        val converter = Mappers.getMapper(PersonConverter::class.java)
        list.map {
            converter.convertToDomainModel(it)
        }
    }

    suspend fun refreshPersons() {
        withContext(Dispatchers.IO) {
            val persons = Network.persons.getPersons().await()
            val converter = Mappers.getMapper(PersonConverter::class.java)
            val databasePersons = persons.map {
                converter.convertToDatabaseModel(it)
            }.toTypedArray()
            database.personDao.deleteAll()
            database.personDao.insertAll(*databasePersons)
        }
    }

}