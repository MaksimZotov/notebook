package com.maksimzotov.notebook.presenter.view

import android.os.Bundle
import android.view.View
import com.maksimzotov.notebook.databinding.FragmentAboutBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.adapters.ItemsAboutAdapter
import com.maksimzotov.notebook.presenter.main.util.OnItemClickListener
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.parcelable.mapToParcelable
import com.maksimzotov.notebook.presenter.viewmodel.AboutViewModel

class AboutFragment: BaseFragment<AboutViewModel, FragmentAboutBinding>(
    AboutViewModel::class.java,
    FragmentAboutBinding::inflate
), OnItemClickListener {

    private lateinit var adapter: ItemsAboutAdapter

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = ItemsAboutAdapter(emptyList(), this)
        binding.itemsAbout.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val itemAbout = adapter.itemsAbout[position]
        val action = AboutFragmentDirections.actionAboutFragmentToItemAboutDetailsFragment(
            itemAbout.mapToParcelable()
        )
        navController.navigate(action)
    }
}