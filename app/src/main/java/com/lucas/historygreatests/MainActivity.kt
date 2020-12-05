package com.lucas.historygreatests

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lucas.historygreatests.databinding.ActivityMainBinding
import com.lucas.historygreatests.utils.AnalyticsSender


class MainActivity : AppCompatActivity() {

    private val bottomNavigationScreensIds = arrayOf(
        R.id.navigation_home,
        R.id.navigation_trending,
        R.id.navigation_library
    )

    private val noToolbarScreens = arrayOf(
        *bottomNavigationScreensIds,
        R.id.navigation_login,
        R.id.navigation_chapters_detailed
    )

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupNavController()

    }

    fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
    }

    fun setupNavController() {
        val navController = findNavController(R.id.nav_host_fragment)

        binding.toolbar.setupWithNavController(navController)
        binding.bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            trackNavigation(navController, destination)

            showOrHideMainActivityViews(destination)
        }
    }

    private fun trackNavigation(navController: NavController, destination: NavDestination) {
        navController.previousBackStackEntry?.let {
            if (it.destination.label.toString() != getString(R.string.screen_name_main)) {
                AnalyticsSender.trackNavigation(
                    from = it.destination.label.toString(),
                    to = destination.label.toString()
                )
            }
        }
    }

    private fun showOrHideMainActivityViews(destination: NavDestination) {
        binding.bottomNavView.isVisible = bottomNavigationScreensIds.contains(destination.id)
        setToolbarVisibility(!noToolbarScreens.contains(destination.id))
    }

    private fun setToolbarVisibility(visible: Boolean) {
        binding.toolbar.visibility = if (visible) View.VISIBLE else View.GONE
    }

}