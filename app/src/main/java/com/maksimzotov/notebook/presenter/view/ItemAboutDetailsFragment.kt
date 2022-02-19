package com.maksimzotov.notebook.presenter.view

import androidx.navigation.fragment.navArgs
import com.maksimzotov.notebook.databinding.FragmentItemAboutDetailsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.main.view.FragmentWithoutParamsForVM
import com.maksimzotov.notebook.presenter.viewmodel.ItemAboutDetailsViewModel

class ItemAboutDetailsFragment: FragmentWithoutParamsForVM
<ItemAboutDetailsViewModel, FragmentItemAboutDetailsBinding>(
    ItemAboutDetailsViewModel::class.java,
    FragmentItemAboutDetailsBinding::inflate
) {
    private val args by navArgs<ItemAboutDetailsFragmentArgs>()
    private val itemAbout by lazy { args.itemAbout.mapToItemAbout() }

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun setupView() {
        binding.webView.loadUrl(itemAbout.urlToImage)
        viewModel.showShortToast(itemAbout.title)
    }
}