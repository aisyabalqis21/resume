/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc584.model;

/**
 *
 * @author aisya
 */

public class ResumeBean {

    private String name;
    private String studentId;
    private String email;
    private String programCode;
    private String careerObjective;
    private double cgpa;
    private int yearsExperience;
    private String[] skills;
    private boolean internship;

    public ResumeBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getCareerObjective() {
        return careerObjective;
    }

    public void setCareerObjective(String careerObjective) {
        this.careerObjective = careerObjective;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public boolean isInternship() {
        return internship;
    }

    public void setInternship(boolean internship) {
        this.internship = internship;
    }

    public double calculateStrength_3456() {
        int L1 = 6;
        int L2 = 5;

        int numSkills = 0;
        if (skills != null) {
            numSkills = skills.length;
        }

        double score = (50 + L1 * 5) + (yearsExperience * 5) + (numSkills * (L2 + 2));

        if (score > 100) {
            score = 100;
        }
        return score;
    }
}
