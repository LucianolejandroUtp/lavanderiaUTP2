<%-- 
    Document   : PDF_Citas
    Created on : 12 dic. 2022, 19:32:08
    Author     : Acer
--%>

<%@page import="com.servlets.ReporteCitas"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="net.sf.jasperreports.engine.JRException"%>
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
            
        try {
            Conexion con = new Conexion();
            con.getCon();
            File reportFile =new File(request.getServletContext().getRealPath("ReportCitas.jasper"));

            Map parameters = new HashMap();
            parameters.put("nombre del parametro", "valor del parametro");
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), null, con.getCon());
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0 , bytes.length);
            outputStream.flush();
            outputStream.close();
            
            
        } catch (JRException ex) {
            Logger.getLogger(ReporteCitas.class.getName()).log(Level.SEVERE, null,ex);
            
        }
            
            
         
        %>
    </body>
</html>
