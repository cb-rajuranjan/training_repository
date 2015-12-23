<%-- 
    Document   : editDetails
    Created on : 16 Dec, 2015, 5:30:07 PM
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
        
        <link rel="stylesheet" href="editcss.css" type="text/css">
    </head>
    <body>
         
        <div id="div1">
            <h2 id="h11">Space Portal</h2>
            <h2 id="h22"> HI <%= session.getAttribute("firstName")+" "+session.getAttribute("lastName")%>
               <a href="DeactivateAccount">Deactivate Account</a> 
               <a href="Logout">LOGOUT</a></h2>
        </div>
        
     <div id="div2">
        <form action="AddressController" method="post">
            
           Name: <input  id="i1" type="text" name="firstName" value="<%= session.getAttribute("firstName")%>"   required>
            <input type="text" id="i1" name="lastName" value="<%=session.getAttribute("lastName")%>"  required/><br><br>
            Email: <input type="text" name="email" value="<%=session.getAttribute("email")%>"  required/><br><br>  
            Address: <input type="text" name="addressLine1" placeholder="Address Line1"  required/><br><br>
            <input type="text" name="addressLine2" placeholder="Address Line2"  /><br><br>
            <input type="text" id="i1" name="city" placeholder="City" required/>
            <select name="state"  id="i1" required>
                <option value="none">State</option>
                <option value="TamilNadu">TamilNadu</option>
                <option value="Bihar">Bihar</option>
                <option value="Andhra">Andhra</option>
                <option value="UP">UP</option>
            </select>
            <br><br>
            <input type="text" id="i1" name="ZIP" placeholder="ZIP"  required/>
            <select name="country" id="i1" required>
                <option value="none">country</option>
                <option value="India">India</option>
                <option value="USA">USA</option>
                <option value="France">France</option>
                <option value="Japan">Japan</option>
            </select>
            <br><br>
            <input type="submit" id="input1" name="saveDetails" value="Save Details" style="width: 200px; height: 30px"/>
            
        </form>
    </div>
    </body>
</html>
