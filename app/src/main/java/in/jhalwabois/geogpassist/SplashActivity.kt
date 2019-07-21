package `in`.jhalwabois.geogpassist

import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.ayz4sci.androidfactory.permissionhelper.PermissionHelper
import kotlinx.android.synthetic.main.activity_splash.*
import pl.tajchert.nammu.PermissionCallback

class SplashActivity : AppCompatActivity() {

    private lateinit var permissionHelper: PermissionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        permissionHelper = PermissionHelper.getInstance(this)

        permissionButton.setOnClickListener {
            permissionHelper.verifyPermission(arrayOf(
                    "use Voice Assistant",
                    "Location",
                    "",
                    "Storage"
            ),
                    arrayOf(
                            android.Manifest.permission.RECORD_AUDIO,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    object : PermissionCallback {

                        override fun permissionGranted() {
                            permissionButton.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE

                            Handler().postDelayed({
                                startActivity(Intent(this@SplashActivity, MapsActivity::class.java))
                            }, 1000)
                        }

                        override fun permissionRefused() {
                            Toast.makeText(this@SplashActivity, "Please grant permissions to proceed", Toast.LENGTH_LONG).show()
                            finish()
                        }

                    })
        }

        val permissionIsAvailable = getPermissionAvailability()

        if (!permissionIsAvailable) {
            permissionButton.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        } else {
            permissionButton.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            Handler().postDelayed({
                startActivity(Intent(this, MapsActivity::class.java))
                finish()
            }, 1000)
        }
    }

    private fun getPermissionAvailability(): Boolean {
        val permissions = listOf(
                android.Manifest.permission.RECORD_AUDIO ,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        return permissions.none {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

    }
}
