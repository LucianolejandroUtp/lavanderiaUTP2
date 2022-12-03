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
import javax.persistence.EntityManagerFactory;
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
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
    try {
      PrendaJpaController jpac_prenda = new PrendaJpaController(emf);
      TipoDePrendaJpaController jpac_TdPrenda = new TipoDePrendaJpaController(emf);
      PersonaJpaController jpac_Persona = new PersonaJpaController(emf);
      ServicioJpaController jpac_servicio = new ServicioJpaController(emf);
      Prenda miPrenda = new Prenda();
      TipoDePrenda miTipoDePrenda = new TipoDePrenda();
      Persona miPersonaCliente = new Persona();
      Persona miPersonaEmpleado = new Persona();
      Servicio mi_obj_servicio = new Servicio();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Obteniendo el objeto en base al Id obtenido de la vista
      miTipoDePrenda = jpac_TdPrenda.findTipoDePrenda(Long.valueOf(request.getParameter("addTdPrendaId")));
      miPersonaCliente = jpac_Persona.findPersona(Long.valueOf(request.getParameter("addPersonaIdCliente")));
      miPersonaEmpleado = jpac_Persona.findPersona(Long.valueOf(request.getParameter("addPersonaIdEmpleado")));
      mi_obj_servicio = jpac_servicio.findServicio(Long.valueOf(request.getParameter("addServicioId")));
      System.out.println("El tipo de prenda obtenido fue: " + miTipoDePrenda.getDescripcion() + " - " + miTipoDePrenda.getId());
      System.out.println("La Persona Cliente obtenida fue: " + miPersonaCliente.getNombres() + " - " + miPersonaCliente.getId());
      System.out.println("La Persona Empleado obtenida fue: " + miPersonaEmpleado.getNombres() + " - " + miPersonaEmpleado.getId());
      System.out.println("El Servicio obtenido fue: " + mi_obj_servicio.getDescripcion() + " - " + mi_obj_servicio.getId());

//      Llenando los parámetros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      miPrenda.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      miPrenda.setCantidad(Double.valueOf(request.getParameter("addCantidad")));
      miPrenda.setColor(request.getParameter("addColor"));
      miPrenda.setMarca(request.getParameter("addMarca"));
      miPrenda.setEstadoDePrenda(request.getParameter("addEstadoDePrenda"));
//      miPrenda.setPeso(Double.valueOf(request.getParameter("addPeso")));
      miPrenda.setObservacion(request.getParameter("addObservacion"));
      miPrenda.setEstado("activo");
      miPrenda.setCreatedAt(ts);
      miPrenda.setUpdatedAt(ts);
      miPrenda.setTipoDePrendaId(miTipoDePrenda);
      miPrenda.setPersonaIdCliente(miPersonaCliente);
      miPrenda.setPersonaIdEmpleado(miPersonaEmpleado);
      miPrenda.setServicioId(mi_obj_servicio);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpac_prenda.create(miPrenda);

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
