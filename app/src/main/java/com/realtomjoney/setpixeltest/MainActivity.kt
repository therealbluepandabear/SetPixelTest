package com.realtomjoney.setpixeltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realtomjoney.pyxlmoose.fragments.canvas.CanvasFragment
import com.realtomjoney.setpixeltest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var canvasFragmentInstance: CanvasFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.undoButton.setOnClickListener {
            canvasFragmentInstance!!.myCanvasViewInstance.undo()
        }

        canvasFragmentInstance = CanvasFragment.newInstance(1000)
        supportFragmentManager.beginTransaction().add(R.id.canvasFrame, canvasFragmentInstance!!).commit()
    }
}