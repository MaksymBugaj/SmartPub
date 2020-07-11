package sp.smart.admin.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import sp.smart.admin.viewmodel.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {
    //instance of viewmodelproviderfactory
    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory) : ViewModelProvider.Factory
}