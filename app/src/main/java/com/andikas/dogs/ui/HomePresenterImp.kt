package com.andikas.dogs.ui

import com.andikas.dogs.data.network.ApiClient
import com.andikas.dogs.data.response.DogsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class HomePresenterImp(private val view: HomeView) : HomePresenter, CoroutineScope {
    override fun getDogs() {
        view.showShimmer()
        launch(coroutineContext) {
            ApiClient.instance.searchImages(limit = 20)
                .enqueue(object : Callback<DogsResponse> {
                    override fun onResponse(
                        call: Call<DogsResponse>,
                        response: Response<DogsResponse>
                    ) {
                        if (response.code() == 200) {
                            val dogs = response.body()!!
                            if (dogs.isEmpty()) {
                                view.showToast("Dogs is empty")
                            } else {
                                view.showDogs(dogs)
                            }
                        }
                        view.hideShimmer()
                    }

                    override fun onFailure(call: Call<DogsResponse>, t: Throwable) {
                        view.showToast(t.message.toString())
                        view.hideShimmer()
                    }

                })
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}