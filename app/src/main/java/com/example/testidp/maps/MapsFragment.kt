package com.example.testidp.maps

import android.Manifest
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.testidp.R
import com.example.testidp.utils.toPx
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsFragment private constructor() : Fragment(), OnMapReadyCallback {

    companion object {
        private const val LAT = 48.47060751061337
        private const val LNG = 35.03317067369574

        private const val MARKER_BITMAP_SIZE_IN_DP = 80

        fun newInstance() = MapsFragment()
    }

    private var map: GoogleMap? = null
    private var requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        }

    private var marker: Marker? = null
    private var markerClickCounter: Int = 0

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
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        showMarker(LAT, LNG)


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

    private fun showMarker(lat: Double, lng: Double) {
        val markerOptions = MarkerOptions()
            .position(LatLng(lat, lng))

        marker = map?.addMarker(markerOptions)
        marker?.let {
            map?.moveCamera(CameraUpdateFactory.newLatLngZoom(it.position, 17f))
        }
        map?.setOnMarkerClickListener { marker ->
            val bitmap = createBitmapWithDigit(markerClickCounter++)
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap))
            true
        }

    }

    private fun createBitmapWithDigit(digitToDraw: Int): Bitmap {
        val bitmapSizeInPx = MARKER_BITMAP_SIZE_IN_DP.toPx(requireContext())
        val digitSize = (MARKER_BITMAP_SIZE_IN_DP / 3).toPx(requireContext()).toFloat()
        val circleStrokeWidth = bitmapSizeInPx / 18.toFloat()
        val surfaceRadius = bitmapSizeInPx / 2.toFloat()
        val strokePadding = 4.toPx(requireContext())

        val surfacePaint = Paint().apply {
            color = Color.WHITE
            style = Paint.Style.FILL
            isAntiAlias = true
        }

        val strokePaint = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = circleStrokeWidth
            isAntiAlias = true
        }

        val digitPaint = Paint().apply {
            color = Color.RED
            isAntiAlias = true
            textSize = digitSize
            textAlign = Paint.Align.CENTER
        }
        val bitmap = Bitmap.createBitmap(
            bitmapSizeInPx,
            bitmapSizeInPx,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        canvas.drawCircle(
            bitmapSizeInPx / 2.toFloat(),
            bitmapSizeInPx / 2.toFloat(),
            surfaceRadius,
            surfacePaint
        )

        canvas.drawCircle(
            bitmapSizeInPx / 2.toFloat(),
            bitmapSizeInPx / 2.toFloat(),
            surfaceRadius - strokePadding,
            strokePaint
        )

        val x = canvas.width / 2.toFloat()
        val y = (canvas.height / 2 - (digitPaint.descent() + digitPaint.ascent()) / 2)
        canvas.drawText(digitToDraw.toString(), x, y, digitPaint)

        return bitmap
    }


}