package sp.smart.admin.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import sp.smart.admin.ui.add.AddCourseViewModel
import sp.smart.admin.ui.all.AllCoursesViewModel


@Module
abstract class ViewModelModule {




    @Binds
    @IntoMap
    @ViewModelKey(AllCoursesViewModel::class)
    abstract fun bindAllCoursesViewModel(allCoursesViewModel: AllCoursesViewModel): ViewModel


}