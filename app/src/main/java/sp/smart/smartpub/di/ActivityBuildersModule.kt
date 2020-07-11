package sp.smart.smartpub.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sp.smart.smartpub.ui.MainActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : MainActivity
}