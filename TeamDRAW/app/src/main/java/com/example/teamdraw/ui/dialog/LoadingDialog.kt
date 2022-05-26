package com.example.teamdraw.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.teamdraw.R

class LoadingDialog(context:Context) : Dialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)

        // 취소 불가능하게 함
        setCancelable(false)

        //
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }


}