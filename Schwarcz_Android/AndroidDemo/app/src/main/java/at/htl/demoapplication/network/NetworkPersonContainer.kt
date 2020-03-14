package at.htl.demoapplication.network

import at.htl.demoapplication.database.DatabasePerson
import at.htl.demoapplication.domain.Person

class NetworkPersonContainer(
    val persons: List<NetworkPerson>
) {
    fun asDomainModel(): List<Person> {
        return persons.map {
            Person(
                name = it.name,
                username = it.username
            )
        }
    }

    fun asDatabaseModel(): Array<DatabasePerson> {
        return persons.map {
            DatabasePerson(
                name = it.name,
                username = it.username
            )
        }.toTypedArray()
    }
}