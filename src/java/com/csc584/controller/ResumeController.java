/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc584.controller;

/**
 *
 * @author aisya
 */

import com.csc584.model.ResumeBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResumeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String studentId = request.getParameter("studentId");
        String email = request.getParameter("email");
        String programCode = request.getParameter("programCode");
        String objective = request.getParameter("objective");
        String internship = request.getParameter("internship");
        String[] skills = request.getParameterValues("skills");

        double cgpa = Double.parseDouble(request.getParameter("cgpa"));
        int experience = Integer.parseInt(request.getParameter("experience"));
        int token = Integer.parseInt(request.getParameter("securityToken_456"));

        String error = "";

        if (!email.contains("@student.uitm.edu.my")) {
            error = error + "Email must use @student.uitm.edu.my<br>";
        }
        if (cgpa < 0.00 || cgpa > 4.00) {
            error = error + "CGPA must be between 0.00 and 4.00<br>";
        }
        if (token != 11) {
            error = error + "Invalid security token<br>";
        }

        if (!error.equals("")) {
            out.println("<html><body>");
            out.println("<h2>Error</h2>");
            out.println(error);
            out.println("<br><a href='resumeForm.html'>Back</a>");
            out.println("</body></html>");
            return;
        }

        HttpSession session = request.getSession();

        int count = 0;
        if (session.getAttribute("editCount") != null) {
            count = (Integer) session.getAttribute("editCount");
        }
        count = count + 1;
        session.setAttribute("editCount", count);

        if (count > 11) {
            out.println("<html><body>");
            out.println("<h2>You have reached your edit limit for this session!</h2>");
            out.println("</body></html>");
            return;
        }

        ResumeBean resumeBean = new ResumeBean();
        resumeBean.setName(name);
        resumeBean.setStudentId(studentId);
        resumeBean.setEmail(email);
        resumeBean.setProgramCode(programCode);
        resumeBean.setCgpa(cgpa);
        resumeBean.setYearsExperience(experience);
        resumeBean.setCareerObjective(objective);
        resumeBean.setSkills(skills);

        if (internship.equals("yes")) {
            resumeBean.setInternship(true);
        } else {
            resumeBean.setInternship(false);
        }

        session.setAttribute("candidate_2024123456_profile", resumeBean);

        out.println("<html><body>");
        out.println("<h2>Resume Preview</h2>");
        out.println("Name: " + resumeBean.getName() + "<br>");
        out.println("Student ID: " + resumeBean.getStudentId() + "<br>");
        out.println("Email: " + resumeBean.getEmail() + "<br>");
        out.println("Program Code: " + resumeBean.getProgramCode() + "<br>");
        out.println("CGPA: " + resumeBean.getCgpa() + "<br>");
        out.println("Years of Experience: " + resumeBean.getYearsExperience() + "<br>");
        out.println("Career Objective: " + resumeBean.getCareerObjective() + "<br>");

        out.println("Skills: ");
        if (skills != null) {
            for (int i = 0; i < skills.length; i++) {
                out.println(skills[i] + " ");
            }
        } else {
            out.println("None");
        }
        out.println("<br>");

        if (resumeBean.isInternship()) {
            out.println("Internship: Yes<br>");
        } else {
            out.println("Internship: No<br>");
        }

        out.println("<h3>Profile Strength Score: " + resumeBean.calculateStrength_3456() + " %</h3>");
        out.println("Edit count: " + count + "<br>");
        out.println("<a href='resumeForm.html'>Edit Profile</a>");
        out.println("</body></html>");
    }
}