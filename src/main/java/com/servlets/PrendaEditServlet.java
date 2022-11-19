/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.PersonaJpaController;
import com.dao.PrendaJpaController;
import com.dao.TipoDePrendaJpaController;
import com.dto.Persona;
import com.dto.Prenda;
import com.dto.TipoDePrenda;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
@WebServlet(name = "PrendaEditServlet", urlPatterns = {"/PrendaEditServlet"})
public class PrendaEditServlet extends HttpServlet {

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

    System.out.println("Entrando a Servicio Edit Servlet");
    System.out.println(request.getParameter("editId"));
    System.out.println(request.getParameter("editCantidad"));
    System.out.println(request.getParameter("editColor"));
    System.out.println(request.getParameter("editMarca"));
    System.out.println(request.getParameter("editEstadoDePrenda"));
    System.out.println(request.getParameter("editPeso"));
    System.out.println(request.getParameter("editObservacion"));
    System.out.println(request.getParameter("editEstado"));
    System.out.println(request.getParameter("editTdPrendaId"));
    System.out.println(request.getParameter("editPersonaId"));
    try {
//      Inicialización de objetos
      PrendaJpaController jpac_obj_prenda = new PrendaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      TipoDePrendaJpaController jpac_obj_tipo_prenda = new TipoDePrendaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Prenda oldObject_prenda;
      TipoDePrenda tipo_prenda;
      Persona persona;

//      Lo relacionado a la fecha
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Necesitamos una lista de los Direcciones
//      DireccionJpaController jpac_xa_lista_de_Direcciones = new DireccionJpaController();
//      List<Direccion> mi_lista_de_Direcciones = new ArrayList<>();
//      mi_lista_de_Direcciones = jpac_xa_lista_de_Direcciones.findDireccionEntities();
//      Obteniendo el objeto con foreign key en base al Id que nos da la vista
      tipo_prenda = jpac_obj_tipo_prenda.findTipoDePrenda(Long.valueOf(request.getParameter("editTdPrendaId")));
      persona = jpac_obj_persona.findPersona(Long.valueOf(request.getParameter("editPersonaId")));

      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObject_prenda = jpac_obj_prenda.findPrenda(Long.valueOf(request.getParameter("editId")));
      System.out.println("La prenda obtenida es: " + oldObject_prenda);

//      Comparando y asignando nuevos valores al objeto
      if (!oldObject_prenda.getColor().equals(request.getParameter("editColor"))) {
        oldObject_prenda.setColor(request.getParameter("editColor"));
      }
      if (!oldObject_prenda.getMarca().equals(request.getParameter("editMarca"))) {
        oldObject_prenda.setMarca(request.getParameter("editMarca"));
      }
      if (!oldObject_prenda.getEstadoDePrenda().equals(request.getParameter("editEstadoDePrenda"))) {
        oldObject_prenda.setEstadoDePrenda(request.getParameter("editEstadoDePrenda"));
      }
      if (!oldObject_prenda.getObservacion().equals(request.getParameter("editObservacion"))) {
        oldObject_prenda.setObservacion(request.getParameter("editObservacion"));
      }
      if (!oldObject_prenda.getEstado().equals(request.getParameter("editEstado"))) {
        oldObject_prenda.setEstado(request.getParameter("editEstado"));
      }

//      BigDecimal old = new BigDecimal(oldObject_prenda.getCantidad());
//      BigDecimal nuevo = new BigDecimal(request.getParameter("editCantidad"));

      if(oldObject_prenda.getPeso().compareTo(Double.valueOf(request.getParameter("editPeso"))) != 0){
        oldObject_prenda.setPeso(Double.valueOf(request.getParameter("editPeso")));
      }
      
      if(oldObject_prenda.getCantidad().compareTo(Double.valueOf(request.getParameter("editCantidad"))) !=0){
        oldObject_prenda.setCantidad(Double.valueOf(request.getParameter("editCantidad")));
      }
      
//      if (old.compareTo(nuevo) != 0) {
//        oldObject_prenda.setCantidad(Double.valueOf(request.getParameter("editCantidad")));
//      }
//      if (oldObject_servicio.getPrecio().compareTo(Double.valueOf(request.getParameter("editPrecio")))!=0) {
//        oldObject_servicio.setPrecio(Double.valueOf(request.getParameter("editPrecio")));
//      }
      if (!oldObject_prenda.getTipoDePrendaId().equals(tipo_prenda)) {
        oldObject_prenda.setTipoDePrendaId(tipo_prenda);
      }
      if (!oldObject_prenda.getPersonaId().equals(persona)) {
        oldObject_prenda.setPersonaId(persona);
      }
      oldObject_prenda.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("La prenda actualizada es: "
          + oldObject_prenda.getId() + " - " + oldObject_prenda.getMarca()+ " - "
          + oldObject_prenda.getEstado() + " - " + oldObject_prenda.getTipoDePrendaId().getDescripcion() + " - "
          + oldObject_prenda.getCreatedAt() + " - " + oldObject_prenda.getUpdatedAt() + " - "
          + oldObject_prenda.getDetalleFacturaCollection());

      jpac_obj_prenda.edit(oldObject_prenda);

      PrendaListServlet call = new PrendaListServlet();
      call.processRequest(request, response);

    } catch (Exception theException) {
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
