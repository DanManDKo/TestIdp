package koin

import com.example.testidp.features.recycler.RecyclerFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RecyclerFragmentViewModel() }
}