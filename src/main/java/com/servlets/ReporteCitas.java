/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import report.Conexion;

/**
 *
 * @author Acer
 */
@WebServlet(name = "ReporteCitas", urlPatterns = {"/reporteCitas"})
public class ReporteCitas extends HttpServlet {

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
            throws ServletException, IOException{
        
        System.out.println(" ------vvvvvvvvvvvvv------- ");
        response.setContentType("text/html;charset=UTF-8");
        
        
        try {
            
            
            Conexion con = new Conexion();
            con.getCon();
            File reportFile =new File(request.getServletContext().getRealPath("ReportCitas.jasper"));
            
            System.out.println(" -------Dentro del TRY------ ");
            
            Map parameters = new HashMap();
            parameters.put("nombre del parametro", "valor del parametro");
            
            System.out.println(" -------entrando en bytes------ ");
            System.out.println(" ------ "+ reportFile.getPath());
            System.out.println(" ------ "+ con.getCon());
            
            
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), null, con.getCon());
            
                
            
            System.out.println(" -------saliendo de  bytes------ ");
            
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0 , bytes.length);
            outputStream.flush();
            outputStream.close();
            
            
        } catch (JRException ex) {
            Logger.getLogger(ReporteCitas.class.getName()).log(Level.SEVERE, null,ex);
            
        }
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
    
    
    
    /**
    
    try {
            InputStream /**logoEmpresa = this.getServletConfig()
                    .getServletContext()
                    .getResourceAsStream("reportesJasper/img/logoAlexanderStore.png"),
                    logoFooter = this.getServletConfig()
                            .getServletContext()
                            .getResourceAsStream("reportesJasper/img/check.png"),
   -    -  *   -        /
                    reporteEmpleado = this.getServletConfig()
                            .getServletContext()
                        //  .getResourceAsStream("reportesJasper/ReporteEmpleados.jasper");
                            .getResourceAsStream("ReportCitas.jasper");
            if (reporteEmpleado != null) {
                
                /**
                
                try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          String url = "jdbc:mysql://localhost:3306/lavanderiautpmigrations";
          String username = "root";
          String password = "";

          Connection con = DriverManager.getConnection(url, username, password);
          if (con != null) {
              System.out.println("Database Connected correctamente");
          } else {
              System.out.println("Database Connection failed");
          }
          File fichero = new File("D:\\ReportCitas.jasper");

          System.out.println("   ----1----     " + fichero.getAbsoluteFile());
          System.out.println("   --------     " + fichero.getPath());
          System.out.println("   --------     " + fichero.getName());
          System.out.println("   --------     " + fichero.getParent());
          System.out.println("   --------     " + fichero.getCanonicalPath());

          JasperReport reporte = (JasperReport) JRLoader.loadObject("D:/ReportCitas.jasper");

          JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, con);
          JRExporter exporter = new JRPdfExporter();
          exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
          exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportejackPDF.pdf"));
          exporter.exportReport();
          
                    System.out.println("Se guardo con exito");

      } catch (Exception e) {
            response.setContentType("text/plain");
            out.print("ocurrio un error al intentar generar el reporte:" + e.getMessage());
            e.printStackTrace();
      } finally {
      }
                
    */            
                
                
                
                /**
                
                String jsonEmpleados = request.getParameter("lista"); //OJO
                Gson gson = new Gson();
                List<Empleado> reportesEmpleados = new ArrayList<>();
                List<Empleado> reportesEmpleados2 = new ArrayList<>();

                reportesEmpleados.add(new Empleado());
                reportesEmpleados2 = gson.fromJson(jsonEmpleados, new TypeToken<List<Empleado>>() {
                }.getType());
                reportesEmpleados.addAll(reportesEmpleados2);
  -    -    /  -    *
                
                JasperReport report = (JasperReport) JRLoader.loadObject(reporteEmpleado);
                //JRBeanArrayDataSource ds = new JRBeanArrayDataSource(reportesEmpleados.toArray());

                Map<String, Object> parameters = new HashMap();
                //parameters.put("ds", ds);
                //parameters.put("logoEmpresa", logoEmpresa);
                //parameters.put("imagenAlternativa", logoFooter);
                response.setContentType("application/pdf");
                response.addHeader("Content-disposition", "inline; filename=ReportesEmpleados.pdf");
                //a Null de ´parameters
                JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, conn);
                
                
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                out.flush();
                out.close();
            } else {
                response.setContentType("text/plain");
                out.println("no se pudo generar el reporte");
                out.println("esto puede deberse a que la lista de datos no fue recibida o el archivo plantilla del reporte no se ha encontrado");
                out.println("contacte a soporte");
            }
        } catch (Exception e) {
            response.setContentType("text/plain");
            out.print("ocurriÃ³ un error al intentar generar el reporte:" + e.getMessage());
            e.printStackTrace();
        }
    
    
                 */
    
    /**
    Conexion con1 = new Conexion();
    Connection conn1 = con1.getCon();
    

    try {
      Map parameters = new HashMap();
      parameters.put("TITULO", "PAISES");
      parameters.put("FECHA", new java.util.Date());
      JasperReport report = JasperCompileManager.compileReport("D:\\ReportCitas.jrxml");
      JasperPrint print = JasperFillManager.fillReport(report, null, conn);
      // Exporta el informe a PDF
      JasperExportManager.exportReportToPdfFile(print,"D:\\ReportCitas.pdf");
      //Para visualizar el pdf directamente desde java
      JasperViewer.viewReport(print, false);
    } catch (Exception e) {
      e.printStackTrace();
    }*/
    
    /*
      try {
          
          System.out.println(" ------------ Ingreso");
          Conexion con = new Conexion();
          Connection conn = con.getCon();
          
          JasperReport reporte  = null;
          String path = "src\\main\\java\\report\\ReportCitas.jrxml";
          
          reporte = (JasperReport) JRLoader.loadObjectFromLocation(path);
          
          JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
          
          
          
          JasperViewer view = new JasperViewer(jprint, false);
          
          view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
          
          view.setVisible(true);
          
          System.out.println(" ------------ Salida");
          
          
          
      } catch (Exception e) {
          Logger.getLogger(EjemplosServlet.class.getName()).log(Level.SEVERE, null, e);
      }
    */

  /**
//  "C:\\Users\\Acer\\Documents\\GitHub\\lavanderiaUTP2\\src\\main\\java\\report\\ReportCitas.jrxml"
    try {
        File reporFile = new File("src/main/java/report/ReportCitas.jasper");
        
        File reporFile = new File( servletContext.getRealPath("report.ReportCitas"));
        );    
        Map<String, Object> parameter = new HashMap<String, Object>();
        String valor = request.getParameter("asd");

        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameter, con);
        byte[] bytes = JasperRunManager.runReportToPdf("src\\main\\java\\report\\ReportCitas.jasper", null, conn);

        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);

        outputStream.flush();
        outputStream.close();
    
    */
    

}
