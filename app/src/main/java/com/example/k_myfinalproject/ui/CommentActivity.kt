package com.example.k_myfinalproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.k_myfinalproject.adapter.AdapterComments
import com.example.k_myfinalproject.adapter.AdapterPosts
import com.example.k_myfinalproject.databinding.ActivityCommentBinding
import com.example.k_myfinalproject.model.Comments
import com.example.k_myfinalproject.model.Posts
import org.json.JSONArray

class CommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentBinding
    private lateinit var arrayComments:ArrayList<Comments>
    private lateinit var viewAdapter: AdapterComments
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("postId",0)
        if (id != null){
            getAllPosts(id)
        } else {
            Toast.makeText(this,"postId not found", Toast.LENGTH_SHORT).show()
        }

        arrayComments = ArrayList()


    }

    private fun getAllPosts(id :Int){
        val url = "https://jsonplaceholder.typicode.com/comments?postId=$id"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,url,null,
            { response : JSONArray ->

                for (i in 0 until response.length()){
                    val postJsonObject = response.getJSONObject(i)
                    val userId = postJsonObject.getInt("postId")
                    val id = postJsonObject.getInt("id")
                    val name = postJsonObject.getString("name")
                    val email = postJsonObject.getString("email")
                    val body = postJsonObject.getString("body")
                    arrayComments.add(Comments(userId,id,name,email,body))
                }
                viewAdapter = AdapterComments(this,arrayComments)
                viewManager = LinearLayoutManager(this)
                binding.recyclerviewComments.apply {
                    adapter = viewAdapter
                    layoutManager = viewManager
                }
            },
            { error ->
                Toast.makeText(this,"Error : ${error.message}", Toast.LENGTH_SHORT).show()
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonArrayRequest)

    }

}