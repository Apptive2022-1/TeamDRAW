package com.example.teamdraw.ui.dialog

import android.app.Dialog
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
import androidx.fragment.app.DialogFragment
import com.example.teamdraw.databinding.DialogRecruitingBinding
import com.example.teamdraw.models.Recruiting
import com.example.teamdraw.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class RecruitingDialog(recruitingDialogInterface: RecruitingDialogInterface, val recruiting : Recruiting) : DialogFragment() {

    // 뷰 바인딩 정의
    private var _binding: DialogRecruitingBinding? = null
    private val binding get() = _binding!!

    private var recruitingDialogInterface: RecruitingDialogInterface? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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



    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@RecruitingDialog, 0.8f, 0.7f)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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