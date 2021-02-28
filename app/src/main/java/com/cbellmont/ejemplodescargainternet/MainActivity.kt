package com.cbellmont.ejemplodescargainternet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cbellmont.ejemplodescargainternet.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

interface MainActivityInterface {
    suspend fun onStarshipsReceived(listStarships: List<Starships>)
}

// IMPORTANT: Passing the activity to a the receiver is not a good practice, it may cause issues
// with the activity-s lifecycle. We are doing it just to keep the focus on the target of this example
class MainActivity : AppCompatActivity(), MainActivityInterface {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setContentView(R.layout.activity_main)


        CoroutineScope(Dispatchers.IO).launch{
            GetAllStarships.send(this@MainActivity)
        }
    }

    override suspend fun onStarshipsReceived(listStarships : List<Starships>) {
        withContext(Dispatchers.Main){
            binding.tvFilms.text = ""
            listStarships.forEach {
                tvFilms.append(it.toString())
            }
        }
    }
}