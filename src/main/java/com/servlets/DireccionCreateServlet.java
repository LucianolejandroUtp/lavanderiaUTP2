/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DireccionJpaController;
import com.dao.DistritoJpaController;
import com.dto.Direccion;
import com.dto.Distrito;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
@WebServlet(name = "DireccionCreateServlet", urlPatterns = {"/DireccionCreateServlet"})
public class DireccionCreateServlet extends HttpServlet {

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
    
    System.out.println("Bandera servlet create Dirección");
    try {
      DireccionJpaController jpac_obj_direccion = new DireccionJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      DistritoJpaController jpac_obj_distrito = new DistritoJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Direccion mi_objeto_direccion = new Direccion();
      Distrito mi_objeto_distrito = new Distrito();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);
      
//      Obteniendo el objeto en base al Id obtenido de la vista
      mi_objeto_distrito = jpac_obj_distrito.findDistrito(Long.valueOf(request.getParameter("addDistritoId")));
      System.out.println("El Distrito obtenido fue: " + mi_objeto_distrito.getDescripcion() +" - "+ mi_objeto_distrito.getId());

//      Llenando los parámetros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_direccion.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      mi_objeto_direccion.setDescripcion(request.getParameter("addDireccion"));
      mi_objeto_direccion.setReferencia(request.getParameter("addReferencia"));
      mi_objeto_direccion.setEstado("activo");
      mi_objeto_direccion.setDistritoId(mi_objeto_distrito);
      mi_objeto_direccion.setCreatedAt(ts);
      mi_objeto_direccion.setUpdatedAt(ts);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpac_obj_direccion.create(mi_objeto_direccion);

//      Llamando al listALGO.jsp
      DireccionListServlet call = new DireccionListServlet();
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
