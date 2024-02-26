package com.serunews.newsapp.detail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.serunews.core.domain.model.IndoNews
import com.serunews.newsapp.R
import com.serunews.newsapp.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val detailIndoNews: IndoNews? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DATA, IndoNews::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DATA)
        }

        showDetailNewsIndo(detailIndoNews)
    }

    private fun showDetailNewsIndo(detailIndoNews: IndoNews?) {
        detailIndoNews?.let {
            supportActionBar?.title = detailIndoNews.judul
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            binding.apply {
                content.tvDateDetail.text = detailIndoNews.rilisberita
                content.tvDetailTitle.text = detailIndoNews.judul
                content.tvDetailDescription.text = detailIndoNews.headline
                content.tvDetailCategory.text = detailIndoNews.kategori
                content.tvDetailNewsbadge.text = detailIndoNews.news_badge
            }

            Glide.with(this@DetailActivity)
                .load(detailIndoNews.foto)
                .into(binding.ivDetailImage)

            var statusFavorite = detailIndoNews.isFavorite

            setStatusFavorite(statusFavorite)

            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteNewsIndo(detailIndoNews, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_star_filled
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_star_outline
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}