package academy.zere.app.activities

import DBHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import academy.zere.app.R
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    var editLogin: EditText? = null
    var editPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editLogin = findViewById<EditText>(R.id.edit_login)
        editPassword = findViewById<EditText>(R.id.edit_password)
        val buttonLogin: Button = findViewById<Button>(R.id.button_login)


        buttonLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val dbHelper = DBHelper(this, null)
        val userName = editLogin?.text.toString()
        val password = editPassword?.text.toString()
        if (dbHelper.isUserPasswordCorrect(userName, password)) {
            val intent = Intent(this, CourseListActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.error_login_incorrect, Toast.LENGTH_LONG).show()
        }
    }
}