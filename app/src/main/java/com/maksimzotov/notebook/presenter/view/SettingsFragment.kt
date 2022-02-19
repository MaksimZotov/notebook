package com.maksimzotov.notebook.presenter.view

import androidx.lifecycle.ViewModelProvider
import com.maksimzotov.notebook.databinding.FragmentSettingsBinding
import com.maksimzotov.notebook.di.main.appComponent
import com.maksimzotov.notebook.presenter.main.di.BaseViewModelFactory
import com.maksimzotov.notebook.presenter.main.view.BaseFragment
import com.maksimzotov.notebook.presenter.main.view.FragmentWithoutParamsForVM
import com.maksimzotov.notebook.presenter.viewmodel.SettingsViewModel
import com.maksimzotov.notebook.presenter.viewmodel.activity.MainViewModel
import javax.inject.Inject

class SettingsFragment: FragmentWithoutParamsForVM<SettingsViewModel, FragmentSettingsBinding>(
    SettingsViewModel::class.java,
    FragmentSettingsBinding::inflate
) {

    @Inject
    lateinit var activityViewModelFactory: BaseViewModelFactory<MainViewModel>
    private val activityViewModel: MainViewModel by lazy {
        ViewModelProvider(this, activityViewModelFactory).get(MainViewModel::class.java)
    }

    override fun inject() {
        requireContext().appComponent.inject(this)
    }

    override fun setupView() {
        with(binding) {
            switchDarkTheme.isChecked = activityViewModel.darkTheme.value
            switchBottomNavigation.isChecked = activityViewModel.bottomNavigation.value

            switchDarkTheme.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setDarkTheme(isChecked)
            }
            switchBottomNavigation.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setBottomNavigation(isChecked)
            }
        }
    }
}