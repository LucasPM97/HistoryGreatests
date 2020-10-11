package com.lucas.historygreatests.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.lucas.historygreatests.R
import com.lucas.historygreatests.UserViewModel
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment() {

    companion object {
        const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
        const val GOOGLE_SIGNUP_RC = 202
    }

    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var googleSignInClient: GoogleSignInClient

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


        login_button.setOnClickListener {
            startSignIn()
        }

        initGoogleSignInClient()
        setupViewModel()
    }

    private fun initGoogleSignInClient() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        context?.let { context ->
            googleSignInClient = GoogleSignIn.getClient(context, gso)
        }
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
        val signInIntent:Intent = googleSignInClient.signInIntent;
        startActivityForResult(signInIntent, GOOGLE_SIGNUP_RC);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === GOOGLE_SIGNUP_RC) {

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