<%-- 
    Document   : PDF_Citas
    Created on : 12 dic. 2022, 19:32:08
    Author     : Acer
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.io.File"%>
<%@page import="report.Conexion"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Connection con = null;
            try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/lavanderiautpmigrations","root","");
            out.print("Conexion en Linea");
            }catch(Exception ex){
            out.print("Error:  !!!!!!!!!!!!!!  "+ex.getMessage());
            }
         
        %>
    </body>
</html>
