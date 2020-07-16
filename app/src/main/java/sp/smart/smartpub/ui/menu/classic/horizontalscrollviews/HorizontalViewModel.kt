package sp.smart.smartpub.ui.menu.classic.horizontalscrollviews

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sp.smart.smartpub.data.db.entity.Course
import sp.smart.smartpub.data.repository.SmartRepository
import javax.inject.Inject

class HorizontalViewModel @Inject constructor(
    private val smartRepository: SmartRepository
): ViewModel() {

    private val _firestoreDataStatus = MutableLiveData<FirebaseDataStatus>()
    val firestoreDataStatus: LiveData<FirebaseDataStatus> = _firestoreDataStatus

    fun onVisible() {
        viewModelScope.launch(Dispatchers.IO) {
            val fetched = smartRepository.fetchDataFromServer()
            if(fetched) {
                Log.d("NOPE","Fetched :) ")
                _firestoreDataStatus.postValue(DataDownloadSuccess)
            } else {
                Log.d("NOPE","Not Fetched :( ")
                _firestoreDataStatus.postValue(DataDownloadFailed)
            }
        }
    }
    var czujka6 = 0
    fun checkClicks(){
        Log.d("NOPE","Clicku clicku:  $czujka6")
        czujka6++
    }
}

sealed class FirebaseDataStatus

object DataDownloadSuccess : FirebaseDataStatus()
object DataDownloadFailed : FirebaseDataStatus()