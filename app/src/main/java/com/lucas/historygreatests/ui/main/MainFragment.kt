package com.lucas.historygreatests.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lucas.historygreatests.R
import com.lucas.historygreatests.UserViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        context?.let {
            userViewModel.initViewModel(it)
        }

        if (userViewModel.isLogged()){
            navController.navigate(R.id.navigation_home)
        }
        else{
            navController.navigate(R.id.navigation_login)
        }

    }
}