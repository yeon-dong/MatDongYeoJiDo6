package com.jsj.myapplication.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jsj.myapplication.R


data class PlaceItem(
    val imageResId: Int,
    val name: String,
    val type: String,
    val signatureMenu: String
)


class PlaceListAdapter(context: Context, private val data: List<PlaceItem>) :
    ArrayAdapter<PlaceItem>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val item = data[position]

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val nameTextView = view.findViewById<TextView>(R.id.textRestaurantName)
        val typeTextView = view.findViewById<TextView>(R.id.textType)
        val signatureMenuTextView = view.findViewById<TextView>(R.id.textSignatureMenu)

        imageView.setImageResource(item.imageResId)
        nameTextView.text = item.name
        typeTextView.text = item.type
        signatureMenuTextView.text = item.signatureMenu

        return view
    }
}

class PlaceListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_list)

        // 스피너 초기화
        val spinner = findViewById<Spinner>(R.id.spinner)
        val spinnerItems = arrayOf("한식", "중식", "일식", "양식", "기타")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        // 리스트뷰 초기화
        val listView = findViewById<ListView>(R.id.listView)
        val data = listOf(
            PlaceItem(R.drawable.ic_launcher_foreground, "음식점1", "한식", "비빔밥"),
            PlaceItem(R.drawable.ic_launcher_foreground, "음식점2", "일식", "스시")
        )
        val listAdapter = PlaceListAdapter(this, data)
        listView.adapter = listAdapter
    }
}