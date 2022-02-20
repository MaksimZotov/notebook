package com.maksimzotov.notebook.presenter.view

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
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

    // ViewModel Initialization START **************************************
    private val args by navArgs<ItemAboutDetailsFragmentArgs>()
    private val itemAbout by lazy { args.itemAbout.mapToItemAbout() }

    @Inject
    lateinit var factory: ItemAboutDetailsViewModel.Factory.AssistedFactoryForVM
    override val viewModel by viewModels<ItemAboutDetailsViewModel> {
        factory.create(itemAbout)
    }
    // **************************************** ViewModel Initialization END

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun setupView() {
        with(binding) {
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean = false
            }
            webView.loadUrl(itemAbout.urlToWebPage)

            title.setOnLongClickListener {
                viewModel.showShortToast(title.text.toString())
                return@setOnLongClickListener true
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