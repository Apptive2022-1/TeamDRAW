package com.example.teamdraw.ui.fragments

import android.app.Dialog
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
import androidx.viewpager2.widget.ViewPager2
import com.example.teamdraw.adapters.InputInformationViewPagerAdapter
import com.example.teamdraw.databinding.FragmentInputInformationBinding
import com.example.teamdraw.models.User
import com.example.teamdraw.ui.dialog.LoadingDialog
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
        btnBack()
        initInputInformationViewPager()
    }

    private fun btnBack() {
        binding.btnBack.setOnClickListener {
            binding.btnBack.isEnabled = false // 버튼 두번눌러지면 앱 터짐
            val dialog = makeLoadingDialog() // Loading Dialog 호출
            dialog.show()
            updateUserData(dialog)
        }
    }

    private fun  updateUserData(dialog : Dialog){
            if(userInfoViewModel.positionList.value!!.isEmpty()){ // 선택된 포지션이 없으면 선택한 디테일도 다 지움
                userInfoViewModel.positionDetailList.value!!.clear()
            }

            val userId = auth.currentUser?.uid // userId 가져오기
            val db = Firebase.firestore
            val dbRef = db.collection("Users").document(userId.toString())
            dbRef.get()
                .addOnSuccessListener {
                    val user = User(
                        userId.toString(), userInfoViewModel.name.value,
                        userInfoViewModel.nickname.value, userInfoViewModel.sex.value,
                        userInfoViewModel.local.value, userInfoViewModel.univ.value,
                        userInfoViewModel.univEmail.value, userInfoViewModel.major.value,
                        userInfoViewModel.grade.value, userInfoViewModel.emailAuthenticated.value,
                        userInfoViewModel.departureList.value, userInfoViewModel.positionList.value,
                        userInfoViewModel.positionDetailList.value, userInfoViewModel.selfItroduce.value,
                        userInfoViewModel.personalLink.value
                    )

                    db.collection("Users").document(userId.toString())
                        .set(user)
                        .addOnSuccessListener {
                            Log.d("DB Update ", "success")
                            dialog.dismiss()
                            navHostController.popBackStack()

                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }

                }
                .addOnFailureListener {
                    Toast.makeText(context, "인터넷 연결이 불안정합니다. 정보 저장에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

            Log.d(
                "Cur user info : ", userInfoViewModel.name?.value.toString() + " "
                        + userInfoViewModel.nickname.value.toString() + " "
                        + userInfoViewModel.sex.value.toString() + " "
                        + userInfoViewModel.grade.value.toString() + " "
                        + userInfoViewModel.univ.value.toString() + " "
                        + userInfoViewModel.univEmail.value.toString() + " "
                        + userInfoViewModel.major.value.toString() + " "
                        + userInfoViewModel.emailAuthenticated.value.toString() + " "
            )

    }
    private fun makeLoadingDialog(): LoadingDialog {
        return LoadingDialog(requireContext())
    }

    private fun initInputInformationViewPager() {
        val vpAdapter = InputInformationViewPagerAdapter(this)
        binding.vpInputInformation.adapter = vpAdapter // 정보입력 화면 뷰페이저로 연결
        binding.vpInputInformation.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.tvPageDescription.text = "기본정보 입력"
                    1 -> binding.tvPageDescription.text = "기본정보 입력"
                    2 -> binding.tvPageDescription.text = "기본정보 입력"
                    3 -> binding.tvPageDescription.text = "성향체크"
                    4 -> binding.tvPageDescription.text = "성향체크"
                    5 -> binding.tvPageDescription.text = "성향체크"
                    6 -> binding.tvPageDescription.text = "스킬셋, 툴"
                    7 -> binding.tvPageDescription.text = "스킬셋, 툴"
                    8->  binding.tvPageDescription.text = "프로필 설정"
                }
            }
        })
    }


}