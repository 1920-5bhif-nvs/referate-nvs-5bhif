package at.htl.demoapplication.network

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class NetworkPerson (
    val name: String,
    val username: String
)