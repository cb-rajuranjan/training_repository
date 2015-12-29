<%-- 
    Document   : searchjsp
    Created on : Dec 28, 2015, 12:29:55 PM
    Author     : cb-rajuranjankumar
--%>

<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.File"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="model.Phone"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phone Directory</title>
         <script type = "text/javascript" >
            function preventBack(){window.history.forward();}
            setTimeout("preventBack()", 0);
            window.onunload=function(){null};
        </script>
       
       <link rel="stylesheet" href="searchcss.css" type="text/css">
    </head>
    <body>
        <div>
            <form action="SearchServlet" method="post">
                <h1>All Contacts</h1><br>
                <select name="search_type" required>
                <option value="search_type">Search_type</option>
                <option value="By Name">By Name</option>
                <option value="By Partial Name">By Partial Name</option>
                <option value="By Phone">By Phone</option>
                </select>
                <input type="text" name="search_value" placeholder="Enter Search value" required/>
                <input type="submit" id="input1" name="search" value="search"/><br><br>
                <table >
                    <tr>
                    <th width="20%">Name</th>
                    <th width="20%">Address</th> 
                    <th width="100%">Phone</th>
                    <th width="10%">Edit</th>
                    </tr>
                    
        <%   
            
            int serial_no=0;
            
            String jsonString=(String)request.getAttribute("jsonString");
           
            String [] column = "Name,Address,Mobile,Home,Work".split(",");
            JSONParser parser = new JSONParser();        
        try 
        {       
            Object obj = parser.parse(jsonString.toString());
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray persons=(JSONArray)jsonObject.get("Person");
            Iterator it=persons.iterator();
            while(it.hasNext())
            {
                StringBuffer phone1=new StringBuffer();
                serial_no++;
                JSONObject jsonObject1=(JSONObject) it.next(); %>
                
               <tr>
                    <td><% out.print(jsonObject1.get("Name").toString()); %></td>
                    <td><% out.print(jsonObject1.get("Address").toString()); %></td>
     
                   
                <%
                JSONArray phones=(JSONArray)jsonObject1.get("Phone");
                Iterator it1=phones.iterator();
                int i=2;
                while(it1.hasNext())
                {
                    JSONObject o=((JSONObject)it1.next());
                    phone1.append(o.get(column[i]).toString()+"  ("+column[i]+")");
                    i++;
                }%>
     
                <td><% out.print(phone1); %></td>
                <td><a href="EditServlet?serial_no=<% out.print(serial_no);%>">edit</a></td>  </tr>
     
               
            <% 
            }
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }

         %>
        
        </table>
            </form>
        </div>
    </body>
</html>
