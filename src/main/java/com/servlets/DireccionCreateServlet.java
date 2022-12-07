/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DireccionJpaController;
import com.dao.DireccionPersonaJpaController;
import com.dao.DistritoJpaController;
import com.dao.PersonaJpaController;
import com.dto.Direccion;
import com.dto.DireccionPersona;
import com.dto.Distrito;
import com.dto.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");

    System.out.println("Bandera servlet create Dirección");
    try {
      DireccionJpaController jpacDireccion = new DireccionJpaController(emf);
      DireccionPersonaJpaController jpacDirPersona = new DireccionPersonaJpaController(emf);
      DistritoJpaController jpacDistrito = new DistritoJpaController(emf);
      PersonaJpaController jpacPersona = new PersonaJpaController(emf);
      Direccion miDireccion = new Direccion();
      DireccionPersona miDirPersona = new DireccionPersona();
      Distrito miDistrito = new Distrito();
      Persona miPersona = new Persona();
      List<Persona> miListaDePersonas = new ArrayList<>();
      List<Direccion> miListaDeDirecciones = new ArrayList<>();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);
      
//      Obteniendo el objeto en base al Id obtenido de la vista
      miDistrito = jpacDistrito.findDistrito(Long.valueOf(request.getParameter("addDistritoId")));
      miPersona = jpacPersona.findPersona(Long.valueOf(request.getParameter("addPersonaId")));
      System.out.println("El Distrito obtenido fue: " + miDistrito.getDescripcion() +" - "+ miDistrito.getId());

//      Llenando los parámetros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      miDireccion.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      miDireccion.setDescripcion(request.getParameter("addDireccion"));
      miDireccion.setReferencia(request.getParameter("addReferencia"));
      miDireccion.setEstado("activo");
      miDireccion.setDistritoId(miDistrito);
      miDireccion.setCreatedAt(ts);
      miDireccion.setUpdatedAt(ts);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpacDireccion.create(miDireccion);
      
      
      
      miPersona = jpacPersona.findPersona(Long.valueOf(request.getParameter("addPersonaId")));
      
      miListaDeDirecciones = jpacDireccion.findDireccionEntities();
      
      for (Direccion temp: miListaDeDirecciones){
        if(temp.getDescripcion().equalsIgnoreCase(request.getParameter("addDireccion"))){
//          System.out.println("Dirección encontrada: "+temp.getDescripcion()+" - "+temp.getId());
          miDireccion = temp;
        }
      }
      
      System.out.println("La persona es: "+ miPersona.getNombres() + " - "+ miPersona.getId());
      System.out.println("La dirección es: "+ miDireccion.getDescripcion()+" - "+miDireccion.getId());
      
      miDirPersona.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      miDirPersona.setEstado("activo");
      miDirPersona.setCreatedAt(ts);
      miDirPersona.setUpdatedAt(ts);
      miDirPersona.setPersonaId(miPersona);
      miDirPersona.setDireccionId(miDireccion);
      
      jpacDirPersona.create(miDirPersona);
      
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
