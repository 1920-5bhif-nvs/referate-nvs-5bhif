package at.htl.demoapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import at.htl.demoapplication.database.PersonDatabase
import at.htl.demoapplication.database.asDomainModel
import at.htl.demoapplication.domain.Person
import at.htl.demoapplication.network.Network
import at.htl.demoapplication.network.NetworkPerson
import at.htl.demoapplication.network.NetworkPersonContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonRepository(private val database: PersonDatabase) {

    val persons: LiveData<List<Person>> = Transformations.map(database.personDao.getPersons()) {
        it.asDomainModel()
    }

    suspend fun refreshPersons() {
        withContext(Dispatchers.IO) {
            val persons = Network.persons.getPersons().await()
            val personsContainer = NetworkPersonContainer(persons)
            database.personDao.deleteAll()
            database.personDao.insertAll(*personsContainer.asDatabaseModel())
        }
    }

}