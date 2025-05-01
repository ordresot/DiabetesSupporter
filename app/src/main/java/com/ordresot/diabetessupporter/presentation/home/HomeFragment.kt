package com.ordresot.diabetessupporter.presentation.home

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.ordresot.diabetessupporter.R
import com.ordresot.diabetessupporter.databinding.FragmentHomeBinding
import java.io.File

class HomeFragment : Fragment() {

    private lateinit var photoFile: File
    private lateinit var photoUri: Uri

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
            //recognizeText(bitmap)
        } else {

        }
    }

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupChart()
        with(binding){
            newEntry.setOnClickListener{
                /*if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), 123)
                } else {
                    launchCamera()
                }*/
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupChart() {
        with(binding) {
            // Примерные данные за неделю
            val entries = listOf(
                Entry(0f, 5.6f), // Понедельник
                Entry(1f, 6.2f),
                Entry(2f, 5.8f),
                Entry(3f, 7.0f),
                Entry(4f, 6.5f),
                Entry(5f, 6.0f),
                Entry(6f, 5.9f)  // Воскресенье
            )

            val dataSet = LineDataSet(entries, "").apply {
                color = resources.getColor(R.color.green) // ✅ зелёный цвет линии
                setDrawCircles(false)
                setDrawValues(false)
                lineWidth = 2f
                setDrawHighlightIndicators(false) // ✅ отключаем крестовину при выделении
                isHighlightEnabled = false
            }

            val lineData = LineData(dataSet)
            glucoseChart.data = lineData

            val days = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")
            glucoseChart.xAxis.apply {
                valueFormatter = IndexAxisValueFormatter(days)
                granularity = 1f
                position = XAxis.XAxisPosition.BOTTOM
                gridColor = resources.getColor(R.color.light_green)
                axisLineColor = resources.getColor(R.color.light_green)
            }

            glucoseChart.axisLeft.isEnabled = false
            glucoseChart.axisRight.apply {
                isEnabled = true
                gridColor = resources.getColor(R.color.light_green)
                setDrawGridLines(false) // ✅ перекрашиваем горизонтальные линии
            }

            glucoseChart.legend.isEnabled = false
            glucoseChart.description.isEnabled = false // ✅ отключаем описание

            glucoseChart.setScaleEnabled(false)       // ✅ отключаем масштабирование
            glucoseChart.setPinchZoom(false)          // ✅ отключаем "щипок" для зума
            glucoseChart.isDoubleTapToZoomEnabled = false // ✅ отключаем двойной тап для зума

            glucoseChart.animateX(0)
            glucoseChart.invalidate()
        }
    }

    /*private fun launchCamera() {
        photoFile = File.createTempFile("photo_", ".jpg", requireContext().cacheDir)
        photoUri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            photoFile
        )
        cameraLauncher.launch(photoUri)
    }*/
}