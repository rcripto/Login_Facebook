package fake.com.loginfacebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AuthenticatedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticated)
    }
}