package `in`.jhalwabois.geogpassist

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority

/**
 * Created by betterclever on 30/3/18.
 */


fun getResponseForQuery(query: String, currentLanguage: String) =
        /*AndroidNetworking.get("http://aksh-assistant.herokuapp.com/nlp")
                .addQueryParameter("q", query)
                .setPriority(Priority.HIGH)
                .build()*/
        AndroidNetworking.get("http://192.168.43.6:3000/nlp")
                .addQueryParameter("q", query)
                .addQueryParameter("lang", currentLanguage)
                .setPriority(Priority.HIGH)
                .build()
