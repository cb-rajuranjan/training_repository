<%-- 
    Document   : editjsp
    Created on : Dec 28, 2015, 11:48:47 AM
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
        <title>JSP Page</title>
         <script type = "text/javascript" >
            function preventBack(){window.history.forward();}
            setTimeout("preventBack()", 0);
            window.onunload=function(){null};
        </script>
       
     <link rel="stylesheet" href="editcss.css" type="text/css">
    </head>
    <body>
        <div id="div2">
            <form action="UpdateServlet" method="post">
           
                    
        
        Name: <input  id="i1" type="text" name="Name" value="<%= session.getAttribute("Name")%>"   required>
            <input type="text" id="i1" name="lastName" value="<%=session.getAttribute("lastName")%>"  required/><br>
            Email: <input type="text" name="email" value="<%=session.getAttribute("email")%>"  required/><br> 
            Address: <input type="text" name="Address" value="<%=session.getAttribute("Address")%>"  required/><br>
            <input type="text" id="i1" name="city" placeholder="City" />
            <select name="state" required>
                <option value="none">State</option>
                <option value="TamilNadu">TamilNadu</option>
                <option value="Bihar">Bihar</option>
                <option value="Andhra">Andhra</option>
                <option value="UP">UP</option>
            </select>
            <br>
            <input type="text" id="i1" name="ZIP" placeholder="ZIP"  />
            <select name="country"  >
                <option value="none">country</option>
                <option value="India">India</option>
                <option value="USA">USA</option>
                <option value="France">France</option>
                <option value="Japan">Japan</option>
            </select>
            <br>
            Phone(Work) <input type="text" name="phone_work" placeholder="Phone(Work)"  value="<%=session.getAttribute("Work")%>" /><br>
            Phone(Mobile)<input type="text" name="phone_mobile" placeholder="Phone(Mobile)" value="<%=session.getAttribute("Mobile")%>"  /><br>
            Phone(Home)<input type="text" name="phone_home" placeholder="Phone(Home)" value="<%=session.getAttribute("Home")%>"   /><br><br>
            
            <input type="submit" id="input1" name="saveDetails" value="Save Details" style="width: 200px; height: 30px"/>
            
            
            
            
            
        </form>
        </div>
    </body>
</html>
