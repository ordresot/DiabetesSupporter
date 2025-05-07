package com.ordresot.diabetessupporter.domain.models

enum class AmountType(val typeName: String) {
    MG("мг"),
    UNITS("ед."),
    PILLS("табл."),
    DROPS("капли"),
    DOSES("дозы"),
    ML("мл")
}
