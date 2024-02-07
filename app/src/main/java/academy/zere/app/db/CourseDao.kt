package academy.zere.app.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CourseDao {
    @Query("SELECT * FROM course")
    fun getAll(): List<Course>

    fun insertAll(vararg users: User)

}