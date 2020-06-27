package com.example.background.workers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test

/**
 *  Test CleanupWorker, to check that it actually deletes the files.
 *  To do this,copy the test image on the device in the test,
 *  and then check if it's there after the CleanupWorker has been executed.
 */
class CleanupWorkerTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var wmRule = WorkManagerTestRule()

    @Test
    fun testCleanupWork() {
        val testUri = copyFileFromTestToTargetCtx(
                wmRule.testContext, wmRule.targetContext, "test_img.jpg")
        assertThat(uriFileExists(wmRule.targetContext, testUri.toString()), `is`(true))

        // Create request
        val request = OneTimeWorkRequestBuilder<CleanupWorker>().build()

        // Enqueue and wait for result. This also runs the Worker synchronously
        // because we are using a SynchronousExecutor.
        wmRule.workManager.enqueue(request).result.get()
        // Get WorkInfo
        val workInfo = wmRule.workManager.getWorkInfoById(request.id).get()

        // Assert
        assertThat(uriFileExists(wmRule.targetContext, testUri.toString()), `is`(false))
        assertThat(workInfo.state, `is`(WorkInfo.State.SUCCEEDED))
    }
}