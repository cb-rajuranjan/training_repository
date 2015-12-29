/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Person;
import model.PhoneDirectoryModel;

/**
 *
 * @author cb-rajuranjankumar
 */
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        
        HttpSession httpSession=request.getSession();
        String search_type=(String) httpSession.getAttribute("search_type");
        String search_value=(String) httpSession.getAttribute("search_value");
        int serial_no=Integer.parseInt(httpSession.getAttribute("serial_no").toString());
        
        PhoneDirectoryModel phoneDirectoryModel=new PhoneDirectoryModel();
        
        String name=request.getParameter("Name");
        String address=request.getParameter("Address");
        String phone_work=request.getParameter("phone_work");
        String phone_home=request.getParameter("phone_home");
        String phone_mobile=request.getParameter("phone_mobile");
        
        out.print(name+" "+address+" "+phone_home+" "+phone_mobile+" "+phone_work);
        String name1=httpSession.getAttribute("Name").toString();
        String address1=httpSession.getAttribute("Address").toString();
        String phone_work1=httpSession.getAttribute("Work").toString();
        String phone_home1=httpSession.getAttribute("Home").toString();
        String phone_mobile1=httpSession.getAttribute("Mobile").toString();
        
        out.print(name1+" "+address1+" "+phone_home1+" "+phone_mobile1+" "+phone_work1);
        
        phoneDirectoryModel.updatePerson(name1, address1, name, address);
        phoneDirectoryModel.updatePhone(phone_home1, phone_home);
        phoneDirectoryModel.updatePhone(phone_work1, phone_work);
        phoneDirectoryModel.updatePhone(phone_mobile1, phone_mobile);

        httpSession.invalidate();
        
        RequestDispatcher rd=request.getRequestDispatcher("/searchjsp.jsp");  
        rd.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
