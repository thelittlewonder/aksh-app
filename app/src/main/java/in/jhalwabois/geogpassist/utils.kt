package `in`.jhalwabois.geogpassist

import android.content.Context
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANRequest
import com.androidnetworking.common.Priority

/**
 * Created by betterclever on 30/3/18.
 */


fun getResponseForQuery(context: Context, query: String, currentLanguage: String): ANRequest<out ANRequest<*>> {
    val url = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE )
            .getString("backend_server", "https://aksh-backend.herokuapp.com")

    return AndroidNetworking.get("$url/nlp")
            .addQueryParameter("q", query)
            .addQueryParameter("lang", currentLanguage)
            .setPriority(Priority.HIGH)
            .build()
}
