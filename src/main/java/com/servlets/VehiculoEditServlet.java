/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.PersonaJpaController;
import com.dao.VehiculoJpaController;
import com.dto.Persona;
import com.dto.Vehiculo;
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
@WebServlet(name = "VehiculoEditServlet", urlPatterns = {"/VehiculoEditServlet"})
public class VehiculoEditServlet extends HttpServlet {

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
    
    System.out.println("Entrando a Vehiculo Edit Servlet");
    System.out.println(request.getParameter("editId"));
    System.out.println(request.getParameter("editPlaca"));
    System.out.println(request.getParameter("editMarca"));
    System.out.println(request.getParameter("editModelo"));
    System.out.println(request.getParameter("editEstado"));
    System.out.println(request.getParameter("editPersonaId"));
    try {
//      Inicialización de objetos
      VehiculoJpaController jpac_obj_vehiculo = new VehiculoJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Vehiculo oldObject_vehiculo;
      Persona mi_persona;

//      Lo relacionado a la fecha
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Obteniendo el objeto con foreign key en base al Id que nos da la vista
      mi_persona = jpac_obj_persona.findPersona(Long.valueOf(request.getParameter("editPersonaId")));

      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObject_vehiculo = jpac_obj_vehiculo.findVehiculo(Long.valueOf(request.getParameter("editId")));
      System.out.println("LA Persona obtenida es: " + oldObject_vehiculo);

//      Comparando y asignando nuevos valores al objeto
      if (oldObject_vehiculo.getPlaca()== null || !oldObject_vehiculo.getPlaca().equals(request.getParameter("editPlaca"))) {
        oldObject_vehiculo.setPlaca(request.getParameter("editPlaca"));
      }
      if (oldObject_vehiculo.getMarca()== null || !oldObject_vehiculo.getMarca().equals(request.getParameter("editMarca"))) {
        oldObject_vehiculo.setMarca(request.getParameter("editMarca"));
      }
      if (oldObject_vehiculo.getModelo()== null || !oldObject_vehiculo.getModelo().equals(request.getParameter("editModelo"))) {
        oldObject_vehiculo.setModelo(request.getParameter("editModelo"));
      }
      
      if (oldObject_vehiculo.getEstado() == null || !oldObject_vehiculo.getEstado().equals(request.getParameter("editEstado"))) {
        oldObject_vehiculo.setEstado(request.getParameter("editEstado"));
      }
      if (!oldObject_vehiculo.getPersonaId().equals(mi_persona)) {
        oldObject_vehiculo.setPersonaId(mi_persona);
      }
      oldObject_vehiculo.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("La Persona actualizada es: "
          + oldObject_vehiculo.getId() + " - " + oldObject_vehiculo.getPlaca()+ " - "
          + oldObject_vehiculo.getMarca()+ " - " + oldObject_vehiculo.getModelo()+ " - "
          + oldObject_vehiculo.getEstado() + " - " + oldObject_vehiculo.getPersonaId().getNombres()+ " - "
          + oldObject_vehiculo.getCreatedAt() + " - " + oldObject_vehiculo.getUpdatedAt() + " - "
          + oldObject_vehiculo.getCitaCollection());

      jpac_obj_vehiculo.edit(oldObject_vehiculo);

      VehiculoListServlet call = new VehiculoListServlet();
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
