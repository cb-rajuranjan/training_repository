/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Person;
import model.Phone;
import model.PhoneDirectoryModel;

/**
 *
 * @author cb-rajuranjankumar
 */
public class SearchServlet extends HttpServlet {

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
        PhoneDirectoryModel phoneDirectoryModel=new PhoneDirectoryModel();
        String jsonString=null;
        String search_type=request.getParameter("search_type");
        String search_value=request.getParameter("search_value");
        
        //out.println(search_type+" "+search_value);
        
        HttpSession httpSession=request.getSession();
        httpSession.setAttribute("search_type", search_type);
        httpSession.setAttribute("search_value", search_value);
        
        if(search_type.equalsIgnoreCase("search_type"))
        {
            out.println("Select appropriate search_type");
            RequestDispatcher rd=request.getRequestDispatcher("/searchjsp.jsp");  
            rd.include(request, response);
            return;
        }
        else if(search_type.equalsIgnoreCase("By Name"))
        {
               jsonString=phoneDirectoryModel.getJSONString_ByName(search_value);
        }
        else if(search_type.equalsIgnoreCase("By Partial Name"))
        {
               jsonString=phoneDirectoryModel.getJSONString_ByPartialName(search_value);
        }
        else if(search_type.equalsIgnoreCase("By Phone"))
        {
               jsonString=phoneDirectoryModel.getJSONString_ByPhone(search_value);
        }
        if(jsonString.equals("{\"Person\":[]}"))
        out.println("Invalid Search_value or type....!!!");
        request.setAttribute("jsonString", jsonString);
        RequestDispatcher rd=request.getRequestDispatcher("/searchjsp.jsp");  
        rd.include(request, response);
        
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
