package sp.smart.smartpub.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import sp.smart.smartpub.viewmodel.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {
    //instance of viewmodelproviderfactory
    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory) : ViewModelProvider.Factory
}