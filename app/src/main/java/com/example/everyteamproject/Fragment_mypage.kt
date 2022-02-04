package com.example.everyteamproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everyteamproject.com.example.everyteamproject.AdapterRecycler
import com.example.everyteamproject.databinding.FragmentMypageBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class Fragment_mypage : Fragment(), View.OnClickListener{

    private lateinit var fab2:FloatingActionButton
    private lateinit var recycler_view_main:RecyclerView
    private lateinit var binding : FragmentMypageBinding

    //RecyclerView가 불러올 목록
    private var adapter: AdapterRecycler?=null
    private var data:MutableList<Member>?=mutableListOf()
    var i = 1

    init {
        instance = this
    }

    companion object{
        private var instance:Fragment_mypage?=null
        fun getInstance(): Fragment_mypage? {
            return instance
        }
        fun newInstance(): Fragment_mypage {
            return Fragment_mypage()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(layoutInflater)
        val view2 = binding.root
        recycler_view_main = view2.findViewById(R.id.recycler_view_main)

        //initialize()

        //adapter = AdapterRecycler()

        if (data != null) {
            adapter?.listData = data as MutableList<Member>
        }

        binding.recyclerViewMain.adapter = adapter
        recycler_view_main.layoutManager= LinearLayoutManager(activity)

        //return inflater.inflate(R.layout.fragment_mypage, container, false)
        //val view: View = inflater!!.inflate(R.layout.fragment_mypage, container, false)
        fab2 = view2.findViewById(R.id.fab2)
        fab2.setOnClickListener(this)

        return view2
    }

    // 추가 format example
    fun init() {

        val item = Member()
        item.name = "Team Project $i"
        i++
        item.role = " xxx: 개발 \n xxx: 개발 \n xxx: 개발 \n xxx: 개발"
        item.day = "2022년 2월 7일"
        item.time = "7시 30분" + " 까지"
        data?.add(item)
        adapter?.notifyDataSetChanged()


        adapter = AdapterRecycler(this)
        adapter!!.listData = data as ArrayList<Member>
        var linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation=RecyclerView.VERTICAL
        binding?.recyclerViewMain?.layoutManager = linearLayoutManager
        binding?.recyclerViewMain?.adapter = adapter
    }

    /*
    private fun initialize(){
        with(data){
            this?.add(Member("TeamProject1역할: ~ \n 마감일: ~"))
            this?.add(Member("TeamProject2"))
            this?.add(Member("TeamProject3"))
        }
    }*/

    fun deleteMember(member:Member){
        data?.remove(member)
        adapter?.notifyDataSetChanged()
    }

    fun editMember(){
        val intent = Intent(getActivity(), Edit::class.java)
        startActivity(intent)
    }

    fun goActivity(){
        val intent = Intent(getActivity(), StudyGroup::class.java)
        startActivity(intent)
    }

    // 추가 버튼 클릭시 Registration 으로 이동
    override fun onClick(v: View?
    ) {
        when (v?.id) {
            R.id.fab2 -> {
                init()

                /*
                val string = "TeamProject$i"
                i++
                data?.add(Member(string))
                adapter?.notifyDataSetChanged()*/

                //val intent = Intent(getActivity(), Registration::class.java)
                //startActivity(intent)
            }
        }
    }
}
