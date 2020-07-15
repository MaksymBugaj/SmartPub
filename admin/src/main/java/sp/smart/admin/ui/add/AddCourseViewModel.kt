package sp.smart.admin.ui.add

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import sp.smart.admin.db.entity.Course
import sp.smart.admin.network.NetworkState
import sp.smart.admin.network.NoConnectivityException
import sp.smart.admin.repository.FirebaseStatus
import sp.smart.admin.repository.SmartRepository
import timber.log.Timber
import javax.inject.Inject

class AddCourseViewModel @Inject constructor(
    val smartRepository: SmartRepository
) : ViewModel() {

    val name = ObservableField<String>()
    val price = ObservableField<String>()
    val category = ObservableField<String>()
    val description = ObservableField<String>()
    val spinnerVisibility = ObservableField<Boolean>(false)
    private val JOB_TIMEOUT = 5000L

    private val _coursesState = MutableLiveData<CourseState>()
    val coursesState: LiveData<CourseState> = _coursesState

    fun onAddClick() {
        viewModelScope.launch(Dispatchers.IO) {
            if (name.get().isNullOrBlank() || price.get().isNullOrBlank() || category.get()
                    .isNullOrBlank() || description.get().isNullOrBlank()
            ) {
                _coursesState.postValue(EmptyFields)
            } else {
                spinnerVisibility.set(true)
                if (category.get() == "Main courses") category.set("main")
                val course = Course(
                    name = name.get()!!,
                    price = price.get()!!,
                    category = category.get()!!,
                    description = description.get()!!
                )
                smartRepository.insertCourse(course)
                val courseToFirestore = smartRepository.getCourseByName(course.name)
                val job = withTimeoutOrNull(JOB_TIMEOUT) {
                    val inserted = smartRepository.saveDataInFirestore(courseToFirestore)
                    if (inserted) {
                        Log.d("NOPE", "INSERTED")
                        _coursesState.postValue(CourseAdded)
                    } else {
                        Log.d("NOPE", "error")
                    }
                }

                if (job == null) {
                    _coursesState.postValue(InternetNotAvailable)
                }
            }
        }
    }

    fun onVisible() {
        viewModelScope.launch(IO) {
            val job = withTimeoutOrNull(JOB_TIMEOUT) {
                val downloaded = smartRepository.fetchDataFromServer()
                if (downloaded == FirebaseStatus.NOT_LOADED) _coursesState.postValue(InternetNotAvailable)
            }
            if (job == null) {
                _coursesState.postValue(InternetNotAvailable)
            }
        }
    }

    init {
        Log.d("NOPE", "Initialized")
    }

    //todo to be used in future?
    //todo https://stackoverflow.com/questions/42919621/how-to-check-internet-connection-in-dagger-2-module-for-okhttp3
    /*private val _networkState = MutableLiveData<NetworkState>()
    val returnNetworkState: LiveData<NetworkState>
        get() = _networkState
    fun networkData(){
        CoroutineScope(IO).launch {
            _networkState.postValue(NetworkState.LOADING)
            try {
                //Asynchronous Operation
                _networkState.postValue(NetworkState.LOADED)

            } catch (e: NoConnectivityException) { // custom exception class
                Log.d("NOPE", "No Internet Is Available")
                _networkState.postValue(NetworkState.ERROR)

            }
        }
    }*/
}

sealed class CourseState

object CourseAdded : CourseState()
object InternetNotAvailable : CourseState()
object CourseNotAdded : CourseState()
object EmptyFields : CourseState()