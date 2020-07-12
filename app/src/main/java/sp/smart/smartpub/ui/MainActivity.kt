package sp.smart.smartpub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import sp.smart.smartpub.R

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupNavigation()
    }

    private fun setupNavigation(){
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        //bottom_nav.setupWithNavController(navController)
    }

    private fun setupToolbar() {
        val toolbarLayout: Toolbar = topAppBar
        setSupportActionBar(toolbarLayout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}