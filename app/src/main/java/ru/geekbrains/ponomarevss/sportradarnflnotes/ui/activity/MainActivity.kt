package ru.geekbrains.ponomarevss.sportradarnflnotes.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import ru.geekbrains.ponomarevss.sportradarnflnotes.R
import ru.geekbrains.ponomarevss.sportradarnflnotes.mvp.model.entity.response.LeagueHierarchy
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val gson = Gson()
//        val leagueHierarchy = gson.fromJson(loadJSONFromAsset(), LeagueHierarchy::class.java)
//        println(leagueHierarchy.league.name)
//        println(leagueHierarchy.league.alias)
//        println("leagueHierarchy.conferences.size: ${leagueHierarchy.conferences.size}")
//        println(leagueHierarchy.conferences[0].name)
//        println("leagueHierarchy.conferences[0].divisions.size: ${leagueHierarchy.conferences[0].divisions.size}")
//        println(leagueHierarchy.conferences[0].divisions[0].name)
//        println("leagueHierarchy.conferences[0].divisions[0].teams.size: ${leagueHierarchy.conferences[0].divisions[0].teams.size}")
//        println(leagueHierarchy.conferences[0].divisions[0].teams[0].name)

    }

//    private fun loadJSONFromAsset(): String? {
//        val json = try {
//            val inputStream: InputStream = assets.open("LeagueHierarchy2021test")
//            val size: Int = inputStream.available()
//            val buffer = ByteArray(size)
//            inputStream.read(buffer)
//            inputStream.close()
//            String(buffer)
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//            return null
//        }
//        return json
//    }
}