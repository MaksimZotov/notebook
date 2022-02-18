package com.maksimzotov.notebook.presenter.view

import com.maksimzotov.notebook.databinding.FragmentSettingsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.viewmodel.SettingsViewModel

class SettingsFragment: BaseFragment<SettingsViewModel, FragmentSettingsBinding>(
    SettingsViewModel::class.java,
    FragmentSettingsBinding::inflate
) {
    override fun inject() {
        requireContext().appComponent.inject(this)
    }
}