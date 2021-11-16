package com.example.android.dogtionary

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.android.dogtionary.databinding.ActivityMainBinding
import com.example.android.dogtionary.model.DogViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: DogViewModel by viewModels()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.dog_display_container) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val search = menu.findItem(R.id.app_bar_search)
        searchView = search.actionView as SearchView

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (searchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(userQuery: String?): Boolean {
                //Called when this view wants to give up focus
                searchView.clearFocus()
                //Returns the query string currently in the text field. checks if string is empty?
                searchView.setQuery("", false)
                search.collapseActionView()
                if (userQuery.isNullOrBlank() || viewModel.status.value.equals("error")) {
                    viewModel.getPhotoByBreed(userQuery)
                } else {
                    Snackbar
                        .make(applicationContext,
                            binding.dogDisplayContainer,//needs to be SearchView textView
                            "Rotten doggy treats, try another search term!",
                            Snackbar.LENGTH_SHORT)
                        .show()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.favorite -> {
            Log.i("MainActivity", "Favorites clicked!")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
