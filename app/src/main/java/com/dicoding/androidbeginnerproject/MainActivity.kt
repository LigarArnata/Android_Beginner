package com.dicoding.androidbeginnerproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var rvIdol: RecyclerView
    private val list = ArrayList<Idol>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvIdol = findViewById(R.id.rv_gg)
        rvIdol.setHasFixedSize(true)

        val btnAbout: Button = findViewById(R.id.btn_about)
        btnAbout.setOnClickListener(this)

        list.addAll(getListIdol())
        showRecyclerList()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_about -> {
                val moveIntent = Intent(this, About::class.java)
                startActivity(moveIntent)
            }
        }
    }

    private fun getListIdol(): ArrayList<Idol>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listIdol = ArrayList<Idol>()
        for (i in dataName.indices){
            val idol = Idol(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listIdol.add(idol)
        }
        return listIdol
    }

    private fun showSelectedIdol(idol: Idol){
        Toast.makeText(this, "Kamu memilih " + idol.name, Toast.LENGTH_SHORT).show()
    }

    private fun openDetailIdol(idol: Idol, detailClassName: String) {
        try {
            val intent = Intent(this, Class.forName(detailClassName))
            intent.putExtra("name", idol.name)
            intent.putExtra("description", idol.description)
            intent.putExtra("photo", idol.photo)
            startActivity(intent)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun showRecyclerList(){
        rvIdol.layoutManager = LinearLayoutManager(this)
        val listIdolAdapter = ListIdolAdapter(list)
        rvIdol.adapter = listIdolAdapter

        listIdolAdapter.setOnItemClickCallback(object : ListIdolAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Idol) {
                openDetailIdol(data,"com.dicoding.androidbeginnerproject.${data.name}Detail")
            }
        })
    }

}