/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.CategoriaJpaController;
import com.dao.DepartamentoJpaController;
import com.dao.PrendaJpaController;
import com.dto.Categoria;
import com.dto.Departamento;
import com.dto.Prenda;
import com.dto.TipoDePrenda;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import report.Conexion;


import net.sf.jasperreports.engine.JasperRunManager;
import java.sql.DriverManager;
import java.io.File;
import report.Conexion;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import javax.faces.application.Application;
import javax.servlet.ServletContext;

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
    response.setContentType("text/html;charset=UTF-8");

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");

    Conexion con = new Conexion();
    
    
    
    
    
    con.getCon();

            File  reporFile = new File ( .getRealPath("report.ReportCitas"));
            Map<String,Object> parameter = new HashMap<String, Object>();
            String valor = request.getParameter("asd");
            
            byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameter,con);
           
            
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes,0,bytes.length);
            
            outputStream.flush();
            outputStream.close();
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
