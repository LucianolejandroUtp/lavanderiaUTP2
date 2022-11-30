/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.CategoriaJpaController;
import com.dao.PersonaJpaController;
import com.dao.PrendaJpaController;
import com.dao.ServicioJpaController;
import com.dao.TipoDePrendaJpaController;
import com.dto.Categoria;
import com.dto.Persona;
import com.dto.Prenda;
import com.dto.Servicio;
import com.dto.TipoDePrenda;
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
@WebServlet(name = "PrendaCreateServlet", urlPatterns = {"/PrendaCreateServlet"})
public class PrendaCreateServlet extends HttpServlet {

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

    System.out.println("Bandera servlet create Prenda");
    try {
      PrendaJpaController jpac_object_prenda = new PrendaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      TipoDePrendaJpaController jpac_object_TdPrenda = new TipoDePrendaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_object_Persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      ServicioJpaController jpac_obj_servicio = new ServicioJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Prenda mi_objeto_prenda = new Prenda();
      TipoDePrenda mi_objeto_TdPrenda = new TipoDePrenda();
      Persona mi_objeto_Persona = new Persona();
      Servicio mi_obj_servicio = new Servicio();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Obteniendo el objeto en base al Id obtenido de la vista
      mi_objeto_TdPrenda = jpac_object_TdPrenda.findTipoDePrenda(Long.valueOf(request.getParameter("addTdPrendaId")));
      mi_objeto_Persona = jpac_object_Persona.findPersona(Long.valueOf(request.getParameter("addPersonaId")));
      mi_obj_servicio = jpac_obj_servicio.findServicio(Long.valueOf(request.getParameter("addServicioId")));
      System.out.println("El tipo de prenda obtenido fue: " + mi_objeto_TdPrenda.getDescripcion() + " - " + mi_objeto_TdPrenda.getId());
      System.out.println("La Persona obtenida fue: " + mi_objeto_Persona.getNombres() + " - " + mi_objeto_Persona.getId());
      System.out.println("El Servicio obtenido fue: " + mi_obj_servicio.getDescripcion() + " - " + mi_obj_servicio.getId());

//      Llenando los parámetros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_prenda.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      mi_objeto_prenda.setCantidad(Double.valueOf(request.getParameter("addCantidad")));
      mi_objeto_prenda.setColor(request.getParameter("addColor"));
      mi_objeto_prenda.setMarca(request.getParameter("addMarca"));
      mi_objeto_prenda.setEstadoDePrenda(request.getParameter("addEstadoDePrenda"));
      mi_objeto_prenda.setPeso(Double.valueOf(request.getParameter("addPeso")));
      mi_objeto_prenda.setObservacion(request.getParameter("addObservacion"));
      mi_objeto_prenda.setEstado("activo");
      mi_objeto_prenda.setCreatedAt(ts);
      mi_objeto_prenda.setUpdatedAt(ts);
      mi_objeto_prenda.setTipoDePrendaId(mi_objeto_TdPrenda);
      mi_objeto_prenda.setPersonaId(mi_objeto_Persona);
      mi_objeto_prenda.setServicioId(mi_obj_servicio);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpac_object_prenda.create(mi_objeto_prenda);

//      Llamando al listALGO.jsp
      PrendaListServlet call = new PrendaListServlet();
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
