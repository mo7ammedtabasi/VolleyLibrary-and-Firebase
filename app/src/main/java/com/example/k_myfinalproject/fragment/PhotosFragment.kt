package com.example.k_myfinalproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.k_myfinalproject.R
import com.example.k_myfinalproject.adapter.AdapterPhotos
import com.example.k_myfinalproject.adapter.AdapterPosts
import com.example.k_myfinalproject.databinding.FragmentPhotosBinding
import com.example.k_myfinalproject.databinding.FragmentPostsBinding
import com.example.k_myfinalproject.model.Photos
import com.example.k_myfinalproject.model.Posts
import org.json.JSONArray


class PhotosFragment : Fragment() {

    private lateinit var binding: FragmentPhotosBinding
    private lateinit var arrayPhotos:ArrayList<Photos>
    private lateinit var viewAdapter: AdapterPhotos
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPhotosBinding.inflate(inflater,container,false)

        arrayPhotos = ArrayList()
        getAllPosts()

        return binding.root
    }

    private fun getAllPosts(){
        val url = "https://jsonplaceholder.typicode.com/photos"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,url,null,
            { response : JSONArray ->

                for (i in 0 until response.length()){
                    val postJsonObject = response.getJSONObject(i)
                    val albumId = postJsonObject.getInt("albumId")
                    val id = postJsonObject.getInt("id")
                    val title = postJsonObject.getString("title")
                    val url = postJsonObject.getString("url")
                    val thumbnailUrl = postJsonObject.getString("thumbnailUrl")

                    arrayPhotos.add(Photos(albumId,id,title,url,thumbnailUrl))
                }
                viewAdapter = AdapterPhotos(requireContext(),arrayPhotos)
                viewManager =GridLayoutManager(requireContext(),2)
                binding.recyclerviewPhotos.apply {
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