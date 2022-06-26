package com.example.k_myfinalproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.k_myfinalproject.R
import com.example.k_myfinalproject.adapter.AdapterPosts
import com.example.k_myfinalproject.adapter.AdapterToDos
import com.example.k_myfinalproject.databinding.FragmentPostsBinding
import com.example.k_myfinalproject.databinding.FragmentToDoBinding
import com.example.k_myfinalproject.model.Posts
import com.example.k_myfinalproject.model.Todos
import org.json.JSONArray


class ToDoFragment : Fragment() {
    private lateinit var binding: FragmentToDoBinding
    private lateinit var arrayToDo:ArrayList<Todos>
    private lateinit var viewAdapter: AdapterToDos
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentToDoBinding.inflate(inflater,container,false)
        arrayToDo = ArrayList()
        getAllPosts()

        return binding.root
    }

    private fun getAllPosts(){
        val url = "https://jsonplaceholder.typicode.com/todos"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,url,null,
            { response : JSONArray ->

                for (i in 0 until response.length()){
                    val postJsonObject = response.getJSONObject(i)
                    val userId = postJsonObject.getInt("userId")
                    val id = postJsonObject.getInt("id")
                    val title = postJsonObject.getString("title")
                    val body = postJsonObject.getBoolean("completed")
                    arrayToDo.add(Todos(userId,id,title,body))
                }
                viewAdapter = AdapterToDos(requireContext(),arrayToDo)
                viewManager = LinearLayoutManager(requireContext())
                binding.recyclerviewToDo.apply {
                    adapter = viewAdapter
                    layoutManager = viewManager
                }
            },
            { error ->
                Toast.makeText(requireContext(),"Error : ${error.message}", Toast.LENGTH_SHORT).show()
            })
        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(jsonArrayRequest)

    }

}