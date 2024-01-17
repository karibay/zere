package academy.zere.app.activities

import DBHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import academy.zere.app.R
import academy.zere.app.helpers.CourseListAdapter
import androidx.recyclerview.widget.RecyclerView

class CourseListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_list)

        val courseListRecycler = findViewById<RecyclerView>(R.id.recycler_courses)

        val dbHelper = DBHelper(this, null)
        val courses = dbHelper.getAllCourses()

        courseListRecycler.adapter = CourseListAdapter(courses)
    }
}