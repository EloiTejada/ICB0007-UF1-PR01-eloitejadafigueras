package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RocketListFragment : Fragment() {

    lateinit var rocketAdapter : ListAdapter
    lateinit var rvRockets : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_rocket_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvRockets = view.findViewById(R.id.rv_rockets)
        rvRockets.layoutManager = LinearLayoutManager(requireContext())


        fetchRockets()
    }

    private fun fetchRockets() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getRockets()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val rocketList = response.body() ?: emptyList()
                        rocketAdapter = ListAdapter(rocketList)
                        rvRockets.adapter = rocketAdapter
                    } else {
                        Toast.makeText(requireContext(), "Failed to load rockets", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    object RetrofitInstance {
        private const val BASE_URL = "https://api.spacexdata.com/v4/"

        val api: RocketAPIService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RocketAPIService::class.java)
        }
    }
}

