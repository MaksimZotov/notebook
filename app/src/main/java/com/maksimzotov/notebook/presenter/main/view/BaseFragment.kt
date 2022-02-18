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
import com.google.android.material.snackbar.Snackbar
import com.maksimzotov.notebook.presenter.main.di.BaseViewModelFactory
import com.maksimzotov.notebook.presenter.main.util.Constants.EMPTY_STRING
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseFragment<VM: BaseViewModel, VB: ViewBinding>(
    private val viewModelType: Class<VM>,
    private val inflateBinding: (LayoutInflater, ViewGroup?, Boolean) -> VB
): Fragment() {

    abstract fun inject()

    @Inject
    lateinit open var viewModelFactory: BaseViewModelFactory<VM>
    protected open val viewModel: VM by lazy {
        ViewModelProvider(this, viewModelFactory).get(viewModelType)
    }

    private var _binding: VB? = null
    protected val binding get() = checkNotNull(_binding)

    private val navController by lazy {
        findNavController()
    }

    private val snackBar by lazy {
        Snackbar.make(binding.root, EMPTY_STRING, Snackbar.LENGTH_INDEFINITE)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun observeViewModel() {
        with(viewModel) {
            showShortToast.observe { shortToast ->
                Toast.makeText(context, shortToast.text, Toast.LENGTH_SHORT).show()
            }
            showLongToast.observe { longToast ->
                Toast.makeText(context, longToast.text, Toast.LENGTH_LONG).show()
            }
            navigate.observe { navigate ->
                navController.navigate(navigate.action)
            }
            popBackStack.observe {
                navController.popBackStack()
            }
            snackBarIsActive.observe { isActive ->
                if (isActive)
                    snackBar.show()
                else
                    snackBar.dismiss()
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