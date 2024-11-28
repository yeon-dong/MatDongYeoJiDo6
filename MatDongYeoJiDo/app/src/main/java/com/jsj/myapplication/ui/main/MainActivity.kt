package com.jsj.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.jsj.myapplication.R
import com.jsj.myapplication.data.database.AppDataBase
import com.jsj.myapplication.data.model.Place
import com.jsj.myapplication.data.repository.PlaceRepository
import com.jsj.myapplication.databinding.ActivityMainBinding
import com.jsj.myapplication.ui.list.PlaceListActivity
import com.jsj.myapplication.utill.csvConverter
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navermap : NaverMap
    private var allMarker = mutableListOf<Place>()
//    private lateinit var repository: PlaceRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val db = AppDataBase.getDatabase(this)
//        repository = PlaceRepository(db)
//
//
//        lifecycleScope.launch {
//            val places = csvConverter.readCSV(this@MainActivity)
//            repository.insertPlaces(places)
//
//            // DB에서 데이터 읽기
//            val savedRestaurants = repository.getAllPlaces()
//            savedRestaurants.forEach {
//                println("Restaurant: ${it.name}, ${it.location}")
//            }
//        }
        binding.button.setOnClickListener{
            getVisibleMarkers()
            Log.d("chkec","hey")
        }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                supportFragmentManager.beginTransaction().add(R.id.map_fragment, it).commit()
        }
        mapFragment.getMapAsync(this)
        binding.button.setOnClickListener{
            val intent = Intent(this, PlaceListActivity::class.java)
            val showingPlace = getVisibleMarkers()
            intent.putParcelableArrayListExtra("places",ArrayList(showingPlace) ) // 마커 정보 전달
            startActivity(intent)
            Log.d("chk","${ArrayList(showingPlace)}")
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.navermap = naverMap
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.481945, 126.879523))
        naverMap.moveCamera(cameraUpdate)
        lifecycleScope.launch {
            val places = csvConverter.readCSV(this@MainActivity)
            places.forEach{
                place -> addMarker(place.longitude, place.latitude)
            }
            allMarker = places.toMutableList()
        }
    }

    private fun addMarker(latitude: Double, longitude: Double){
        val marker = Marker()
        marker.position = LatLng(latitude,longitude)
        marker.map = navermap
    }
    private fun getVisibleMarkers(): List<Place> {
        val visiblePlaces = mutableListOf<Place>()

        // 카메라 뷰포트의 범위 (LatLngBounds)를 구합니다
        val bounds = navermap.coveringBounds

        allMarker.forEach { marker ->
            // 마커의 위치가 카메라 뷰포트 내에 있는지 확인
            val markerLat = marker.longitude
            val markerLong = marker.latitude
            val markerPos = LatLng(markerLat,markerLong)
            if (bounds.contains(markerPos)) {
                val place = marker as? Place
                place?.let {
                    visiblePlaces.add(it)
                }
            }
        }
        Log.d("visiblemarker",visiblePlaces.toString())
        return visiblePlaces
    }
}


