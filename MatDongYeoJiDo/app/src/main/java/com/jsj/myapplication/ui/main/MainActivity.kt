package com.jsj.myapplication.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.jsj.myapplication.R
import com.jsj.myapplication.data.database.AppDataBase
import com.jsj.myapplication.data.repository.PlaceRepository
import com.jsj.myapplication.databinding.ActivityMainBinding
import com.jsj.myapplication.utill.csvConverter
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navermap : NaverMap
    private lateinit var repository: PlaceRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDataBase.getDatabase(this)
        repository = PlaceRepository(db)

        // CSV 파일 읽어서 DB에 저장
        lifecycleScope.launch {
            val places = csvConverter.readCSV(this@MainActivity)
            repository.insertPlaces(places)

            // DB에서 데이터 읽기
            val savedRestaurants = repository.getAllPlaces()
            savedRestaurants.forEach {
                println("Restaurant: ${it.name}, ${it.location}")
            }
        }
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                supportFragmentManager.beginTransaction().add(R.id.map_fragment, it).commit()
        }
        mapFragment.getMapAsync(this)
        binding.button.setOnClickListener{
            //placeList로 이동
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.navermap = naverMap
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(37.481945, 126.879523))
        naverMap.moveCamera(cameraUpdate)
    }

    private fun addMarker(latitude: Double, longitude: Double){
        val marker = Marker()
        marker.position = LatLng(latitude,longitude)
        marker.map = navermap
    }
}