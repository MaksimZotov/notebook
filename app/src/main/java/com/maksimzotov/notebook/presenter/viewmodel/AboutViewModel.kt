package com.maksimzotov.notebook.presenter.viewmodel

import com.maksimzotov.notebook.domain.entities.itemabout.ItemAbout
import com.maksimzotov.notebook.domain.entities.response.Response
import com.maksimzotov.notebook.domain.usecases.about.GetItemsAboutUseCase
import com.maksimzotov.notebook.presenter.main.viewmodel.BaseViewModel
import com.maksimzotov.notebook.presenter.parcelable.mapToParcelable
import com.maksimzotov.notebook.presenter.view.AboutFragmentDirections
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AboutViewModel @Inject constructor(
    getItemsAboutUseCase: GetItemsAboutUseCase
): BaseViewModel() {

    val itemsAbout: StateFlow<Response<List<ItemAbout>>> = getItemsAboutUseCase.getItemsAbout()
        .stateIn(Response.Loading())

    fun navigateToItemAboutDetails(itemAbout: ItemAbout) =
        navigate(AboutFragmentDirections.actionAboutFragmentToItemAboutDetailsFragment(
            itemAbout.mapToParcelable()
        ))
}