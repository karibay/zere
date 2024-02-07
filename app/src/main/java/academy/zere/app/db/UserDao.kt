package academy.zere.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE username = :username")
    fun findUsername(username: String): User

    @Insert
    fun insertAll(vararg users: User)
}