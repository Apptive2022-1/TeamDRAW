package com.example.teamdraw.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.teamdraw.R
import com.example.teamdraw.databinding.FragmentChattingBinding
import com.example.teamdraw.databinding.FragmentMemberEvaluateBinding

class MemberEvaluateFragment : Fragment() {

    private lateinit var binding: FragmentMemberEvaluateBinding

    private var name : String? = null
    private var teamName : String? = null
    private var ischeck = mutableListOf<Boolean>(false,false,false,false,false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMemberEvaluateBinding.inflate(inflater, container, false)

        arguments?.let {
            name = it.getString("name")
            teamName = it.getString("teamName")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvN.text= teamName
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.textView1.text = name + "님은 어떤 팀원이었나요?"
        binding.tv2.text = name+ "님을 다시 만나고 싶으신가요?"

        binding.btnCircle1.setOnClickListener {
            binding.btnCircle1.setBackgroundResource(R.drawable.circle_check)
            binding.btnCircle2.setBackgroundResource(R.drawable.circle_no_check)
        }
        binding.btnCircle2.setOnClickListener {
            binding.btnCircle2.setBackgroundResource(R.drawable.circle_check)
            binding.btnCircle1.setBackgroundResource(R.drawable.circle_no_check)
        }

        binding.tv3.text = name + "님에게 한 마디!"
        binding.btnGo.setOnClickListener {
            Toast.makeText(requireContext(),name + "님 팀원평가 완료 !!", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }

        binding.btn1.setOnClickListener { 
            if(!ischeck[0]){
                ischeck[0] = true 
                binding.btn1.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else{
                ischeck[0] = false
                binding.btn1.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }

        binding.btn2.setOnClickListener {
            if(!ischeck[1]){
                ischeck[1] = true
                binding.btn2.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else{
                ischeck[1] = false
                binding.btn2.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        binding.btn3.setOnClickListener {
            if(!ischeck[2]){
                ischeck[2] = true
                binding.btn3.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else{
                ischeck[2] = false
                binding.btn3.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        binding.btn4.setOnClickListener {
            if(!ischeck[3]){
                ischeck[3] = true
                binding.btn4.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else{
                ischeck[3] = false
                binding.btn4.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        binding.btn5.setOnClickListener {
            if(!ischeck[4]){
                ischeck[4] = true
                binding.btn5.setBackgroundResource(R.drawable.frame_btn_selected2)
            }
            else{
                ischeck[4] = false
                binding.btn5.setBackgroundResource(R.drawable.frame_btn_notselected2)
            }
        }
        



    }


}
