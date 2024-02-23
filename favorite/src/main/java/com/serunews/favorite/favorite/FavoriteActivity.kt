package com.serunews.favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.serunews.core.ui.NewsIndoAdapter
import com.serunews.favorite.databinding.ActivityFavoriteBinding
import com.serunews.favorite.favorite.di.favoriteModule
import com.serunews.newsapp.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val newsIndoAdapter = NewsIndoAdapter()
        newsIndoAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteNewsTech.observe(this@FavoriteActivity) { dataNewsTech ->
            newsIndoAdapter.setData(dataNewsTech)
            binding.viewEmpty.root.visibility =
                if (dataNewsTech.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvNews) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsIndoAdapter
        }
    }
}