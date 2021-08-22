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
import com.google.android.gms.maps.model.MapStyleOptions

class MapsFragment private constructor() : Fragment(), OnMapReadyCallback {

    companion object {
        private const val GRAY_MAP_ID = "5501de6188230cb1"
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
        val options = MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.dark_map_style)
        map.setMapStyle(options)
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