package sp.smart.admin.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sp.smart.admin.ui.add.AddCourseFragment
import sp.smart.admin.ui.all.AllCoursesFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAllCoursesFragment(): AllCoursesFragment


}