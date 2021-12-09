package koin

import com.example.testidp.features.mappers.*
import com.example.testidp.features.recycler.RecyclerFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RecyclerFragmentViewModel() }
    viewModel { MappersViewModel(get()) }

    single<List<Mapper<*,*>>> { listOf(
        UserMapper(),
        CarMapper(),
        DogMapper()
    ) }

    single { MapperDelegate.Builder().registerMapper(get()).build() }
    single<UserDataSource> { UserDataSourceImpl() }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

}