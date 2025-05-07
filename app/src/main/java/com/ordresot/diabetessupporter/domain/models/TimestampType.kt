package com.ordresot.diabetessupporter.domain.models

enum class TimestampType(val typeName: String) {
    BREAKFAST("Завтрак"),
    LUNCH("Обед"),
    SNACK("Перекус"),
    DINNER("Ужин"),
    BEFORE_MEAL("До еды"),
    AFTER_MEAL("После еды"),
    FASTING("Натощак"),
    CORRECTION("Коррекция"),
    HYPO_FEELING("Ощущение гипогликемии"),
    HYPER_FEELING("Ощущение гипергликемии")
}