package com.example.json_studenta.entities;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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

    private double getAverage() {
        double[] grades = {
                this.grades.getGrade1(),
                this.grades.getGrade2(),
                this.grades.getGrade3()
        };
        return Arrays.stream(grades).average().orElse(0);
    }

    public boolean isApproved() {
        return getAverage() >= 6.0;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        String situation = isApproved() ? "Aprovado" : "Reprovado";
        String formattedAverage = new DecimalFormat("#.00").format(getAverage());

        return String.format(
                "Aluno(a) %s de %d anos está %s com média %s",
                getName(),
                getAge(),
                situation,
                formattedAverage
        );
    }
}
