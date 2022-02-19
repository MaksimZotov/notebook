package com.maksimzotov.notebook.presenter.view

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.maksimzotov.notebook.databinding.FragmentItemAboutDetailsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.viewmodel.ItemAboutDetailsViewModel
import javax.inject.Inject

class ItemAboutDetailsFragment: BaseFragment
<ItemAboutDetailsViewModel, FragmentItemAboutDetailsBinding>(
    ItemAboutDetailsViewModel::class.java,
    FragmentItemAboutDetailsBinding::inflate
) {

    private val args by navArgs<ItemAboutDetailsFragmentArgs>()
    private val itemAbout by lazy { args.itemAbout.mapToItemAbout() }

    @Inject
    lateinit var factory: ItemAboutDetailsViewModel.Factory.AssistedFactoryForVM
    override val viewModel by viewModels<ItemAboutDetailsViewModel> {
        factory.create(itemAbout)
    }

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun setupView() {
        with(binding) {
            webView.loadUrl(itemAbout.urlToImage)
            title.setOnClickListener {
                viewModel.showShortToast(title.text.toString())
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        viewModel.itemAbout.observe { itemAbout ->
            binding.title.text = itemAbout.title
        }
    }
}