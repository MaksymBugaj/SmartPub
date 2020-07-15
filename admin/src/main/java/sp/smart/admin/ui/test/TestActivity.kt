package sp.smart.admin.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.test_fragment.*
import kotlinx.coroutines.*
import sp.smart.admin.R
import kotlin.system.measureTimeMillis

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        jobX1_btn.setOnClickListener {
            fake()
        }
    }

    private fun setTextOn1Thread(input: String){
        jobX_text.text = input
    }

    private fun setTextOn2Thread(input: String){
        jobX2_text.text = input
    }

    private suspend fun setTextOnThreads1Test(input: String){
        withContext(Dispatchers.Unconfined){
            Log.d("EPON","launch ojb1 in settext in thread: ${Thread.currentThread().name}")
            setTextOn1Thread(input)
        }
    }

    private suspend fun setTextOnThreads2Test(input: String){
        withContext(Dispatchers.Unconfined){
            setTextOn2Thread(input)
        }
    }

    private fun fake(){
        CoroutineScope(Dispatchers.Default).launch {
            val job1 = launch {
                val tami1 = measureTimeMillis {
                    Log.d("EPON","launch ojb1 in thread: ${Thread.currentThread().name}")
                    val result1 =getResult1FromFake()
                    setTextOnThreads1Test(result1)
                }
                Log.d("EPON","Result completed in tami1: $tami1")
            }
            job1.invokeOnCompletion {
                Log.d("EPON","job1 finished")

            }

            val job2 = launch {
                val tami1 = measureTimeMillis {
                    Log.d("EPON","launch ojb2 in thread: ${Thread.currentThread().name}")
                    val result1 =getResult2FromFake()
                    setTextOnThreads2Test(result1)
                }
                Log.d("EPON","Result completed in tami2: $tami1")
            }
        }
    }

    private suspend fun getResult1FromFake():String{
        delay(1000)
        return "Result #1"
    }

    private suspend fun getResult2FromFake():String{
        delay(1700)
        return "Result #2"
    }

}