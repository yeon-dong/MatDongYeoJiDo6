package com.jsj.myapplication.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.jsj.myapplication.R
import com.jsj.myapplication.data.model.Place
import com.jsj.myapplication.ui.detail.DetailActivity

class PlaceListAdapter(context: Context, private val data: List<Place>) :
    ArrayAdapter<Place>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val item = data[position]

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val nameTextView = view.findViewById<TextView>(R.id.textRestaurantName)
        val typeTextView = view.findViewById<TextView>(R.id.textType)
        val signatureMenuTextView = view.findViewById<TextView>(R.id.textSignatureMenu)

        imageView.setImageResource(makeImage(item.type)) // 이미지 설정
        nameTextView.text = item.name
        typeTextView.text = item.type
        signatureMenuTextView.text = item.signature

        return view
    }

    private fun makeImage(type: String): Int {
        return when (type) {
            "한식" -> R.drawable.korean
            "중식" -> R.drawable.chinese
            "일식" -> R.drawable.japanese
            "양식" -> R.drawable.western
            else -> R.drawable.others // 기타 유형에 대한 기본 이미지
        }
    }
}

class PlaceListActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var places: List<Place> // 전체 Place 리스트
    private lateinit var filteredPlaces: List<Place> // 필터링된 Place 리스트

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_list)

        // 리스트뷰 초기화
        listView = findViewById(R.id.listView)
        places = intent.getParcelableArrayListExtra("places") ?: emptyList()
        filteredPlaces = places // 초기 필터링된 리스트는 전체 리스트와 동일

        // Place 객체를 직접 사용
        val listAdapter = PlaceListAdapter(this, places)
        listView.adapter = listAdapter

        // 스피너 초기화
        val spinner = findViewById<Spinner>(R.id.spinner)
        val spinnerItems = arrayOf("전체", "한식", "중식", "일식", "양식", "기타")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        // 리스트뷰 아이템 클릭 리스너
        listView.setOnItemClickListener { parent, view, position, id ->
            // 필터링된 리스트에서 선택된 장소 가져오기
            val selectedPlace = filteredPlaces[position] // 필터링된 리스트에서 선택된 장소 가져오기
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("place", selectedPlace) // Place 객체를 Intent에 추가
            startActivity(intent)
        }

        // 스피너 선택 리스너
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedType = spinnerItems[position]
                filteredPlaces = if (selectedType == "전체") {
                    places // 전체 아이템을 보여줌
                } else {
                    places.filter { it.type == selectedType } // 선택된 유형으로 필터링
                }
                val listAdapter = PlaceListAdapter(this@PlaceListActivity, filteredPlaces)
                listView.adapter = listAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 선택되지 않았을 때 처리 (필요한 경우)
            }
        }
    }
}
