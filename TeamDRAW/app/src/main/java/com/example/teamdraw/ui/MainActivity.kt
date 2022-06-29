package com.example.teamdraw.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.teamdraw.R
import com.example.teamdraw.databinding.ActivityMainBinding
import com.example.teamdraw.models.User
import com.example.teamdraw.viewmodels.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val userInfoViewModel: UserInfoViewModel by viewModels()

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var getResultCode: ActivityResultLauncher<Intent>
    lateinit var navHostController: NavController


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 액션바 관련 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        navHostController = navHostFragment.navController

        getResultCode =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_FIRST_USER) { // 최초 로그인시에는 정보입력창으로 안내
                    navHostController.navigate(R.id.action_contestFragment_to_inputInformationFragment)
                }
            }
        login() // 자동 로그인


        binding.bottomNavBar.setupWithNavController(navHostController)
        navHostController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("dest : ", navHostController.graph.startDestDisplayName)
            when (destination.id) {
                R.id.contestDetailFragment, R.id.inputInformationFragment, R.id.chattingFragment, R.id.writeRecruitingFragment,
                R.id.oneToOneChatFragment, R.id.userProfileFragment, R.id.checkTeamNoticeFragment, R.id.memberEvaluateFragment -> {
                    supportActionBar?.hide()
                    bottomNavBarHide()
                }
                else -> {
                    supportActionBar?.show()
                    bottomNavBarShow()
                }
            }
        }
    }

    private fun login() {
        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            getResultCode.launch(intent)
        } // 로그인이 되어있지 않으면 loginActivity 호출
        else {
            setDataIntoViewModel()
            // 자동로그인이 되어있는 경우 DB에서 data를 가져와 ViewModel에 넣음
        }
    }

    private fun bottomNavBarHide() {
        binding.bottomNavBar.visibility = View.GONE
    }

    private fun bottomNavBarShow() {
        binding.bottomNavBar.visibility = View.VISIBLE
    }

    private fun setDataIntoViewModel() {
        val userId = auth.currentUser?.uid // userId 가져오기
        val db = Firebase.firestore
        val dbRef = db.collection("Users").document(userId.toString()) // userId로 DB 접근
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val userData = document.toObject<User>()
                    updateViewModel(userData)

                    Log.d("setDataIntoViewModel ", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("db get", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("db get", "get failed with ", exception)
            }
    }

    fun updateViewModel(user: User?) {
        if (user?.name != null) {
            userInfoViewModel.updateValue(user?.name.toString(), "NAME")
        }
        if (user?.nickname != null) {
            userInfoViewModel.updateValue(user?.nickname.toString(), "NICKNAME")
        }
        if (user?.sex != null) {
            userInfoViewModel.updateValue(user?.sex.toString(), "SEX")
        }
        if (user?.univ != null) {
            userInfoViewModel.updateValue(user?.univ.toString(), "UNIV")
        }
        if (user?.univ_email != null) {
            userInfoViewModel.updateValue(user?.univ_email.toString(), "UNIV_EMAIL")
        }
        if (user?.major != null) {
            userInfoViewModel.updateValue(user?.major.toString(), "MAJOR")
        }
        if (user?.grade != null) {
            userInfoViewModel.updateValue(user?.grade.toString(), "GRADE")
        }
        if (user?.local != null) {
            userInfoViewModel.updateValue(user?.local.toString(), "LOCAL")
        }
        if (user?.emailAuthenticated != null) {
            userInfoViewModel.updateValue(user?.emailAuthenticated.toString(), "AUTHENTICATE")
        }
        if (user?.departureList != null) {
            for (value in user?.departureList) {
                userInfoViewModel.addList(value, "DEPARTURE")
            }
        }
        if (user?.positionList != null) {
            for (value in user?.positionList) {
                userInfoViewModel.addList(value, "POSITION")
            }
        }
        if (user?.positionDetailList != null) {
            for (value in user?.positionDetailList) {
                userInfoViewModel.addList(value, "POSITION_DETAIL")
            }
        }
        if (user?.selfIntroduce != null) {
            userInfoViewModel.updateValue(user?.selfIntroduce.toString(), "SELFINTRODUCE")
        }
        if (user?.personalLink != null) {
            userInfoViewModel.updateValue(user?.personalLink.toString(), "PERSONALLINK")
        }
        if (user?.teamList != null) {
            for (value in user?.teamList) {
                userInfoViewModel.addList(value, "TEAMLIST")
            }
        }
        if (user?.one_to_one_ChatList != null) {
            for (value in user?.one_to_one_ChatList) {
                userInfoViewModel.addList(value, "ONETOONE")
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.myProfileFragment -> {
            Log.d("toolbar : ", "profile")
            navHostController.navigate(R.id.myProfileFragment)
            supportActionBar?.hide()
            true
        }
        R.id.chattingListFragment -> {
            navHostController.navigate(R.id.chattingListFragment)
            supportActionBar?.hide()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}