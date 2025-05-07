package com.ordresot.diabetessupporter.data

import com.ordresot.diabetessupporter.data.dto.Preference

interface PreferenceClient {
    fun getData(dto: Preference): Any
    fun saveData(dto: Preference)
}