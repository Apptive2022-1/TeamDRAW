package com.example.teamdraw.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamdraw.adapters.ChatListAdapter
import com.example.teamdraw.adapters.ChatListListener
import com.example.teamdraw.databinding.FragmentChattingListBinding
import com.example.teamdraw.models.ChatList


class ChattingListFragment : Fragment() {

    private lateinit var binding: FragmentChattingListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChattingListBinding.inflate(inflater, container, false)

        val chatList = listOf<ChatList>(
            ChatList("신성현", "개발자", "넵 감사합니다!", "오후 12:40"),
            ChatList("정수현", "개발자", "내일까지 보내주세요", "오전 8:30"),
            ChatList("김재민", "디자이너", "네", "어제"),
            ChatList("주다온", "기획자", "알겠습니다", "6월 8일"),
            ChatList("이주빈", "디자이너", "아.. 네", "5월 25일"),
            ChatList("최이경", "개발자", "감사합니다", "4월 27일")
        )
        val adapter = ChatListAdapter(ChatListListener {
            findNavController().navigate(ChattingListFragmentDirections.actionChattingListFragmentToOneToOneChat())
        })
        adapter.setList(chatList)

        binding.chatListRecyclerview.adapter = adapter
        binding.chatListRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

}