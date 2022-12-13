<%-- 
    Document   : PDF_Citas
    Created on : 12 dic. 2022, 19:32:08
    Author     : Acer
--%>

<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
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
            
            File  reporFile = new File (application  getRealPath("report.ReportCitas"));
            Map<String,Object> parameter = new HashMap<String, Object>();
            String valor = request.getParameter("asd");
            
            byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameter,con);
           
            
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes,0,bytes.length);
            
            outputStream.flush();
            outputStream.close();
            
            
            
         
        %>
    </body>
</html>
