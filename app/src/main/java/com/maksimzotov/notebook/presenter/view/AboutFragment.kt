package com.maksimzotov.notebook.presenter.view

import com.maksimzotov.notebook.databinding.FragmentAboutBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.domain.entities.response.Status
import com.maksimzotov.notebook.presenter.adapters.ItemsAboutAdapter
import com.maksimzotov.notebook.presenter.main.util.OnItemClickListener
import com.maksimzotov.notebook.presenter.main.view.FragmentWithoutParamsForVM
import com.maksimzotov.notebook.presenter.parcelable.mapToParcelable
import com.maksimzotov.notebook.presenter.viewmodel.AboutViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class AboutFragment: FragmentWithoutParamsForVM<AboutViewModel, FragmentAboutBinding>(
    AboutViewModel::class.java,
    FragmentAboutBinding::inflate
), OnItemClickListener {

    private lateinit var adapter: ItemsAboutAdapter

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun setupView() {
        adapter = ItemsAboutAdapter(emptyList(), this)
        binding.itemsAbout.adapter = adapter
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.itemsAbout
            .filter { response ->
                response.status == Status.SUCCESS
            }.map { response ->
                response.data ?: emptyList()
            }.observe { list ->
                adapter.setData(list)
            }
    }

    override fun onItemClick(position: Int) {
        val itemAbout = adapter.itemsAbout[position]
        val action = AboutFragmentDirections.actionAboutFragmentToItemAboutDetailsFragment(
            itemAbout.mapToParcelable()
        )
        navController.navigate(action)
    }
}