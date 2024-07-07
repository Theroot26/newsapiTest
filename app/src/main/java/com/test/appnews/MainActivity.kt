package com.test.appnews

import MyAdapter
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.appnews.Constant.Companion.apikey
import com.test.appnews.Constant.Companion.country
import com.test.appnews.databinding.MainActivityBinding
import com.test.appnews.respository.NewsRepository
import com.test.appnews.viewmodel.NewsViewModel
import com.test.appnews.viewmodelfactory.NewsViewModelFactory


class MainActivity : ComponentActivity() {

    lateinit var binding: MainActivityBinding
    lateinit var viewModel: NewsViewModel
    var select ="business";
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBar(binding.toolbar)
        binding.toolbar.title = select.uppercase()







        val repository = NewsRepository()
        val viewModelFactory = NewsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
        println(country)
        println(apikey)


        viewModel.fetchData(country , select,apikey)
        //viewModel.fetchData(country , "business",apikey)
        //viewModel.fetchData(country , "technology",apikey)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(this, emptyList()) { item ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("image", item.urlToImage)
                putExtra("title", item.title)
                putExtra("description", item.description)
            }
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }// Initialize with empty list


        recyclerView.adapter = adapter

        // Observe LiveData from ViewModel and update UI
        viewModel.data.observe(this, Observer { data ->

            try {
                val items = data.articles // Make API call using suspend function
                adapter = MyAdapter(this, items) { item ->
                    val intent = Intent(this, DetailActivity::class.java).apply {
                        putExtra("image", item.urlToImage)
                        putExtra("title", item.title)
                        putExtra("description", item.description)
                    }
                    startActivity(intent)
                }// Initialize with
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }


        })









    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> {
                // Handle search action
                select = "business"

                selectMenu()

                true
            }
            R.id.navigation_sport -> {
                // Handle settings action

                select = "sport"
                selectMenu()
                true
            }

            R.id.navigation_tech -> {
                // Handle settings action

                select = "technology"
                selectMenu()
                true
            }
            R.id.navigation_entertain -> {
                // Handle settings action

                select = "entertainment"
                selectMenu()
                true
            }
            R.id.navigation_th -> {
                // Handle settings action

                country = "th"
                selectMenu()
                true
            }
            R.id.navigation_us -> {
                // Handle settings action

                country = "us"
                selectMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun selectMenu(){

        binding.toolbar.title = select.uppercase()

        viewModel.fetchData(country , select,apikey)

    }





}

