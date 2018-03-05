package xyz.juniverse.lib.firebase

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import xyz.juniverse.lib.console

/**
 * Created by juniverse on 05/03/2018.
 */
class Report {
    companion object {
        var enable: Boolean = false

        fun event(context: Context, event: String, params: Array<Pair<String, String>>? = null) {
            val bundle = Bundle()
            if (params != null)
                for (pair in params)
                    bundle.putString(pair.first, pair.second)

            if (enable)
                FirebaseAnalytics.getInstance(context).logEvent(event, bundle)
            else
                console.i("sending event:", event, bundle)
        }

        fun event(context: Context, event: String, key: String, value: String) {
            event(context, event, arrayOf(Pair(key, value)))
        }
    }
}