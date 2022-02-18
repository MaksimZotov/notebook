package com.maksimzotov.notebook.presenter.view

import com.maksimzotov.notebook.databinding.FragmentItemAboutDetailsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.viewmodel.ItemAboutDetailsViewModel

class ItemAboutDetailsFragment: BaseFragment<ItemAboutDetailsViewModel, FragmentItemAboutDetailsBinding>(
    ItemAboutDetailsViewModel::class.java,
    FragmentItemAboutDetailsBinding::inflate
) {
    override fun inject() {
        requireContext().appComponent.inject(this)
    }
}