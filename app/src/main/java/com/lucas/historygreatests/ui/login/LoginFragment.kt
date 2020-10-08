package com.lucas.historygreatests.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.lucas.historygreatests.R
import com.lucas.historygreatests.UserViewModel
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
    }
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)

        button.setOnClickListener {
            login()
        }
    }

    fun login() {
        userViewModel.login().observe(viewLifecycleOwner, Observer { result ->
            if (result != null) {
                savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                findNavController().popBackStack()
            } else {
                showErrorMessage()
            }
        })
    }

    private fun showErrorMessage() {
        //TODO: Show Login error message
    }

}