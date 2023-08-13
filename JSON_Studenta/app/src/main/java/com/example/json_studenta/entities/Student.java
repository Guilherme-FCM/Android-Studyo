package com.example.json_studenta.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Student {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("birth_year")
    @Expose
    private Integer birth_year;

    @SerializedName("grades")
    @Expose
    private Grades grades;

    public Student(String name, Integer birth_year, Grades grades) {
        this.name = name;
        this.birth_year = birth_year;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birth_year;
    }

    public void setBirthYear(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Grades getGrades() {
        return grades;
    }

    public void setGrades(Grades grades) {
        this.grades = grades;
    }

    public int getAge() {
        int year = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O
                ? LocalDate.now().getYear() : 2023;
        return year - this.birth_year;
    }

    public boolean isApproved() {
        double average = (grades.getGrade1() + grades.getGrade2() + grades.getGrade3()) / 3;
        return average >= 6.0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", birth_year=" + birth_year +
                ", grades=" + grades +
                '}';
    }
}
