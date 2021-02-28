package com.cbellmont.ejemplodescargainternet

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class GetAllStarships {
    companion object {
        suspend fun send(mainActivity : MainActivityInterface?) {

            val client = OkHttpClient()
            val url = "https://swapi.dev/api/starships/"
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    Log.e("GetAllStarships", call.toString())

                }

                override fun onResponse(call: Call, response: Response) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val bodyInString = response.body?.string()
                        bodyInString?.let {
                            Log.w("GetAllStarships", bodyInString)
                            val JsonObject = JSONObject(bodyInString)

                            val results = JsonObject.optJSONArray("results")
                            results?.let {
                                Log.w("GetAllStarships", results.toString())
                                val gson = Gson()

                                val itemType = object : TypeToken<List<Starships>>() {}.type

                                val list = gson.fromJson<List<Starships>>(results.toString(), itemType)

                                mainActivity?.onStarshipsReceived(list)
                            }
                        }
                    }
                }
            })
        }
    }
}