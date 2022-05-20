package com.example.teamdraw.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teamdraw.R
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create()

        initButton()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun initButton() {
        val btn_facebookLogin = findViewById<LoginButton>(R.id.btn_facebookLogin)
        btn_facebookLogin.setPermissions("email", "public_profile")

        // facebook login 콜백 함수
        btn_facebookLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                // 로그인 성공
                val credential = FacebookAuthProvider.getCredential(result.accessToken.token)
                // 페이스북에서 로그인 토큰을 받아옴

                auth.signInWithCredential(credential) // 파이어베이스에 토큰을 넘겨줌
                    .addOnCompleteListener(this@LoginActivity) { task ->
                        if (task.isSuccessful) {
                            finish()
                            // 페이스북 계정이 등록되어있지 않으면 자동으로 회원가입시키고 success를 리턴
                        } else { // 로그인 실패한 경우
                            Toast.makeText(this@LoginActivity, "페이스북 로그인이 실패했습니다", Toast.LENGTH_SHORT).show()
                        }
                    }


            }

            override fun onCancel() {
                // 로그인 취소
            }

            override fun onError(error: FacebookException?) {
                // 로그인 실패
                Toast.makeText(this@LoginActivity, "페이스북 로그인이 실패했습니다", Toast.LENGTH_SHORT).show()
            }


        })
    }
}