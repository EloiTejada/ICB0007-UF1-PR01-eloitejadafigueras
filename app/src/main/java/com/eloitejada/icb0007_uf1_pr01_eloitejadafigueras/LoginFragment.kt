package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etUsername = view.findViewById(R.id.et_username)
        etPassword = view.findViewById(R.id.et_password)
        btnLogin = view.findViewById(R.id.btn_login)

        btnLogin.setOnClickListener {
            handleLogin()
        }

        lifecycleScope.launch {
            viewModel.username.collectLatest { username ->
                if (etUsername.text.toString() != username) {
                    etUsername.setText(username)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.password.collectLatest { password ->
                if (etPassword.text.toString() != password) {
                    etPassword.setText(password)
                }
            }
        }
        etUsername.addTextChangedListener {
            viewModel.setUsername(it.toString())
        }

        etPassword.addTextChangedListener {
            viewModel.setPassword(it.toString())
        }
    }



    private fun handleLogin() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (username == "admin" && password == "admin1234") {
            onLoginSuccess()
        } else {
            Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onLoginSuccess() {
        Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()

        val intent = Intent(requireActivity(), MainActivity::class.java)
        intent.putExtra("navigateTo", "main_screen")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }



}
