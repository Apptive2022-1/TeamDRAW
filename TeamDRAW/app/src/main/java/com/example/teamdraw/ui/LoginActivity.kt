package com.example.teamdraw.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.teamdraw.R
import com.example.teamdraw.models.User
import com.example.teamdraw.viewmodels.UserInfoViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {
    val userInfoViewModel: UserInfoViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create()

        initFacebookLoginButton()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleSuccessLogin() {
        if (auth.currentUser == null) {
            Toast.makeText(this@LoginActivity, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
            return@handleSuccessLogin
        }

        val userId = auth.currentUser?.uid // userId 가져오기
        val db = Firebase.firestore

        val dbRef = db.collection("Users").document(userId.toString())
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) { // document가 존재하는 경우
                    Log.d("handleSuccessLogin : ", document.data.toString())
                    Log.d("Login Activity  ", "이미 가입된 사용자")
                    setDataIntoViewModel() // 이미 가입된 사용자는 데이터를 불러와서 viewModel에 저장
                    setResult(RESULT_OK)
                    finish()
                }
                else { // document == null 인 경우는, 처음 가입하는 사용자
                    val user = User(userId.toString(),)
                    db.collection("Users").document(userId.toString())
                        .set(user)
                        .addOnSuccessListener {
                            Log.d("첫번째 가입 ", "success")
                            setResult(RESULT_FIRST_USER)
                            finish() // db 업데이트되면 정상종료
                        }
                        .addOnFailureListener {
                            Toast.makeText(this@LoginActivity, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this@LoginActivity, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }


    }

    private fun initFacebookLoginButton() {
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
                            handleSuccessLogin()
                            // 페이스북 계정이 등록되어있지 않으면 자동으로 회원가입시키고 success를 리턴
                        } else { // 로그인 실패한 경우
                            Toast.makeText(
                                this@LoginActivity,
                                "페이스북 로그인이 실패했습니다",
                                Toast.LENGTH_SHORT
                            ).show()
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
    private fun setDataIntoViewModel() {
        val userId = auth.currentUser?.uid // userId 가져오기
        val db = Firebase.firestore

        val dbRef = db.collection("Users").document(userId.toString())
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val userData = document.toObject<User>()
                    updateViewModel(userData)
                    Log.d("setDataIntoViewModel ", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("setDataIntoViewModel ", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("setDataIntoViewModel ", "get failed with ", exception)
            }
    }

    fun updateViewModel(user : User?){
        if(user?.name != null){
            userInfoViewModel.updateValue(user?.name.toString(), "NAME")
        }
        if(user?.nickname != null){
            userInfoViewModel.updateValue(user?.nickname.toString(), "NICKNAME")
        }
        if(user?.sex != null){
            userInfoViewModel.updateValue(user?.sex.toString(), "SEX")
        }
        if(user?.univ != null){
            userInfoViewModel.updateValue(user?.univ.toString(), "UNIV")
        }
        if(user?.univ_email != null){
            userInfoViewModel.updateValue(user?.univ_email.toString(), "UNIV_EMAIL")
        }
        if(user?.major != null){
            userInfoViewModel.updateValue(user?.grade.toString(), "GRADE")
        }
        if(user?.grade != null){
            userInfoViewModel.updateValue(user?.major.toString(), "MAJOR")
        }
    }
}