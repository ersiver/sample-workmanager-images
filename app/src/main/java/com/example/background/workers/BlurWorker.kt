package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import com.example.background.PROGRESS
import timber.log.Timber

/**
 * BlurWorker contains the code to blur an image.
 */
class BlurWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val appContext = applicationContext
        val resourceUri = inputData.getString(KEY_IMAGE_URI)

        makeStatusNotification("Blurring image", appContext)

        //sleep()

        //* Simulate some lengthy process in our doWork() function so that
        // it can publish progress information over a defined amount of time.
        //The change here is to swap a single delay with 10 smaller ones,
        // setting a new progress at each iteration:
        (0..100 step 10).forEach {
            setProgressAsync(workDataOf(PROGRESS to it))
            sleep()
        }

        return try {

            if (TextUtils.isEmpty(resourceUri)) {
                Timber.e("Invalid input uri")
                throw IllegalArgumentException("Invalid input uri")
            }

            val respolver = appContext.contentResolver

            val bitmap = BitmapFactory.decodeStream(
                    respolver.openInputStream(Uri.parse(resourceUri)))

            //Get a blurred version of the bitmap
            val output = blurBitmap(bitmap, appContext)

            // Write bitmap to a temp file
            val outputUri = writeBitmapToFile(appContext, output)

            makeStatusNotification("Output is $outputUri", appContext)

            val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())

            Result.success(outputData)

        } catch (t: Throwable) {
            Timber.e(t, "Error applying blur")
            Result.failure()
        }
    }
}