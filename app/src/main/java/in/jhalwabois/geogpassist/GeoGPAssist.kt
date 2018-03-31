package `in`.jhalwabois.geogpassist

import android.app.Application
import com.androidnetworking.AndroidNetworking

/**
 * Created by betterclever on 30/3/18.
 */

class GeoGPAssist : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext);
    }
}