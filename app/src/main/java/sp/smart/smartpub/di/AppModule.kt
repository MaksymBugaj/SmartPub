package sp.smart.smartpub.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import sp.smart.smartpub.data.repository.SmartRepository
import sp.smart.smartpub.data.repository.SmartRepositoryImpl
import sp.smart.smartpub.data.db.CourseDao
import sp.smart.smartpub.data.db.SmartDatabase
import sp.smart.smartpub.data.repository.FirebaseRepository
import sp.smart.smartpub.data.repository.FirebaseRepositoryImpl
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): SmartDatabase {
        return Room.databaseBuilder(
            app as Context, SmartDatabase::class.java,"smart.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCourseDao(db: SmartDatabase): CourseDao {
        return db.courseDao()
    }

    @Singleton
    @Provides
    fun provideSmartRepository(courseDao: CourseDao, firebaseRepository: FirebaseRepository): SmartRepository {
        return SmartRepositoryImpl(courseDao, firebaseRepository)
    }

    @Singleton
    @Provides
    fun provideFirebaseRepository(): FirebaseRepository{
        return FirebaseRepositoryImpl()
    }
}