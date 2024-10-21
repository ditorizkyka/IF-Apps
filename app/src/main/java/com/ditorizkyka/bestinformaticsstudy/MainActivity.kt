package com.ditorizkyka.bestinformaticsstudy

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ditorizkyka.bestinformaticsstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvCampus: RecyclerView
    private val list = ArrayList<Campus>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        rvCampus = findViewById(R.id.rv_campus)
        rvCampus.setHasFixedSize(true)


        list.addAll(getListCampus())
        showRecyclerList()
    }

    private fun getListCampus(): ArrayList<Campus> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val longDesc = resources.getStringArray(R.array.data_long_description)
        val dataFounded = resources.getStringArray(R.array.data_founded)
        val rector = resources.getStringArray(R.array.data_rectors)
        val accreditation = resources.getStringArray(R.array.data_accreditation)
        val location = resources.getStringArray(R.array.data_location)
        val profileDesc = resources.getStringArray(R.array.data_informatics_profile)
        val listCampus = ArrayList<Campus>()
        for (i in dataName.indices) {
                val hero = Campus(dataName[i], dataDescription[i] ,  dataPhoto.getResourceId(i, -1),location[i] ,profileDesc[i], dataFounded[i], longDesc[i], rector[i], accreditation[i])
            listCampus.add(hero)
        }
        return listCampus
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerList() {
        rvCampus.layoutManager = LinearLayoutManager(this)
        val listCampusAdapter = ListCampusAdapter(list)
        rvCampus.adapter = listCampusAdapter

        listCampusAdapter.setOnItemClickCallback(object : ListCampusAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Campus) {

                val moveWithObjectIntent = Intent(this@MainActivity, DetailInformation::class.java)
                moveWithObjectIntent.putExtra(DetailInformation.EXTRA_CAMPUS, data)
                startActivity(moveWithObjectIntent)
            }
        })
    }

}