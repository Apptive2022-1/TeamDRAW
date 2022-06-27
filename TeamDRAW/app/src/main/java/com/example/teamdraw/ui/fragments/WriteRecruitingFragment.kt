package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentWriteRecruitingBinding
import com.example.teamdraw.models.Chat
import com.example.teamdraw.models.Recruiting
import com.example.teamdraw.models.TeamChat
import com.example.teamdraw.ui.dialog.LoadingDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat


class WriteRecruitingFragment : Fragment() {

    lateinit var binding : FragmentWriteRecruitingBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =  FragmentWriteRecruitingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
        InitEnterButton()
    }
    private fun makeLoadingDialog(): LoadingDialog {
        return LoadingDialog(requireContext())
    }

    private fun InitEnterButton(){
        binding.btnEnter.setOnClickListener{
            binding.btnBack.isEnabled = false // 버튼 두번눌러지면 앱 터짐
            binding.btnEnter.isEnabled = false // 버튼 두번눌러지면 앱 터짐
            val dialog = makeLoadingDialog() // Loading Dialog 호출
            dialog.show()
            val recruiting = Recruiting(auth.currentUser!!.uid,
                                        binding.etvTitle.text.toString(),
                                        binding.etvText.text.toString(),
                                        getTime())

            val randomId = makeRandomRecruitingID()
            val db = Firebase.firestore
            val dbRef = db.collection("Recruiting").document(randomId)
            dbRef.get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        Log.d("Recruiting ", "랜덤 ID생성 실패, 등록버튼 다시 누르세요")
                    } else {
                        db.collection("Recruiting").document(randomId)
                            .set(recruiting)
                            .addOnSuccessListener {
                                Log.d("Recruiting", "팀 구인글 작성 성공")
                                dialog.dismiss()
                                findNavController().popBackStack()
                            }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("db get", "get failed with ", exception)
                }
        }

    }
    private fun makeRandomRecruitingID(): String {
        val str = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
        var code = ""
        for (i in 1..10) code += str[(Math.random() * str.size).toInt()]
        return code
    }
    private fun getTime(): String {
        val current =  System.currentTimeMillis()
        val d = SimpleDateFormat("yyyy.MM.dd")
        val t = SimpleDateFormat("hh:mm a")
        val day = d.format(current)
        val time = t.format(current)
        return day+"\n"+time
    }



}