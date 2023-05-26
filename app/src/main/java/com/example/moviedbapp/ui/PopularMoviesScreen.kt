package com.example.moviedbapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedbapp.R
import com.example.moviedbapp.adapter.PopularMoviesAdapter
import com.example.moviedbapp.databinding.FragmentPopularMoviesScreenBinding
import com.example.moviedbapp.viewmodel.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PopularMoviesScreen : Fragment() {
   private lateinit var _binding: FragmentPopularMoviesScreenBinding
   private val binding get() = _binding
    private val viewModel: PopularMoviesViewModel by viewModels()
    private val movieAdapter by lazy { PopularMoviesAdapter() }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPopularMoviesScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            /** to update the adapter when there's a new list **/
            lifecycleScope.launchWhenCreated {
                viewModel.moviesList.collect {
                    movieAdapter.submitData(it)
                }
            }
        }

        /** to show progressBar and loading text before loading the list **/
        lifecycleScope.launchWhenCreated {
            movieAdapter.loadStateFlow.collect{
                val state = it.refresh
               binding.progressBar.isVisible = state is LoadState.Loading
               binding.statusTv.isVisible = state is LoadState.Loading
            }
        }


        /** to initialise the recyclerview **/
        binding.homepageRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }


        onBackPress()

    }


    /**Log out Alert Dialogue */
    private fun showLogOutAlert(){
        val dialogView = layoutInflater.inflate(R.layout.custom_logout_dialog_layout, null)
        val customDialog = activity?.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
                .show()
        }

        val btnLogOutDialog = dialogView.findViewById<Button>(R.id.fragment_quit_btn)
        btnLogOutDialog.setOnClickListener {
            customDialog?.dismiss()
            requireActivity().finish()
        }

        val btnCancelDialog = dialogView.findViewById<Button>(R.id.fragment_cancel_btn)
        btnCancelDialog.setOnClickListener {
            customDialog?.dismiss()
        }
    }



    /** to cancel or quit the app **/
    private fun onBackPress(){
        val callBack = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                showLogOutAlert()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callBack)
    }

}