package at.htl.demoapplication.domain

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class Person (
    val name: String,
    val username: String
)