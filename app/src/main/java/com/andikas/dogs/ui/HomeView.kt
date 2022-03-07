package com.andikas.dogs.ui

import com.andikas.dogs.data.response.DogsResponseItem

interface HomeView {

    fun showDogs(dogs: List<DogsResponseItem>)

    fun refreshDogs()

    fun showShimmer()

    fun hideShimmer()

    fun showToast(msg: String)

}