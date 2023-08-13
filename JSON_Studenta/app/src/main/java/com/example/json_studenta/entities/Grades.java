package com.example.json_studenta.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Grades {
    @SerializedName("grade1")
    @Expose
    private Integer grade1;

    @SerializedName("grade2")
    @Expose
    private Integer grade2;

    @SerializedName("grade3")
    @Expose
    private Integer grade3;

    public Grades(Integer grade1, Integer grade2, Integer grade3) {
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
    }

    public Integer getGrade1() {
        return grade1;
    }

    public void setGrade1(Integer grade1) {
        this.grade1 = grade1;
    }

    public Integer getGrade2() {
        return grade2;
    }

    public void setGrade2(Integer grade2) {
        this.grade2 = grade2;
    }

    public Integer getGrade3() {
        return grade3;
    }

    public void setGrade3(Integer grade3) {
        this.grade3 = grade3;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "grade1=" + grade1 +
                ", grade2=" + grade2 +
                ", grade3=" + grade3 +
                '}';
    }
}
