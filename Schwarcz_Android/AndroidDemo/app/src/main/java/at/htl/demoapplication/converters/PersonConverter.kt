package at.htl.demoapplication.converters

import at.htl.demoapplication.database.DatabasePerson
import at.htl.demoapplication.domain.Person
import at.htl.demoapplication.network.NetworkPerson
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper

@Mapper
interface PersonConverter {

    // region Network <> Database
    fun convertToDatabaseModel(person: NetworkPerson) : DatabasePerson

    @InheritInverseConfiguration
    fun convertToNetworkModel(person: DatabasePerson) : NetworkPerson
    // endregion

    // region Database <> Domain
    fun convertToDomainModel(person: DatabasePerson) : Person

    @InheritInverseConfiguration
    fun convertToDatabaseModel(person: Person) : DatabasePerson
    // endregion

    // region Network <> Domain
    fun convertToDomainModel(person: NetworkPerson) : Person

    @InheritInverseConfiguration
    fun convertToNetworkModel(person: Person) : NetworkPerson
    // endregion

}