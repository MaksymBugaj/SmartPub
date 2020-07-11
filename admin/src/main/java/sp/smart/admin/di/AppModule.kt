package sp.smart.admin.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import sp.smart.admin.db.CourseDao
import sp.smart.admin.db.SmartDatabase
import sp.smart.admin.repository.FirebaseRepository
import sp.smart.admin.repository.FirebaseRepositoryImpl
import sp.smart.admin.repository.SmartRepository
import sp.smart.admin.repository.SmartRepositoryImpl
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): SmartDatabase {
        return Room.databaseBuilder(
            app as Context, SmartDatabase::class.java,"smartAdmin.db"
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