package academy.zere.app.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey val id: Int,
    val name: String
)
