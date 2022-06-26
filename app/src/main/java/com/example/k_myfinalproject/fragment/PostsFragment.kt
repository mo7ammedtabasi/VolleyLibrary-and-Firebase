package com.example.k_myfinalproject.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.k_myfinalproject.adapter.AdapterPosts
import com.example.k_myfinalproject.databinding.FragmentPostsBinding
import com.example.k_myfinalproject.model.Posts
import org.json.JSONArray


class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding
    private lateinit var arrayPosts:ArrayList<Posts>
    private lateinit var viewAdapter:AdapterPosts
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPostsBinding.inflate(inflater,container,false)

        arrayPosts = ArrayList()
        getAllPosts()

        return binding.root
    }

    private fun getAllPosts(){
        val url = "https://jsonplaceholder.typicode.com/posts"
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET,url,null,
            { response : JSONArray->

                for (i in 0 until response.length()){
                    val postJsonObject = response.getJSONObject(i)
                    val userId = postJsonObject.getInt("userId")
                    val id = postJsonObject.getInt("id")
                    val title = postJsonObject.getString("title")
                    val body = postJsonObject.getString("body")
                    arrayPosts.add(Posts(userId,id,title,body))
                }
                viewAdapter = AdapterPosts(requireContext(),arrayPosts)
                viewManager = LinearLayoutManager(requireContext())
                binding.recyclerviewPosts.apply {
                    adapter = viewAdapter
                    layoutManager = viewManager
                }
            },
            { error ->
                Toast.makeText(requireContext(),"Error : ${error.message}",Toast.LENGTH_SHORT).show()
            })
        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(jsonArrayRequest)

    }

}