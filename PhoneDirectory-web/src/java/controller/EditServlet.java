/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class EditServlet extends HttpServlet {

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
        
        
      
        
        HttpSession httpSession=request.getSession();
        String search_type=(String) httpSession.getAttribute("search_type");
        String search_value=(String) httpSession.getAttribute("search_value");
        int serial_no=Integer.parseInt(request.getParameter("serial_no"));
        
        httpSession.setAttribute("serial_no",serial_no);
        
        
        PrintWriter out=response.getWriter();
        
        //out.print(search_type+" "+search_value+" "+serial_no);
        PhoneDirectoryModel phoneDirectoryModel=new PhoneDirectoryModel();
        ArrayList<Person> persons=null;
        
        if(search_type.equalsIgnoreCase("By Name"))
        {
               persons=phoneDirectoryModel.getByName(search_value);
        }
        else if(search_type.equalsIgnoreCase("By Partial Name"))
        {
               persons=phoneDirectoryModel.getByPartialName(search_value);
        }
        else if(search_type.equalsIgnoreCase("By Phone"))
        {
               persons=phoneDirectoryModel.getByPhone(search_value);
        }
        
        int s=0;
        Iterator it=persons.iterator();
        Iterator it1;
	while(it.hasNext())
	{
            s++;
            Person person=(Person)it.next();
            if(s==serial_no)
            {
             
                httpSession.setAttribute("Name",person.getName());
                httpSession.setAttribute("Address",person.getAddress());
                it1=person.getPhone().iterator();
                while(it1.hasNext())
                {
                    Phone phone=(Phone)it1.next();
                    out.println(phone.getName()+":"+phone.getNumber());
                    httpSession.setAttribute(phone.getName(), phone.getNumber());
                } 
            }
            
	}
        
        RequestDispatcher rd=request.getRequestDispatcher("/editjsp.jsp");  
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
