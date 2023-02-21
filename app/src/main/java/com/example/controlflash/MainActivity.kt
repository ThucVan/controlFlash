package com.example.controlflash

import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.controlflash.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var cameraManager: CameraManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
                binding.btnTurnFlash.isEnabled = true
            }else{
                Toast.makeText(this, "No Flashlight Support !", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "No Camera Support !", Toast.LENGTH_SHORT).show()
        }

        binding.btnTurnFlash.setOnClickListener {
            cameraManager.setTorchMode("0", true)
            binding.btnTurnOffFlash.isEnabled = true
            binding.tvAlertFlash.text = this.getString(R.string.flashOn)
        }

        binding.btnTurnOffFlash.setOnClickListener {
            cameraManager.setTorchMode("0", false)
            it.isEnabled = false
            binding.tvAlertFlash.text = this.getString(R.string.flashOff)
        }
    }
}