package com.lucas.historygreatests.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.lucas.historygreatests.R
import com.lucas.historygreatests.UserViewModel
import com.lucas.historygreatests.databinding.LoginFragmentBinding


class LoginFragment : Fragment(R.layout.login_fragment) {

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
        const val GOOGLE_SIGNUP_RC = 202
    }

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = LoginFragmentBinding.bind(view)

        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(LOGIN_SUCCESSFUL, false)


        binding.loginButton.setOnClickListener {
            startSignIn()
        }

        setupViewModel()
    }

    private fun setupViewModel() {
        userViewModel.user.observe(viewLifecycleOwner, Observer { result ->
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

    private fun startSignIn() {
        userViewModel.googleSignInClient.value?.let {
            val signInIntent:Intent = it.signInIntent;
            startActivityForResult(signInIntent, GOOGLE_SIGNUP_RC);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGNUP_RC) {

            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)

                val googleSignInAccount = task.result
                googleSignInAccount?.let {
                    userViewModel.authenticateWithFirebase(googleSignInAccount.idToken)
                }
            } catch (e: ApiException) {
                print(e.message)
            }
        }
    }
}