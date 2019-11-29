package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.etName
import kotlinx.android.synthetic.main.activity_sign_up.etPassword

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val btnSignUp=findViewById<Button>(R.id.btnSignUp)
        btnSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val btnLogin=findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            DoLogin()
        }

        auth = FirebaseAuth.getInstance()

    }

    private fun DoLogin() {
        if(etLoginName.text.toString().isEmpty()){
            etLoginName.error="Please Enter Email"
            etLoginName.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(etLoginName.text.toString()).matches()){
            etLoginName.error="Enter Valid Email"
            etLoginName.requestFocus()
            return
        }
        if (etLoginPassword.text.toString().isEmpty()){
            etLoginPassword.error="Please Enter Password"
            etLoginPassword.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(etLoginName.text.toString(), etLoginPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    Toast.makeText(baseContext, "SignUp Failed",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // ...
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser!=null){
            startActivity(Intent(this,HomeActivity::class.java))
        }
        else{
            Toast.makeText(baseContext, "Login   Failed",
                Toast.LENGTH_SHORT).show()

        }

    }

}
