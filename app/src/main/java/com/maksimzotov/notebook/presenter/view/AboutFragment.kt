package com.maksimzotov.notebook.presenter.view

import com.maksimzotov.notebook.databinding.FragmentAboutBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.viewmodel.AboutViewModel

class AboutFragment: BaseFragment<AboutViewModel, FragmentAboutBinding>(
    AboutViewModel::class.java,
    FragmentAboutBinding::inflate
) {
    override fun inject() {
        requireContext().appComponent.inject(this)
    }
}