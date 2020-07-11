package sp.smart.smartpub

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import sp.smart.smartpub.di.DaggerAppComponent

class SmartPub : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        Stetho.initializeWithDefaults(this)
        return DaggerAppComponent.builder().application(this).build()
    }
}