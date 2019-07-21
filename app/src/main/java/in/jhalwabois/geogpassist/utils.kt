package `in`.jhalwabois.geogpassist

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import com.androidnetworking.common.Priority

/**
 * Created by betterclever on 30/3/18.
 */


fun getResponseForQuery(context: Context, query: String, currentLanguage: String? = "en", gpCode: Int? = null): ANRequest<out ANRequest<*>> {
    val url = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
            .getString("backend_server", "https://speaking-geo-gp-backend.herokuapp.com")
    return AndroidNetworking.get("$url/api/query").apply {
        addQueryParameter("q", query)
        addQueryParameter("lang", currentLanguage)
        gpCode?.let {
            addQueryParameter("gp_code", gpCode.toString())
        }
        setPriority(Priority.HIGH)
    }.build()


}
