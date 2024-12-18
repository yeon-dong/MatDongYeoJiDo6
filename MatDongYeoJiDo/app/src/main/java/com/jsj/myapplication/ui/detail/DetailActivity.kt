package com.jsj.myapplication.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.jsj.myapplication.R
import com.jsj.myapplication.data.model.Place
import com.jsj.myapplication.databinding.ActivityDetailBinding
import com.jsj.myapplication.databinding.ActivityMainBinding
import com.jsj.myapplication.utill.csvConverter
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var navermap : NaverMap
    private var place : Place? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        place = intent.getParcelableExtra("place")
        binding.storeName.text = "${place?.name}"
        binding.menuAndPrice.text = "${place?.signature} ${place?.price}"
        binding.foodScore.text = "맛동연 점수 : ${place?.score}"
        binding.content.text ="${place?.comment}"

        // 링크 버튼 클릭 리스너 추가
        binding.linkButton.setOnClickListener {
            place?.link?.let { link ->
                openLink(link)
            }
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.view) as MapFragment?
            ?: MapFragment.newInstance().also {
                supportFragmentManager.beginTransaction().add(R.id.map_fragment, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.navermap = naverMap
        val cameraUpdate = CameraUpdate.scrollTo(LatLng(place!!.longitude, place!!.latitude))
        naverMap.moveCamera(cameraUpdate)
        lifecycleScope.launch {
            addMarker(place!!.longitude, place!!.latitude)

        }
    }
    private fun addMarker(latitude: Double, longitude: Double){
        val marker = Marker()
        marker.position = LatLng(latitude,longitude)
        marker.map = navermap
    }

    private fun openLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }
}