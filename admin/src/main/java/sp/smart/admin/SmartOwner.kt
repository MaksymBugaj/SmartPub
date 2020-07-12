package sp.smart.admin

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import sp.smart.admin.di.DaggerAppComponent
import timber.log.Timber

class SmartOwner : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        Stetho.initializeWithDefaults(this)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        return DaggerAppComponent.builder().application(this).build()
    }
}