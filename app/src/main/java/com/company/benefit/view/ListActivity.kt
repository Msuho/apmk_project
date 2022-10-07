package com.company.benefit.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.benefit.adapter.ListAdapter
import com.company.benefit.databinding.ActivityListBinding
import com.company.benefit.model.ListInfo
import com.company.benefit.viewmodel.MainViewModel

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private val model : MainViewModel by viewModels()

    private lateinit var listAdapter: ListAdapter

    private val list : ArrayList<ListInfo> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.add(ListInfo("<정답>",60))
        list.add(ListInfo("<질문>",50))
        list.add(ListInfo("<시청>",100))
        list.add(ListInfo("<테스트>",300))


        recyclerInit()

    }


    private fun recyclerInit() {

        // adapter 설정
        listAdapter = ListAdapter(applicationContext).apply {

            listener = object : ListAdapter.OnItemClickListener{
                override fun onOptionItemClick(position: Int) {

                }

            }

        }

        binding.recyclerView.run {
            // setHasFixedSize 의 기능은 RecyclerView 의 크기 변경이 일정하다는 것을 사용자의 입력으로 확인한다. 항목의 높이나 너비가 변경되지 않으며, 추가 또는 제거된 모든 항목은 동일하다
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = listAdapter
        }

        // recyclerview 구분선 설정
        binding.recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, 1))

        // 어뎁터에 데이터 이동
        listAdapter.setItems(list)

    }
}