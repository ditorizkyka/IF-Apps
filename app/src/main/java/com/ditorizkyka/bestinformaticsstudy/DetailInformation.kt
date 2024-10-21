package com.ditorizkyka.bestinformaticsstudy

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailInformation : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnShare : Button
    private lateinit var campusTitle : String
    private lateinit var campusProfile : String

    companion object {
        const val EXTRA_CAMPUS = "extra_campus"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_information)

        val campusImg : ImageView = findViewById(R.id.campus_img)
        val campusName : TextView = findViewById(R.id.campus_name)
        val campusFound : TextView = findViewById(R.id.campus_founded)
        val campusAkred : TextView = findViewById(R.id.campus_akreditation)
        val campusLocation : TextView = findViewById(R.id.campus_location)
        val campusLongDesc : TextView = findViewById(R.id.campus_desc)
        val campusRector : TextView = findViewById(R.id.campus_rector)
        val campusProfile : TextView = findViewById(R.id.campus_profile)



        btnShare  = findViewById(R.id.action_share)

        btnShare.setOnClickListener(this)

        val campus = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Campus>(EXTRA_CAMPUS, Campus::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_CAMPUS)}


            if (campus != null) {
                campusImg.setImageResource(campus.image)
                campusName.text = campus.name
                campusAkred.text = campus.accreditation
                campusFound.text = campus.founded
                campusRector.text = campus.rector
                campusLocation.text = campus.location
                campusLongDesc.text = campus.longDesc
                campusProfile.text = campus.profile

                this.campusTitle = campus.name
                this.campusProfile = campus.profile

            } else {
                val text = "NO DATA FOUND"
                campusName.text = "null"
                campusAkred.text = "null"
                campusFound.text = "null"
                campusLocation.text = "null"
                campusLongDesc.text = "null"
                campusProfile.text = "null"
            }






    }

    override fun onClick(v: View?) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "$campusTitle\n Profile : $campusProfile")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Bagikan teks dengan:"))

    }
}