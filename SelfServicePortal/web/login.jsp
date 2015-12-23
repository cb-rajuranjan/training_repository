<%-- 
    Document   : login
    Created on : 17 Dec, 2015, 12:09:57 PM
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
        
       <link rel="stylesheet" href="logincss.css" type="text/css">
    </head>
    <body>
        <div id="div1">
            <form action="Login" method="post">
            <h1>Space Portal</h1>
            <h2>Login</h2>
            <input type="text" name="email" placeholder="UserName" required><br> 
            <input type="password" name="password" placeholder="*******" required/><br><br>
            <input id="input1" type="submit" value="Login" ><br>  
            </form>
        </div>
        <div id="div2">
            <form action="register.jsp" method="post">
               <input id="input2" type="submit" value="new Account here?" > 
            </form>
        </div>
    </body>
</html>
