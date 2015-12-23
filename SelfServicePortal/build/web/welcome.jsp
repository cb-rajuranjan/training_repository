<%-- 
    Document   : welcome
    Created on : 16 Dec, 2015, 3:46:37 PM
    Author     : cb-raju
--%>

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
        <link rel="stylesheet" href="welcomecss.css" type="text/css"> 
    </head>
    <body>
        <div id="div1">
            <h2 id="h11">Space Portal</h2>
            <h2 id="h22"> HI <%= session.getAttribute("firstName")+" "+session.getAttribute("lastName")%>
               <a href="DeactivateAccount">Deactivate Account</a> 
               <a href="Logout">LOGOUT</a></h2>
        </div>
        <div id="div2">
            <form action="editDetails.jsp">
                 <h3>Welcome to Space Portal</h3>
                 Name:<%= session.getAttribute("firstName")+" "+session.getAttribute("lastName")%><br><br>
                    Email: <%= session.getAttribute("email")%><br><br>
                    Address: <%= session.getAttribute("address")%><br><br>
                    <input type="submit"  id="input1" name="editDetails" value="Edit Details"/>                             
            </form>
        </div>
    </body>
</html>
