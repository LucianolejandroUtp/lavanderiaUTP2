/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DepartamentoJpaController;
import com.dao.DistritoJpaController;
import com.dto.Departamento;
import com.dto.Distrito;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author desti
 */
@WebServlet(name = "DistritoCreateServlet", urlPatterns = {"/DistritoCreateServlet"})
public class DistritoCreateServlet extends HttpServlet {

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
    System.out.println("Bandera servlet create distrito");
    try {
      DistritoJpaController jpac_object_distrito = new DistritoJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      DepartamentoJpaController jpac_object_depa = new DepartamentoJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Distrito mi_objeto_distrito = new Distrito();
      Departamento mi_objeto_depa = new Departamento();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);
      
//      Obteniendo el departamento en base al Id obtenido de la vista
      mi_objeto_depa = jpac_object_depa.findDepartamento(Long.valueOf(request.getParameter("addDepartamentoId")));
      System.out.println("El departamento obtenido fue: " + mi_objeto_depa.getDescripcion() +" - "+ mi_objeto_depa.getId());

//      Llenando los par??metros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_distrito.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      mi_objeto_distrito.setDescripcion(request.getParameter("addDescripcion"));
      mi_objeto_distrito.setEstado("activo");
      mi_objeto_distrito.setDepartamentoId(mi_objeto_depa);
      mi_objeto_distrito.setCreatedAt(ts);
      mi_objeto_distrito.setUpdatedAt(ts);

//      Llamando al m??todo crear del controlador y pas??ndole el objeto Distrito
      jpac_object_distrito.create(mi_objeto_distrito);

//      Llamando al listALGO.jsp
      DistritoListServlet call = new DistritoListServlet();
      call.processRequest(request, response);
//      response.sendRedirect("Distrito/List.jsp").forward(request, response);

    } catch (IOException | ServletException theException) {
      System.out.println(theException);
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

}
