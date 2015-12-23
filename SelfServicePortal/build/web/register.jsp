<%-- 
    Document   : register
    Created on : 16 Dec, 2015, 12:50:22 PM
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
       <link rel="stylesheet" href="registercss.css" type="text/css"> 
    </head>
    <body>
        <div>
        <form action="RegisterController" method="get">
            <h3>register to</h3>
            <h2>Space Portal</h2>
            <input type="text" name="firstName" placeholder="First Name" style="width: 150px; height: 30px" required>  
            <input type="text" name="lastName" placeholder="Last Name" style="width: 150px; height: 30px" required/><br><br>
            <input type="text" name="email" placeholder="Email" style="width: 150px; height: 30px" required/>      
            <input type="text" name="confirmEmail" placeholder="Confirm Email" style="width: 150px; height: 30px" required/><br><br>
            <input type="password" name="password" placeholder="Password" style="width: 150px; height: 30px" required/>   
            <input type="password" name="confirmPassword" placeholder="Confirm Password" style="width: 150px; height: 30px" required/><br><br>
            <input type="submit" id="input1" name="createAccount" value="Create an Account" style="width: 200px; height: 40px" />
        </form>
        </div>
        <div>
            <br><a href="login.jsp">Have an account already?</a>
        </div>
    </body>
</html>
