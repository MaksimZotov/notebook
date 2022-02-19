package com.maksimzotov.notebook.presenter.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.maksimzotov.notebook.presenter.main.di.BaseViewModelFactory
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class FragmentWithoutParamsForVM<VM: BaseViewModel, VB: ViewBinding>(
    viewModelType: Class<VM>,
    inflateBinding: (LayoutInflater, ViewGroup?, Boolean) -> VB
): BaseFragment<VM, VB>(viewModelType, inflateBinding) {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<VM>
    override val viewModel: VM by lazy {
        ViewModelProvider(this, viewModelFactory).get(viewModelType)
    }
}