package at.htl.demoapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.pozo.KotlinBuilder

@Entity
@KotlinBuilder
data class DatabasePerson (
    val name: String,
    @PrimaryKey val username: String
)