package com.serunews.newsapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.serunews.core.ui.NewsIndoAdapter
import com.serunews.newsapp.R
import com.serunews.newsapp.databinding.FragmentHomeBinding
import com.serunews.newsapp.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding?.run { this }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val newsIndoAdapter = NewsIndoAdapter()
            newsIndoAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.newsIndo.observe(viewLifecycleOwner) { newsIndo ->
                if (newsIndo != null) {
                    when (newsIndo) {
                        is com.serunews.core.source.Resource.Loading -> binding!!.progressBar.visibility =
                            View.VISIBLE

                        is com.serunews.core.source.Resource.Success -> {
                            binding!!.progressBar.visibility = View.GONE
                            newsIndoAdapter.setData(newsIndo.data)
                        }

                        is com.serunews.core.source.Resource.Error -> {
                            binding!!.progressBar.visibility = View.GONE
                            binding!!.viewError.root.visibility = View.VISIBLE
                            binding!!.viewError.tvError.text =
                                newsIndo.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            binding?.let {
                with(it.rvNews) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = newsIndoAdapter
                    setHasFixedSize(true)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}