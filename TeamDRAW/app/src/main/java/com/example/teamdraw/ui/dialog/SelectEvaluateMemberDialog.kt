package com.example.teamdraw.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.teamdraw.R

class SelectEvaluateMemberDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_select_evaluate_member)

    }
}