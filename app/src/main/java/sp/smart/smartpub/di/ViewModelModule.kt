package sp.smart.smartpub.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import sp.smart.smartpub.ui.menu.classic.ClassicMenuViewModel
import sp.smart.smartpub.ui.menu.classic.all.AllViewModel
import sp.smart.smartpub.ui.menu.classic.appetizers.AppetizersViewModel
import sp.smart.smartpub.ui.menu.classic.drinks.DrinksViewModel
import sp.smart.smartpub.ui.menu.classic.fishes.FishViewModel
import sp.smart.smartpub.ui.menu.classic.horizontalscrollviews.HorizontalViewModel
import sp.smart.smartpub.ui.menu.classic.maincourses.MainCoursesViewModel
import sp.smart.smartpub.ui.menu.classic.soups.SoupsViewModel
import sp.smart.smartpub.ui.menu.swipe.SwipeViewModel
import sp.smart.smartpub.ui.tablepicker.TablePickerViewModel
import sp.smart.smartpub.viewmodel.ViewModelProviderFactory

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(ClassicMenuViewModel::class)
    abstract fun bindClassicMenuViewModel(classicMenuViewModel: ClassicMenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AllViewModel::class)
    abstract fun bindAllViewModel(allViewModel: AllViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HorizontalViewModel::class)
    abstract fun bindHorizontalViewModel(horizontalViewModel: HorizontalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppetizersViewModel::class)
    abstract fun bindAppetizersViewModel(appetizersViewModel: AppetizersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SoupsViewModel::class)
    abstract fun bindSoupsViewModel(soupsViewModel: SoupsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainCoursesViewModel::class)
    abstract fun bindMainCoursesViewModel(mainCoursesViewModel: MainCoursesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FishViewModel::class)
    abstract fun bindFishViewModel(fishViewModel: FishViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DrinksViewModel::class)
    abstract fun bindDrinksViewModel(drinksViewModel: DrinksViewModel): ViewModel

   /* @Binds
    @IntoMap
    @ViewModelKey(SwipeViewModel::class)
    abstract fun bindSwipeMenuViewModel(swipeViewModel: SwipeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TablePickerViewModel::class)
    abstract fun bindTablePickerViewModel(tablePickerViewModel: TablePickerViewModel): ViewModel*/


}