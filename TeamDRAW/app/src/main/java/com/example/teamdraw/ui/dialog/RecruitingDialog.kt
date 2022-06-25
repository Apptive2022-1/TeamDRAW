package com.example.teamdraw.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.teamdraw.databinding.DialogRecruitingBinding

class RecruitingDialog(
    recruitingDialogInterface: RecruitingDialogInterface,
    text: String, id: Int
) : DialogFragment() {

    // 뷰 바인딩 정의
    private var _binding: DialogRecruitingBinding? = null
    private val binding get() = _binding!!

    private var recruitingDialogInterface: RecruitingDialogInterface? = null

    private var text: String? = null
    private var id: Int? = null

    init {
        this.text = text
        this.id = id
        this.recruitingDialogInterface = recruitingDialogInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogRecruitingBinding.inflate(inflater, container, false)
        val view = binding.root

        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

//        binding.confirmTextView.text = text
//
//        // 취소 버튼 클릭
//        binding.noButton.setOnClickListener {
//            dismiss()
//        }
//
//        // 확인 버튼 클릭
//        binding.yesButton.setOnClickListener {
//            this.recruitingDialogInterface?.onYesButtonClick(id!!)
//            dismiss()
//        }

        return view
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