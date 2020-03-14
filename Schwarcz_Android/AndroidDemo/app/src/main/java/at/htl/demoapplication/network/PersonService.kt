package at.htl.demoapplication.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PersonService {
    @GET("/users")
    fun getPersons(): Deferred<List<NetworkPerson>>
}