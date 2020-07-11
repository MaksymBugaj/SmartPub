package sp.smart.smartpub.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sp.smart.smartpub.ui.menu.classic.ClassicMenu
import sp.smart.smartpub.ui.menu.classic.all.AllFragment
import sp.smart.smartpub.ui.menu.classic.appetizers.AppetizersFragment
import sp.smart.smartpub.ui.menu.classic.drinks.DrinksFragment
import sp.smart.smartpub.ui.menu.classic.fishes.FishFragment
import sp.smart.smartpub.ui.menu.classic.horizontalscrollviews.HorizontalFragment
import sp.smart.smartpub.ui.menu.classic.maincourses.MainCoursesFragment
import sp.smart.smartpub.ui.menu.classic.soups.SoupsFragment
import sp.smart.smartpub.ui.menu.swipe.SwipeFragment
import sp.smart.smartpub.ui.tablepicker.TablePicker

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeClassicMenuFragment(): ClassicMenu

    @ContributesAndroidInjector
    abstract fun contributeAllFragment(): AllFragment

    @ContributesAndroidInjector
    abstract fun contributeHorizontalFragment(): HorizontalFragment

    @ContributesAndroidInjector
    abstract fun contributeAppetizersFragment(): AppetizersFragment

    @ContributesAndroidInjector
    abstract fun contributeSoupsFragment(): SoupsFragment

    @ContributesAndroidInjector
    abstract fun contributeMainCoursesFragment(): MainCoursesFragment

    @ContributesAndroidInjector
    abstract fun contributeFishFragmentFragment(): FishFragment

    @ContributesAndroidInjector
    abstract fun contributeDrinksFragmentFragment(): DrinksFragment
}