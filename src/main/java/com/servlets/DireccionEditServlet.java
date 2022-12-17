/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DireccionJpaController;
import com.dao.DistritoJpaController;
import com.dao.PersonaJpaController;
import com.dto.Direccion;
import com.dto.Distrito;
import com.dto.Persona;
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
@WebServlet(name = "DireccionEditServlet", urlPatterns = {"/DireccionEditServlet"})
public class DireccionEditServlet extends HttpServlet {

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

    System.out.println("Entrando a Direccion Edit Servlet");
    System.out.println(request.getParameter("editId"));
    System.out.println(request.getParameter("editDescripcion"));
    System.out.println(request.getParameter("editReferencia"));
    System.out.println(request.getParameter("editEstado"));
    System.out.println(request.getParameter("editDistritoId"));
    System.out.println(request.getParameter("editPersonaId"));
    try {
//      Inicialización de objetos
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");

      DireccionJpaController jpacDireccion = new DireccionJpaController(emf);
      DistritoJpaController jpacDistrito = new DistritoJpaController(emf);
      PersonaJpaController jpacPersona = new PersonaJpaController(emf);
      Direccion oldObjDireccion;
      Distrito miDistrito;
      Persona miPersona;

//      Lo relacionado a la fecha
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      Necesitamos una lista de los Direcciones
//      DireccionJpaController jpac_xa_lista_de_Direcciones = new DireccionJpaController();
//      List<Direccion> mi_lista_de_Direcciones = new ArrayList<>();
//      mi_lista_de_Direcciones = jpac_xa_lista_de_Direcciones.findDireccionEntities();
//      Obteniendo el objeto con foreign key en base al Id que nos da la vista
      miDistrito = jpacDistrito.findDistrito(Long.valueOf(request.getParameter("editDistritoId")));
      miPersona = jpacPersona.findPersona(Long.valueOf(request.getParameter("editPersonaId")));

      //  Ahora necesitamos obtener el objeto a editar para chancar los nuevos valores encima
      oldObjDireccion = jpacDireccion.findDireccion(Long.valueOf(request.getParameter("editId")));
      System.out.println("La Dirección obtenida es: " + oldObjDireccion);

//      Comparando y asignando nuevos valores al objeto
      if (oldObjDireccion.getDescripcion() == null || !oldObjDireccion.getDescripcion().equals(request.getParameter("editDescripcion"))) {
        oldObjDireccion.setDescripcion(request.getParameter("editDescripcion"));
      }
      if (oldObjDireccion.getReferencia() == null || !oldObjDireccion.getReferencia().equals(request.getParameter("editReferencia"))) {
        oldObjDireccion.setReferencia(request.getParameter("editReferencia"));
      }

      if (oldObjDireccion.getEstado() == null || !oldObjDireccion.getEstado().equals(request.getParameter("editEstado"))) {
        oldObjDireccion.setEstado(request.getParameter("editEstado"));
      }
      if (!oldObjDireccion.getDistritoId().equals(miDistrito)) {
        oldObjDireccion.setDistritoId(miDistrito);
      }
      if (!oldObjDireccion.getPersonaId().equals(miPersona)) {
        oldObjDireccion.setPersonaId(miPersona);
      }
      oldObjDireccion.setUpdatedAt(ts);
//      oldObject_distrito.setDireccionCollection(mi_lista_de_Direcciones);

      System.out.println("El Servicio actualizado es: "
          + oldObjDireccion.getId() + " - " + oldObjDireccion.getDescripcion() + " - "
          + oldObjDireccion.getEstado() + " - " + oldObjDireccion.getDistritoId().getDescripcion() + " - "
          + oldObjDireccion.getCreatedAt() + " - " + oldObjDireccion.getUpdatedAt());

      jpacDireccion.edit(oldObjDireccion);

      DireccionListServlet call = new DireccionListServlet();
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
