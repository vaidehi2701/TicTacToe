package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        val btnSubmit=findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
           SignUp()
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

    }

    private fun SignUp() {
        if(etName.text.toString().isEmpty()){
            etName.error="Please Enter Email"
            etName.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(etName.text.toString()).matches()){
            etName.error="Enter Valid Email"
            etName.requestFocus()
            return
        }
        if (etPassword.text.toString().isEmpty()){
            etPassword.error="Please Enter Password"
            etPassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(etName.text.toString(), etPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(baseContext, "SignUp Failed",
                        Toast.LENGTH_SHORT).show()

                }

                // ...
            }

    }
}
