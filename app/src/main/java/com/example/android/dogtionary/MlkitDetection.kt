package com.example.android.dogtionary

import android.graphics.Bitmap
import android.util.Log
import android.widget.TextView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class MlkitDetection {
    private fun analyze(imageBitmap: Bitmap, view: TextView) {
        val image = InputImage.fromBitmap(imageBitmap, 0)
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
        var text = ""
        var confidence = 0f

        labeler.process(image)
            .addOnSuccessListener { labels ->
                for (label in labels) {
                    text = label.text
                    confidence = label.confidence
                    Log.i("Frank", "LOOP>     [$text]:$confidence")
                }
                view.text = text
                view.text = confidence.toString()
                Log.i("ImageFrag", text + confidence)
            }
            .addOnFailureListener { e -> Log.d("ImageError", "Error with labeling: $e") }
    }
}