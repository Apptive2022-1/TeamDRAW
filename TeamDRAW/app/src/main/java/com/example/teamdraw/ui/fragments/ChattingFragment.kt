package com.example.teamdraw.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.adapters.ChatAdapter
import com.example.teamdraw.databinding.FragmentChattingBinding
import com.example.teamdraw.models.Chat


class ChattingFragment : Fragment() {

    private lateinit var binding: FragmentChattingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChattingBinding.inflate(inflater, container, false)
        Log.i("##123","sda")
        val adapter = ChatAdapter()
        adapter.setList(
            listOf(
                Chat(0, "안녕하세요 글 보고 연락드렸습니다.", "오후 12:40", false),
                Chat(1, "혹시 개발자로 참여할 수 있을까요?", "오후 12:40", false),
                Chat(2, "안녕하세요 네! 가능합니다", "오후 12:43", true)
            )
        )


        binding.chatRecyclerview.adapter = adapter
        binding.chatRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

}