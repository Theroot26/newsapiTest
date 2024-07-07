package com.test.appnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.test.appnews.databinding.DetailActivityBinding

class DetailActivity : ComponentActivity() {

    lateinit var binding: DetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setActionBar(binding.toolbar).apply {


//            setDisplayHomeAsUpEnabled(true)
//            setDisplayShowHomeEnabled(true)
            actionBar?.setDisplayHomeAsUpEnabled(true)
            actionBar?.setDisplayShowHomeEnabled(true)
            title = "Detail"
        }



        val image = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        binding.titleTextView.text = title
        binding.contentTextView.text = description
        Glide.with(this)
            .load(image)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher) // Placeholder image while loading
            .into(binding.imageView)
//
//        val titleTextView: TextView = findViewById(R.id.detailTitle)
//        val descriptionTextView: TextView = findViewById(R.id.detailDescription)
//
//        titleTextView.text = title
//        descriptionTextView.text = description

    }


}
