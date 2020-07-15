package sp.smart.admin.ui.test

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.test_fragment.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import sp.smart.admin.R
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis

class TestFragment : Fragment() {

    companion object {
        fun newInstance() = TestFragment()
    }

    private lateinit var viewModel: TestViewModel
    private lateinit var job: CompletableJob
    private val TAG: String = "TESTFRAGMENT"

    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0
    private val JOB_TIME = 4000 // ms
    private val JOB_TIMEOUT = 5000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)
        // TODO: Use the ViewModel

        job1_btn.setOnClickListener { startActivity(Intent(requireContext(), TestActivity::class.java)) }
        job2_btn.setOnClickListener {
            val dupa = makeComplement("ATCG")
        Log.d("CODECATA","dupa: $dupa")
        }
        job3_btn.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                setTextOnThreads1Test("DUPA")
            }
        }
        job4_btn.setOnClickListener { fake() }
        job5_btn.setOnClickListener { fake4() }
        job6_btn.setOnClickListener {
            if(!::job.isInitialized) initJob()
            job_progress_bar.startJobOrCancel(job)
        }
    }

    private fun setTextOn1Thread(input: String){
        job1_text.text = input
    }

    private fun setTextOn2Thread(input: String){
        job2_text.text = input
    }

    private suspend fun setTextOnThreads1Test(input: String){
        withContext(Dispatchers.Unconfined){
            Log.d("EPON","launch ojb1 in settext in thread: ${Thread.currentThread().name}")
            setTextOn1Thread(input)
        }
    }

    private fun setTextOnThreads2Test(input: String){
        setTextOn2Thread(input)
    }

    fun ProgressBar.startJobOrCancel(job: Job){
        if(this.progress >0){
            Log.d(TAG,"$job is already active")
            resetJob()
        } else {
            job6_btn.text = "CANCEL JOB!"
            CoroutineScope(IO + job).launch {
                Log.d(TAG, "coroutine ${this} is activated with job ${job}.")

                for (i in PROGRESS_START..PROGRESS_MAX){
                    delay((JOB_TIME / PROGRESS_MAX).toLong())
                    this@startJobOrCancel.progress = i
                }
                job3_text.text = "Job completed XD"
            }
        }
    }

    private fun resetJob(){
        if(job.isActive || job.isCompleted){
            job.cancel(CancellationException("Resseting job"))
        }
        initJob()
    }

    private fun initJob(){
        job3_text.text = "Started JOB"
        job = Job()
        job.invokeOnCompletion {
            it?.message.let {
                var msg = it
                if(msg.isNullOrBlank()){
                    msg = "Unknown cancellation"
                }
                Log.d(TAG,"$job was cancelled. Reason: $msg")
            }
        }
        job_progress_bar.max = PROGRESS_MAX
        job_progress_bar.progress = PROGRESS_START
    }



    private fun fake3() = CoroutineScope(IO).launch(){
        Log.d("EPON","Fake 3 thread test ${Thread.currentThread().name}")
    }

    private fun fake4() = CoroutineScope(newSingleThreadContext("DUPAAAA")).launch(){
        Log.d("EPON","Fake 4 thread test ${Thread.currentThread().name}")
    }

    private fun fake2(){

        CoroutineScope(IO).launch {
            val job = withTimeoutOrNull(JOB_TIMEOUT){

            }
        }


        CoroutineScope(IO).launch {
            val executionTime = measureTimeMillis {

                val result1: Deferred<String> = async {
                    Log.d("EPON","launch ojb1 in thread: ${Thread.currentThread().name}")
                    getResult1FromFake()
                }
                val result2: Deferred<String> = async {
                    Log.d("EPON","launch ojb2 in thread: ${Thread.currentThread().name}")
                    getResult2FromFake()
                }
                setTextOnThreads1Test(result1.await())
                setTextOnThreads2Test(result2.await())
            }
            Log.d("EPON","execTime $executionTime")
        }

    }

    private fun fake(){
        CoroutineScope(Default).launch {
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

    private fun fake5(){

    }

    private suspend fun getResult1FromFake():String{
        delay(1000)
        return "Result #1"
    }

    private suspend fun getResult2FromFake():String{
        delay(1700)
        return "Result #2"
    }

    fun cancelFirstJob() {
        val coroutineScope = CoroutineScope(Dispatchers.Unconfined)

        val job1 = coroutineScope.launch {
            this.cancel()
        }
        val job2 = coroutineScope.launch { }

        println("Job 1 state: ${job1.status()}")
        println("Job 2 state: ${job2.status()}")
        println("Parent job is active: ${coroutineScope.isActive}")
    }

    fun cancelParentJob() {
        val coroutineScope = CoroutineScope(Dispatchers.Unconfined)

        val job1 = coroutineScope.launch {
            delay(500)
        }
        val job2 = coroutineScope.launch {
            delay(500)
        }

        coroutineScope.cancel()

        println("Job 1 state: ${job1.status()}")
        println("Job 2 state: ${job2.status()}")
        println("Parent job is active: ${coroutineScope.isActive}")
    }

    fun Job.status(): String = when {
        isCancelled -> "cancelled"
        isActive -> "Active"
        isCompleted -> "Complete"
        else -> "Nothing"
    }

    fun makeComplement(dna : String) : String {
        val chars = CharArray(dna.length)
        for (i in dna.indices){
            when(dna[i]){
                'A'-> {chars[i] = 'T'
                }
                'T'-> {chars[i] = 'A'
                }
                'C'-> {chars[i] = 'G'
                }
                'G'-> { chars[i] = 'C'
                }
            }
        }

        return chars.joinToString("")
    }

}