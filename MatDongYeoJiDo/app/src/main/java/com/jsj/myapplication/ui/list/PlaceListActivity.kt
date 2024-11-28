package com.jsj.myapplication.ui.list

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.jsj.myapplication.R
import com.jsj.myapplication.data.model.Place

data class PlaceItem(
    val imageResId: Int,
    val name: String,
    val type: String,
    val signature: String
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
        signatureMenuTextView.text = item.signature

        return view
    }
}

class PlaceListActivity : AppCompatActivity() {

    private lateinit var allData: List<PlaceItem>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_list)

        // 리스트뷰 초기화
        listView = findViewById(R.id.listView)
        val places: List<Place>? = intent.getParcelableArrayListExtra("places")
        allData = places?.map {
            PlaceItem(makeImage(it.type), it.name, it.type, it.signature)
        } ?: listOf(
            // 기존의 PlaceItem 리스트를 여기에 추가하세요.
            PlaceItem(R.drawable.korean, "매운집", "한식", "곱도리탕2~3인"),
            PlaceItem(R.drawable.korean, "삼덕통닭", "한식", "삼미통닭"),
            PlaceItem(R.drawable.korean, "이도식당", "한식", "눈꽃닭갈비"),
            PlaceItem(R.drawable.korean, "한사발포차", "한식", "곱도리탕"),
            PlaceItem(R.drawable.western, "더티프라이", "양식", "치즈스커트버거"),
            PlaceItem(R.drawable.korean, "우월소곱창", "한식", "수제한우마늘곱창"),
            PlaceItem(R.drawable.chinese, "금정", "중식", "짜장"),
            PlaceItem(R.drawable.western, "젠틀한식탁", "양식", "베이컨크림 감자뇨끼"),
            PlaceItem(R.drawable.japanese, "이키이키", "일식", "텐붓카케우동"),
            PlaceItem(R.drawable.korean, "창평가마솥순대국 유일무이점", "한식", "순대국"),
            PlaceItem(R.drawable.korean, "월화고기 문래점", "한식", "삼겹살"),
            PlaceItem(R.drawable.western, "플라츠", "양식", "트러플버섯크림리조또"),
            PlaceItem(R.drawable.korean, "청기와타운 영등포본점", "한식", "수원왕갈비"),
            PlaceItem(R.drawable.korean, "오징어마을", "한식", "오징어회"),
            PlaceItem(R.drawable.korean, "천사곱창", "한식", "한우특모듬"),
            PlaceItem(R.drawable.western, "니뽕내뽕 타임스퀘어점", "양식, 중식", "로뽕"),
            PlaceItem(R.drawable.chinese, "연래춘", "중식", "간짜장"),
            PlaceItem(R.drawable.chinese, "가야성", "중식", "짜장면"),
            PlaceItem(R.drawable.korean, "전주익산", "한식", "백순대곱창2인분"),
            PlaceItem(R.drawable.western, "룸펜", "양식", "감바스 알 아히요"),
            PlaceItem(R.drawable.japanese, "멘쇼우라멘", "일식", "돈코츠라멘"),
            PlaceItem(R.drawable.japanese, "오복수산 연남점", "일식", "카이센동"),
            PlaceItem(R.drawable.japanese, "안테이쿠", "일식, 양식", "라자냐볼로네제"),
            PlaceItem(R.drawable.japanese, "은행골", "일식", "목동스페셜"),
            PlaceItem(R.drawable.korean, "당산맥주가게", "한식", "피카츄돈까스 2개"),
            PlaceItem(R.drawable.korean, "애플하우스", "한식", "무침군만두"),
            PlaceItem(R.drawable.korean, "함지곱창전문본점", "한식", "모듬구이(2인)"),
            PlaceItem(R.drawable.japanese, "츄르츄르", "일식", "차슈멘(2장)"),
            PlaceItem(R.drawable.western, "나폴리키친", "양식", "빠네까르보나라"),
            PlaceItem(R.drawable.others, "감성타코 합정점", "기타", "감성그릴드파히타"),
            PlaceItem(R.drawable.others, "낙원타코 용산아이파크몰점", "기타", "한우대창파히타"),
            PlaceItem(R.drawable.chinese, "성민양꼬치 본점", "중식", "양꼬치"),
            PlaceItem(R.drawable.others, "라오삐약", "기타", "까오삐약"),
            PlaceItem(R.drawable.korean, "청어람", "한식", "곱창전골2인"),
            PlaceItem(R.drawable.western, "이즈비", "양식", "새우날치알크림파스타"),
            PlaceItem(R.drawable.korean, "홀짝집", "한식", "돼지김치구이"),
            PlaceItem(R.drawable.korean, "원조순희네빈대떡", "한식", "녹두빈대떡"),
            PlaceItem(R.drawable.korean, "유림 닭도리탕", "한식", "토종닭도리탕"),
            PlaceItem(R.drawable.japanese, "라운드스시", "일식", "필라델피아롤"),
            PlaceItem(R.drawable.japanese, "행운돈까스", "일식", "돈까스+김치볶음밥"),
            PlaceItem(R.drawable.korean, "하얀집 1호점", "한식", "제육볶음"),
            PlaceItem(R.drawable.korean, "원조남산왕돈까스", "한식", "왕돈까스"),
            PlaceItem(R.drawable.korean, "서울왕돈까스 본점", "한식", "왕돈까스"),
            PlaceItem(R.drawable.western, "어흥식당", "양식", "어흥시그니처정식"),
            PlaceItem(R.drawable.western, "매스플레이트안암본점", "양식", "목살필라프"),
            PlaceItem(R.drawable.korean, "홍능족발", "한식", "튀김족발 중"),
            PlaceItem(R.drawable.chinese, "안동장", "중식", "덴뿌라"),
            PlaceItem(R.drawable.others, "반쌥", "기타", "뿌팟퐁커리세트"),
            PlaceItem(R.drawable.korean, "농민백암순대본점", "한식", "순대국"),
            PlaceItem(R.drawable.western, "엘더버거 성수", "양식", "엘더클래식"),
            PlaceItem(R.drawable.western, "제스티살룬 성수", "양식", "와사비쉬림프버거"),
            PlaceItem(R.drawable.chinese, "송죽장", "중식", "간짜장"),
            PlaceItem(R.drawable.korean, "와우신내떡", "한식", "2인세트"),
            PlaceItem(R.drawable.japanese, "하쿠텐라멘", "일식", "이에케라멘"),
            PlaceItem(R.drawable.japanese, "칸다소바", "일식", "마제소바"),
            PlaceItem(R.drawable.korean, "곱 마포직영점", "한식", "모둠구이"),
            PlaceItem(R.drawable.japanese, "다케오호르몬데판야끼", "일식", "믹스호르몬"),
            PlaceItem(R.drawable.korean, "주양두리돈까스", "한식", "두리왕돈까스"),
            PlaceItem(R.drawable.japanese, "광화문미진", "일식", "냉메밀"),
            PlaceItem(R.drawable.korean, "진주집", "한식", "비빔국수"),
            PlaceItem(R.drawable.korean, "장어구이", "한식", "돼지김치찌개 소")
        )

        val listAdapter = PlaceListAdapter(this, allData)
        listView.adapter = listAdapter

        // 스피너 초기화
        val spinner = findViewById<Spinner>(R.id.spinner)
        val spinnerItems = arrayOf("한식", "중식", "일식", "양식", "기타")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedType = spinnerItems[position]
                val filteredData = allData.filter { it.type.contains(selectedType) }
                val listAdapter = PlaceListAdapter(this@PlaceListActivity, filteredData)
                listView.adapter = listAdapter
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun makeImage(type: String): Int {
        return when(type){
            "한식" -> R.drawable.korean
            "중식" -> R.drawable.chinese
            "일식" -> R.drawable.japanese
            "양식" -> R.drawable.western
            else -> R.drawable.others
        }
    }
}