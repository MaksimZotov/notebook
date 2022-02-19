package com.maksimzotov.notebook.presenter.view

import com.maksimzotov.notebook.databinding.FragmentSettingsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.main.view.FragmentWithoutParamsForVM
import com.maksimzotov.notebook.presenter.viewmodel.SettingsViewModel

class SettingsFragment: FragmentWithoutParamsForVM<SettingsViewModel, FragmentSettingsBinding>(
    SettingsViewModel::class.java,
    FragmentSettingsBinding::inflate
) {
    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun setupView() {
        with(binding) {
            darkTheme.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setDarkTheme(isChecked)
            }
            bottomNavigation.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setBottomNavigation(isChecked)
            }
        }
    }
}