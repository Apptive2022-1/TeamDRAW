package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.teamdraw.models.UserData
import com.example.teamdraw.databinding.FragmentInputInformationBinding
import com.example.teamdraw.adapters.InputInformationViewPagerAdapter
import com.example.teamdraw.viewmodels.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class InputInformationFragment : Fragment() {
    private lateinit var binding: FragmentInputInformationBinding
    lateinit var navHostController: NavController
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputInformationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navHostController = findNavController()
        initInputInformationViewPager()


        binding.btnBack.setOnClickListener {
            //  "back 버튼 클릭시 firestore 데이터 저장"
            val userId = auth.currentUser?.uid // userId 가져오기
            val db = Firebase.firestore
            val dbRef = db.collection("Users").document(userId.toString())
            dbRef.get()
                .addOnSuccessListener { document ->
                    // document == null 인 경우는, 처음 가입하는 사용자
                    val user = UserData(
                        userId.toString(), userInfoViewModel.name.value,
                        userInfoViewModel.nickname.value, userInfoViewModel.sex.value,
                        userInfoViewModel.city.value, userInfoViewModel.region.value,
                        userInfoViewModel.univ.value, userInfoViewModel.univEmail.value,
                        userInfoViewModel.major.value, userInfoViewModel.grade.value
                    )
                    db.collection("Users").document(userId.toString())
                        .set(user)
                        .addOnSuccessListener {
                            Log.d("DB Update ", "success")
                            navHostController.popBackStack()

                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
                        }

                }
                .addOnFailureListener {
                    Toast.makeText(context, "인터넷 연결이 불안정합니다. 정보 저장에 실패했습니다.", Toast.LENGTH_SHORT)
                        .show()
                }




            Log.d(
                "Cur user info : ", userInfoViewModel.name?.value.toString() + " "
                        + userInfoViewModel.nickname.value.toString() + " "
                        + userInfoViewModel.sex.value.toString() + " "
                        + userInfoViewModel.grade.value.toString() + " "
                        + userInfoViewModel.univ.value.toString() + " "
                        + userInfoViewModel.univEmail.value.toString() + " "
                        + userInfoViewModel.major.value.toString() + " "
            )


        }
    }

    private fun initInputInformationViewPager() {
        val vpAdapter = InputInformationViewPagerAdapter(this)
        binding.vpInputInformation.adapter = vpAdapter // 정보입력 화면 뷰페이저로 연결
    }


}