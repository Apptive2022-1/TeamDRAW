package com.example.teamdraw.ui.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.teamdraw.R
import com.example.teamdraw.databinding.DialogRecruitingBinding
import com.example.teamdraw.models.OneToOneChat
import com.example.teamdraw.models.Recruiting
import com.example.teamdraw.models.User
import com.example.teamdraw.viewmodels.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class RecruitingDialog(
    recruitingDialogInterface: RecruitingDialogInterface,
    val recruiting: Recruiting
) : DialogFragment() {

    // 뷰 바인딩 정의
    private var _binding: DialogRecruitingBinding? = null
    private val binding get() = _binding!!
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val userInfoViewModel: UserInfoViewModel by activityViewModels()
    private var recruitingDialogInterface: RecruitingDialogInterface? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogRecruitingBinding.inflate(inflater, container, false)
        val view = binding.root
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRecruitingText.text = recruiting.text
        binding.tvRecruitingTime.text = recruiting.time
        binding.tvRecruitingTitle.text = recruiting.title
        val db = Firebase.firestore
        val dbRef = db.collection("Users").document(recruiting.userID) // userId로 DB 접근
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val userData = document.toObject<User>()
                    binding.userNicknameTv.text = userData!!.nickname
                } else {
                    Log.d("db get", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("db get", "get failed with ", exception)
            }

        initEnterTeamButton()


    }

    private fun initEnterTeamButton() {
        if (recruiting.userID == auth.currentUser!!.uid) {
            binding.btnEnterTeam.isEnabled = false
        }
        binding.btnEnterTeam.setOnClickListener {
            val randomId = makeRandomTeamID()
            userInfoViewModel.addList(randomId, "ONETOONE")
            val userId = auth.currentUser?.uid // userId 가져오기
            val db = Firebase.firestore
            val dbRef = db.collection("Users").document(userId.toString())
            dbRef.get()
                .addOnSuccessListener {
                    db.collection("Users").document(userId.toString())
                        .update("one_to_one_ChatList", userInfoViewModel.one_to_one_ChatList.value)
                        .addOnSuccessListener {
                            Log.d("DB Update ", "success")

                            // 상대방 DB 업데이트
                            val dbRef = db.collection("Users").document(recruiting.userID)
                            dbRef.get()
                                .addOnSuccessListener { document ->
                                    if (document != null) {
                                        var userData = document.toObject<User>()
                                        var newList = userData!!.one_to_one_ChatList!!
                                        newList.add(randomId)
                                        db.collection("Users").document(recruiting.userID)
                                            .update("one_to_one_ChatList", newList)
                                            .addOnSuccessListener {
                                                val dbRef = db.collection("OneToOneChat").document(randomId)
                                                    dbRef.get().addOnSuccessListener { document ->
                                                    if (document.exists()) {
                                                        Log.d("개인채팅 생성 실패", "실패")
                                                        dismiss()
                                                    } else {
                                                        db.collection("OneToOneChat")
                                                            .document(randomId)
                                                            .set(OneToOneChat(randomId,
                                                                    mutableListOf(recruiting.userID, userId.toString()),
                                                                    mutableListOf()
                                                                )
                                                            )
                                                            .addOnSuccessListener {
                                                                findNavController().navigate(R.id.action_findingTeamMembersFragment_to_chattingListFragment)
                                                                dismiss()
                                                            }
                                                    }
                                                }
                                            }
                                            .addOnFailureListener {
                                                dismiss()
                                            }
                                    }
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT)
                                        .show()
                                    dismiss()
                                }
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
                            dismiss()
                        }

                }
                .addOnFailureListener {
                    Toast.makeText(context, "인터넷 연결이 불안정합니다. 정보 저장에 실패했습니다.", Toast.LENGTH_SHORT)
                        .show()
                }

        }
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@RecruitingDialog, 0.8f, 0.7f)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun makeRandomTeamID(): String {
        val str = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
        var code = ""
        for (i in 1..10) code += str[(Math.random() * str.size).toInt()]
        return code
    }
}

fun Context.dialogFragmentResize(dialogFragment: DialogFragment, width: Float, height: Float) {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    if (Build.VERSION.SDK_INT < 30) {

        val display = windowManager.defaultDisplay
        val size = Point()

        display.getSize(size)

        val window = dialogFragment.dialog?.window

        val x = (size.x * width).toInt()
        val y = (size.y * height).toInt()
        window?.setLayout(x, y)

    } else {

        val rect = windowManager.currentWindowMetrics.bounds

        val window = dialogFragment.dialog?.window

        val x = (rect.width() * width).toInt()
        val y = (rect.height() * height).toInt()

        window?.setLayout(x, y)
    }

}

interface RecruitingDialogInterface {
    fun onYesButtonClick(id: Int)
}