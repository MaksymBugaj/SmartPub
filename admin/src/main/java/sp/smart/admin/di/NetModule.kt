package sp.smart.admin.di

import android.app.Application
import dagger.Module
import dagger.Provides
import sp.smart.admin.network.ConnectivityInterceptor
import sp.smart.admin.network.ConnectivityInterceptorImpl
import javax.inject.Singleton

@Module
class NetModule {


    @Provides
    @Singleton
    fun provideConnectivityInterceptor(application: Application): ConnectivityInterceptor{
        return ConnectivityInterceptorImpl(application)
    }
}