package com.lucas.historygreatests.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.lucas.historygreatests.MainActivity
import com.lucas.historygreatests.R
import com.lucas.historygreatests.UserViewModel
import com.lucas.historygreatests.ui.components.views.LoadingFullDialog
import com.lucas.historygreatests.ui.login.LoginFragment

open class BaseFragment(resourceLayoutId: Int) : Fragment(resourceLayoutId) {

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingFullDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIfLoginIsNeeded()
    }

    private fun checkIfLoginIsNeeded() {
        val navController = findNavController()
        val currentBackStackEntry = navController.currentBackStackEntry!!
        val savedStateHandle = currentBackStackEntry.savedStateHandle
        savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            .observe(currentBackStackEntry, Observer { success ->
                if (!success) {
                    val startDestination = navController.graph.startDestination
                    val navOptions = NavOptions.Builder()
                        .setPopUpTo(startDestination, true)
                        .build()
                    navController.navigate(startDestination, null, navOptions)
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        userViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            if (user == null) {
                navController.navigate(R.id.navigation_login)
            }
        })
    }

    fun showLoadingDialog() {
        if (!this::loadingDialog.isInitialized) {
            loadingDialog = LoadingFullDialog(requireContext())
        }
        loadingDialog.show()
    }

    fun dismissLoadingDialog() {
        if (this::loadingDialog.isInitialized) {
            loadingDialog?.let {
                it.dismiss()
            }
        }
    }
}