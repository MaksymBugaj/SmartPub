package sp.smart.admin.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import sp.smart.admin.ui.add.AddCourseFragment
import sp.smart.admin.ui.add.AddCourseViewModel

@Module
abstract class AddCourseModule {

    @ContributesAndroidInjector
    abstract fun contributeAddCourseFragment(): AddCourseFragment


    @Binds
    @IntoMap
    @ViewModelKey(AddCourseViewModel::class)
    abstract fun bindAddCourseViewModel(addCourseViewModel: AddCourseViewModel) : ViewModel
}