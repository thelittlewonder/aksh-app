package `in`.jhalwabois.geogpassist

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import com.androidnetworking.common.Priority
import com.uchuhimo.collections.biMapOf

/**
 * Created by betterclever on 30/3/18.
 */

val SUPPORTED_LANGUAGES = biMapOf(
        "English" to "en-IN",
        "Hindi" to "hi-IN",
        "Bengali" to "bn-IN",
        "Gujarati" to "gu-IN",
        "Marathi" to "mr-IN",
        "Kannada" to "kn-IN",
        "Tamil" to "ta-IN",
        "Telugu" to "te-IN"
)

fun getResponseForQuery(context: Context, query: String, currentLanguage: String = "en", gpCode: Int? = null): ANRequest<out ANRequest<*>> {
    val url = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
            .getString("backend_server", "https://speaking-geo-gp-backend.herokuapp.com")
    return AndroidNetworking.get("$url/api/query").apply {
        addQueryParameter("q", query)
        addQueryParameter("lang", currentLanguage.removeSuffix("-IN"))
        gpCode?.let {
            addQueryParameter("gp_code", gpCode.toString())
        }
        setPriority(Priority.HIGH)
    }.build()


}
