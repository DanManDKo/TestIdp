package com.example.testidp.maps

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.testidp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapsFragment private constructor() : Fragment(), OnMapReadyCallback {

    companion object {
        fun newInstance() = MapsFragment()
    }

    private var map: GoogleMap? = null
    private var requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            onBothPermissionsGrunted()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_maps, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(view)
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        checkPermissions()
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun setupUi(view: View) {
        setupMap()
    }

    private fun setupMap() {
        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager.beginTransaction()
            .replace(R.id.maoContainer, mapFragment)
            .commit()
        mapFragment.getMapAsync(this)
    }

    private fun checkPermissions() {

    }

    private fun onBothPermissionsGrunted() {
        map?.isMyLocationEnabled = true
    }
}