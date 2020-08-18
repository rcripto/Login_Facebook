package fake.com.loginfacebook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.util.*

class MainActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        var loginButton = findViewById<Button>(R.id.login_button)
        val intent = Intent(this, AuthenticatedActivity::class.java)

        loginButton.setOnClickListener(View.OnClickListener {
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d("MainActivity", "Facebook token:" + loginResult.accessToken.token)
                        //startActivity(Intent(applicationContext, AuthenticatedActivity::class.java))
                        startActivity(intent)
                        finish()
                    }

                    override fun onCancel() {
                        Log.d("MainActivity", "Facebook onCancel")
                    }

                    override fun onError(error: FacebookException?) {
                        Log.d("MainActivity", "Facebook onError")
                    }
                })
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}