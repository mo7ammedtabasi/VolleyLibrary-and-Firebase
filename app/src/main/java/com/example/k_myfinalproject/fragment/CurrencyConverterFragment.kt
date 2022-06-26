package com.example.k_myfinalproject.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.k_myfinalproject.R
import com.example.k_myfinalproject.adapter.AdapterPhotos
import com.example.k_myfinalproject.databinding.FragmentCurrencyConverterBinding
import com.example.k_myfinalproject.databinding.FragmentPhotosBinding
import com.example.k_myfinalproject.model.Photos
import org.json.JSONObject


class CurrencyConverterFragment : Fragment() {

    private lateinit var binding: FragmentCurrencyConverterBinding
    private lateinit var amount1:String
    private lateinit var amount2:String
    private lateinit var languages: Array<out String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCurrencyConverterBinding.inflate(inflater,container,false)


        languages =  resources.getStringArray(R.array.amount)
        val arrayAdapter1= ArrayAdapter(requireContext(),android.R.layout.simple_expandable_list_item_1,languages)
        binding.spinner1.adapter = arrayAdapter1
        binding.spinner1.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                amount1 = languages[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        languages =  resources.getStringArray(R.array.amount)
        val arrayAdapter2= ArrayAdapter(requireContext(),android.R.layout.simple_expandable_list_item_1,languages)
        binding.spinner2.adapter = arrayAdapter2
        binding.spinner2.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                amount2 = languages[p2]

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.btnConvert.setOnClickListener {
            getAmount(amount1,amount2)
        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun getAmount(pAmount1:String, pAmount2:String){
        val url ="https://free.currconv.com/api/v7/convert?q=${amount1}_$amount2&compact=ultra&apiKey=785b1a0cf417d9cfb5d2"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,
            {response:JSONObject ->
                val amount=response.getDouble("${amount1}_$amount2")
                val num = binding.edEnterAmount.text.toString().trim().toDouble()
                 var result= amount * num
                    binding.tvResult.text = "result: $result"
//                    Toast.makeText(requireContext(),"result: $result",Toast.LENGTH_SHORT).show()

            },
            {error ->
                Toast.makeText(requireContext(),"Error: ${error.message}",Toast.LENGTH_SHORT).show()
            })

        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(jsonObjectRequest)
    }


}

