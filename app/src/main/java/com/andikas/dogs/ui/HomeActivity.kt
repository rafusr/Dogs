package com.andikas.dogs.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.andikas.dogs.adapter.DogsAdapter
import com.andikas.dogs.data.response.DogsResponseItem
import com.andikas.dogs.databinding.ActivityHomeBinding
import com.andikas.dogs.utils.Extensions.makeToast
import com.facebook.shimmer.ShimmerFrameLayout

class HomeActivity : AppCompatActivity(), HomeView {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var presenter: HomePresenterImp
    private lateinit var container: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = HomePresenterImp(this)
        container = binding.shimmerDogs

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.root.setOnScrollChangeListener { _, _, _, _, _ ->
                binding.edtSearch.clearFocus()
            }
        }

        binding.btnRefresh.setOnClickListener {
            refreshDogs()
            binding.edtSearch.clearFocus()
        }

        refreshDogs()
    }

    override fun showDogs(dogs: List<DogsResponseItem>) {
        binding.rvShimmerDogs.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 3)
            setHasFixedSize(true)
            adapter = DogsAdapter()
            visibility = View.VISIBLE
        }
        binding.rvDogs.apply {
            layoutManager = GridLayoutManager(this@HomeActivity, 3)
            setHasFixedSize(true)
            adapter = DogsAdapter(dogs)
            visibility = View.VISIBLE
        }
    }

    override fun refreshDogs() {
        binding.rvDogs.visibility = View.GONE
        presenter.getDogs()
    }

    override fun showShimmer() {
        runOnUiThread {
            container.apply {
                startShimmer()
                visibility = View.VISIBLE
            }
        }
    }

    override fun hideShimmer() {
        runOnUiThread {
            if (container.isShimmerStarted) {
                container.apply {
                    stopShimmer()
                    visibility = View.GONE
                }
            }
        }
    }

    override fun showToast(msg: String) {
        runOnUiThread {
            makeToast(msg)
        }
    }

    override fun onResume() {
        super.onResume()
        refreshDogs()
    }
}