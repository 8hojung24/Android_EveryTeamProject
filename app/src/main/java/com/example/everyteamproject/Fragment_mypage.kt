package com.example.everyteamproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everyteamproject.com.example.everyteamproject.*

class Fragment_mypage : Fragment() {


    private lateinit var button: Button
    private lateinit var recycler_view_main: RecyclerView
    var mDataBaseHandler: DataBaseHandler? = null

    //RecyclerView가 불러올 목록
    private var mAdapter: AdapterRecycler? = null
    private var projects: MutableList<project>? = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button = view.findViewById(R.id.button)
        recycler_view_main = view.findViewById(R.id.recycler_view_main)
        mDataBaseHandler = DataBaseHandler(context)
        mAdapter?.mDataBaseHandler = mDataBaseHandler as DataBaseHandler

        loadRecentDB()
        init()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view2 = inflater!!.inflate(R.layout.fragment_mypage, container, false)

        recycler_view_main = view2.findViewById(R.id.recycler_view_main)
        button = view2.findViewById(R.id.button)

        button.setOnClickListener {
            val intent = Intent(context, Registration::class.java)
            startActivity(intent)
        }
        return view2
    }


    fun loadRecentDB() {
        // 저장되어있던 DB를 가져온다.
        projects = mDataBaseHandler?.getProjectsList()
        if (mAdapter == null) {
            mAdapter = context?.let { AdapterRecycler(projects, it) }
            recycler_view_main.setHasFixedSize(true)
            recycler_view_main.setAdapter(mAdapter)
        }
    }

    // 추가 format example
    fun init() {
        mAdapter = context?.let { AdapterRecycler(projects, it) }
        mAdapter?.project = projects as MutableList<project>
        var linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recycler_view_main?.adapter = mAdapter
        recycler_view_main?.layoutManager = linearLayoutManager
    }

}

