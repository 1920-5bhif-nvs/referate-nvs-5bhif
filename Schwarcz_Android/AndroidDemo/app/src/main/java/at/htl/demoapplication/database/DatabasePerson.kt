package at.htl.demoapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import at.htl.demoapplication.domain.Person

@Entity
class DatabasePerson (
    val name: String,
    @PrimaryKey val username: String
)

fun List<DatabasePerson>.asDomainModel(): List<Person> {
    return map {
        Person(
            name = it.name,
            username = it.username
        )
    }
}