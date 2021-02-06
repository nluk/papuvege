package pl.nluk.papuvege.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_delivery.*
import pl.nluk.papuvege.R

class DeliveryFragment : BaseFragment(), OnMapReadyCallback{

    private companion object {
        const val TRANSPARENT_GREEN = 0x66adeba9
        const val RADIUS_METERS = 4000.0
        val PAPUVEGE_LOCATION = LatLng(51.7697179, 19.4556709)
        const val ZOOM_LEVEL_DELIVERY = 12.0f
    }

    override val layoutId: Int = R.layout.fragment_delivery

    override fun onMapReady(map: GoogleMap) {
        CameraUpdateFactory.newLatLngZoom(PAPUVEGE_LOCATION, ZOOM_LEVEL_DELIVERY)
            .also { map.animateCamera(it) }
        MarkerOptions()
            .position(PAPUVEGE_LOCATION)
            .zIndex(ZOOM_LEVEL_DELIVERY)
            .title(getString(R.string.papuvege))
            .also { map.addMarker(it) }
        CircleOptions()
            .center(PAPUVEGE_LOCATION)
            .radius(RADIUS_METERS)
            .strokeColor(Color.RED)
            .fillColor(TRANSPARENT_GREEN)
            .also { map.addCircle(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val map = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        map.getMapAsync(this)
    }



}