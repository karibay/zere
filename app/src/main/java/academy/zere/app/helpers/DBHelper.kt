import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import academy.zere.app.models.Course
import academy.zere.app.models.User
import java.util.LinkedList

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
        SQLiteOpenHelper(context, "zere", factory, 3){
    override fun onCreate(db: SQLiteDatabase?) {
        val userQuery = "CREATE TABLE user(" +
                        "id INTEGER PRIMARY KEY," +
                        "username TEXT," +
                        "password TEXT" +
                    ");"
        val courseQuery = "" +
                "CREATE TABLE course(" +
                    "id INTEGER PRIMARY KEY," +
                    "title TEXT" +
                ");"
        val userCourseQuery = "" +
                "CREATE TABLE user_courses(" +
                    "user_id INTEGER," +
                    "course_id INTEGER," +
                    "FOREIGN KEY (user_id) REFERENCES users (id)," +
                    "FOREIGN KEY (course_id) REFERENCES courses (id)" +
                ");"
        db?.execSQL(courseQuery+userQuery+userCourseQuery)
        createTestData()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS users")
        db?.execSQL("DROP TABLE IF EXISTS courses")
        onCreate(db)
    }

    private fun insert(tableName: String, values: ContentValues){
        val db = this.writableDatabase
        db.insert(tableName, null, values)
        db.close()
    }

    fun addUser(user: User){
        val values = ContentValues()
        values.put("username", user.username)

        insert("users", values)
    }

    fun addCourse(course: Course){
        val values = ContentValues()
        values.put("title", course.title)

        insert("courses", values)
    }

    fun enrollUserToCourse(user: User, course: Course){
        val contentValues = ContentValues()
        contentValues.put("user_id", user.id)
        contentValues.put("course_id", course.id)
        insert("user_courses", contentValues)
    }

    fun getAllCourses(): List<Course>{
        val db = this.readableDatabase
        val query = "SELECT id, title FROM courses;"
        val cursor: Cursor? = db?.rawQuery(query, null)
        val courses = LinkedList<Course>()

        while (!cursor?.isLast!!){
            val courseId = cursor?.getInt(0)
            val courseTitle = cursor?.getString(1)
            courses.add(Course(courseId, courseTitle))
        }
        return courses
    }

    fun isUserPasswordCorrect(username: String, password: String): Boolean{
        val db = this.readableDatabase
        val query = "SELECT password FROM users WHERE username=?;"
        val cursor: Cursor? = db?.rawQuery(query, arrayOf(username))
        val savedPassword = cursor?.getString(0)
        return password.equals(savedPassword)
    }

    private fun createTestData(){
        val users = arrayOf(
            User(1, "smavy", "password")
        )
        val courses = arrayOf(
            Course(1, "Android"),
            Course(2, "Python")
        )

        for (user in users) addUser(user)
        for (course in courses) addCourse(course)
    }
}