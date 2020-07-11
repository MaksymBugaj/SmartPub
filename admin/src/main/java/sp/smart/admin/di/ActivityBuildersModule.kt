package sp.smart.admin.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sp.smart.admin.OwnerActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : OwnerActivity
}