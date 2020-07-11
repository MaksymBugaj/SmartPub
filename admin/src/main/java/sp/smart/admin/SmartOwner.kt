package sp.smart.admin

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import sp.smart.admin.di.DaggerAppComponent

class SmartOwner : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        Stetho.initializeWithDefaults(this)
        return DaggerAppComponent.builder().application(this).build()
    }
}