package com.ordresot.diabetessupporter.data.network

import com.ordresot.diabetessupporter.data.dto.GlucoseMeasurementsOnPeriodResponse
import com.ordresot.diabetessupporter.data.dto.PeriodDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.OffsetDateTime

interface DiabetesApiService {

    @GET("glucose-measurement")
    fun getGlucoseMeasurementsOnPeriod(
        @Body period: PeriodDto
    ): Call<GlucoseMeasurementsOnPeriodResponse>

}