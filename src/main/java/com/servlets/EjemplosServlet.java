/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import report.Conexion;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author desti
 */
@WebServlet(name = "EjemplosServlet", urlPatterns = {"/EjemplosServlet"})
public class EjemplosServlet extends HttpServlet {

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
      throws ServletException, IOException {
    //    try {
    response.setContentType("text/html;charset=UTF-8");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
    Conexion con = new Conexion();
    Connection conn = con.getCon();


            //File  reporFile = new File ("src\\main\\java\\com\\report\\ReportCitas.jasper");
            
            Map<String,Object> parameter = new HashMap<String, Object>();
            String valor = request.getParameter("asd");
            
            byte[] bytes = null;
            //bytes = JasperRunManager.runReportToPdf("src\\main\\java\\com\\report\\ReportCitas.jasper",null,con);
           
            
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes,0,bytes.length);
            
            outputStream.flush();
            outputStream.close();

    try {
      Map parameters = new HashMap();
      parameters.put("TITULO", "PAISES");
      parameters.put("FECHA", new java.util.Date());
      JasperReport report = JasperCompileManager.compileReport("D:\\ReportCitas.jrxml");
      JasperPrint print = JasperFillManager.fillReport(report, parameters, conn);
      // Exporta el informe a PDF
      JasperExportManager.exportReportToPdfFile(print,"D:\\ReportCitas.pdf");
      //Para visualizar el pdf directamente desde java
      JasperViewer.viewReport(print, false);
    } catch (Exception e) {
      e.printStackTrace();
    }

//
//      File reporFile = new File("src/main/java/report/ReportCitas.jasper");
//
////    File reporFile = new File( .getRealPath("report.ReportCitas"));
//      Map<String, Object> parameter = new HashMap<String, Object>();
//      String valor = request.getParameter("asd");
//
////    byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameter, con);
//      byte[] bytes = JasperRunManager.runReportToPdf("src\\main\\java\\report\\ReportCitas.jasper", null, conn);
//
//      response.setContentType("application/pdf");
//      response.setContentLength(bytes.length);
//      ServletOutputStream outputStream = response.getOutputStream();
//      outputStream.write(bytes, 0, bytes.length);
//
//      outputStream.flush();
//      outputStream.close();
//    } catch (JRException ex) {
//      Logger.getLogger(EjemplosServlet.class.getName()).log(Level.SEVERE, null, ex);
//    }

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

}
