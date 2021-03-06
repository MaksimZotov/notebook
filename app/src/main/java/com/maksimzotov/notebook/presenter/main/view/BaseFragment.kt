package com.maksimzotov.notebook.presenter.main.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

abstract class BaseFragment<VM: BaseViewModel, VB: ViewBinding>(
    private val viewModelType: Class<VM>,
    private val inflateBinding: (LayoutInflater, ViewGroup?, Boolean) -> VB
): Fragment() {

    protected abstract fun inject()

    protected abstract val viewModel: VM

    private var _binding: VB? = null
    protected val binding get() = checkNotNull(_binding)

    private val navController by lazy {
        findNavController()
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun setupView()

    protected open fun observeViewModel() {
        with(viewModel) {
            showShortToastFlow.observe { shortToast ->
                Toast.makeText(context, shortToast.text, Toast.LENGTH_SHORT).show()
            }
            showLongToastFlow.observe { longToast ->
                Toast.makeText(context, longToast.text, Toast.LENGTH_LONG).show()
            }
            popBackStackFlow.observe {
                navController.popBackStack()
            }
            navigateFlow.observe { navigateAction ->
                navController.navigate(navigateAction.action)
            }
        }
    }

    protected fun <T> Flow<T>.observe(collector: FlowCollector<T>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@observe.collect(collector)
            }
        }
    }
}