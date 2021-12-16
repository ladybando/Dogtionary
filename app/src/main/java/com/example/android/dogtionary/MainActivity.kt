package com.example.android.dogtionary

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.android.dogtionary.databinding.ActivityMainBinding
import com.example.android.dogtionary.model.ImagesViewModel
import com.google.android.material.snackbar.Snackbar

//todo add database functionality for favorites only
//maybe set a refresh button that loads all new images
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: ImagesViewModel by viewModels()
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
        searchOptions(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.favorite -> {
            //when user clicks favorites open favorites list and show images from database
            val dogImageToPass = viewModel.dogPhoto.value!!.imageUrl!!
            val bundle = bundleOf("dogPhoto" to dogImageToPass)
            findNavController(R.id.dog_display_container)
                .setGraph(R.navigation.nav_graph, bundle)

            navController.navigate(R.id.action_dogPhotoListFragment_to_favoriteListFragment)
            Log.i("MainActivity", "Favorites clicked!")
            //allow menu processing to occur here not normal menu process
            true
        }
        R.id.image_tag -> {
            findNavController(R.id.dog_display_container)
            navController.navigate(R.id.action_dogPhotoListFragment_to_unsplashDetectImageFragment)
            //allow menu processing to occur here not normal menu process
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun searchOptions(menu: Menu) {
        val search = menu.findItem(R.id.app_bar_search)
        searchView = search.actionView as SearchView

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (searchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        //change search icon color
        val searchImgId = resources.getIdentifier("android:id/search_button", null, null)
        val searchIcon = searchView.findViewById<ImageView>(searchImgId)
        searchIcon.setImageResource(R.drawable.ic_breed_search)

        //change close button color
        val closeImgId = resources.getIdentifier("android:id/search_close_btn", null, null)
        val closeIcon = searchView.findViewById<ImageView>(closeImgId)
        closeIcon.setImageResource(R.drawable.ic_clear)

        //change EditText text color
        val searchTextId =
            searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
        val textView = searchView.findViewById<TextView>(searchTextId)
        textView.setTextColor(Color.BLACK)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(userQuery: String?): Boolean {
                //Called when this view wants to give up focus
                searchView.clearFocus()
                //Returns the query string currently in the text field. checks if string is empty?
                searchView.setQuery("", false)
                search.collapseActionView()
                if (!viewModel.status.value.equals("success")) {
                    viewModel.getPhotoByBreed(userQuery)
                } else {
                    Snackbar
                        .make(
                            this@MainActivity,
                            binding.cl,//needs to be SearchView textView
                            "Rotten doggy treats, try another search term!",
                            Snackbar.LENGTH_SHORT
                        )
                        .show()
                }
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

    }
}
