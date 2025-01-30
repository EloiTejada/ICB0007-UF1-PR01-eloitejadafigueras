package com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras.ui.theme.Rocket
import com.eloitejada.icb0007_uf1_pr01_eloitejadafigueras.ui.theme.RocketAPIService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RocketListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_rocket_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        GlobalScope.launch {
            val rocketService = getRetrofit().create(RocketAPIService::class.java).getRockets().execute()
            val rocketList = rocketService.body() as List<Rocket>
            rocketList.forEach {
                println(it.name)
            }
        }
    }

}