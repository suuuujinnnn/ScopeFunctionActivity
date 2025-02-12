package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // You can test your helper functions by calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        Log.d("Function Output", "getTestDataArray: ${getTestDataArray()}")
        Log.d("Function Output", "averageLessThanMedian: ${averageLessThanMedian(listOf(1.0, 2.0, 3.0, 4.0, 5.0))}")
        Log.d("Function Output", "getView: ${getView(0, null, listOf(10, 20, 30), this)}")
    }

    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */

    // Return a list of random, sorted integers
    private fun getTestDataArray(): List<Int> =
        MutableList(10) { Random.nextInt() }.apply { sort() }

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean =
        listOfNumbers.sorted().run {
            val median = if (size % 2 == 0)
                (this[size / 2] + this[(size - 1) / 2]) / 2
            else
                this[size / 2]
            listOfNumbers.average() < median
        }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).also {
            it.text = collection[position].toString()
        }
}
