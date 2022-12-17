/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DireccionJpaController;
import com.dao.DistritoJpaController;
import com.dao.PersonaJpaController;
import com.dao.TelefonoJpaController;
import com.dao.TipoPersonaJpaController;
import com.dto.Direccion;
import com.dto.Distrito;
import com.dto.Persona;
import com.dto.Telefono;
import com.dto.TipoPersona;
import com.email.SendEmailServlet;
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
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author desti
 */
@WebServlet(name = "PersonaCreateServlet", urlPatterns = {"/PersonaCreateServlet"})
public class PersonaCreateServlet extends HttpServlet {

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

    System.out.println("Bandera servlet create Persona");
    System.out.println(request.getParameter("addNombres"));
    System.out.println(request.getParameter("addApellidos"));
    System.out.println(request.getParameter("addDni"));
    System.out.println(request.getParameter("addTelefono"));
    System.out.println(request.getParameter("addDireccion"));
    System.out.println(request.getParameter("addReferencia"));
    System.out.println(request.getParameter("addDistritoId"));
    System.out.println(request.getParameter("addEmail"));
    System.out.println(request.getParameter("addPassword"));
    System.out.println(request.getParameter("addTdPersonaId"));
    try {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU");
      PersonaJpaController jpacPersona = new PersonaJpaController(emf);
      TipoPersonaJpaController jpacTdP = new TipoPersonaJpaController(emf);
      TelefonoJpaController jpacTelefono = new TelefonoJpaController(emf);
      DistritoJpaController jpacDistrito = new DistritoJpaController(emf);
      DireccionJpaController jpacDireccion = new DireccionJpaController(emf);
      Persona miPersona = new Persona();
      TipoPersona miTdP = new TipoPersona();
      Telefono miTelefono = new Telefono();
      Direccion miDireccion = new Direccion();
      Distrito miDistrito = new Distrito();
      List<Persona> miListaDePersonas = new ArrayList<>();
      List<Distrito> miListaDeDistritos = new ArrayList<>();
      String contrasenia = null;
      BasicPasswordEncryptor bpe = new BasicPasswordEncryptor();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);

//      System.out.println("Tipo de persona: "+request.getParameter("addTdPersonaId"));
      if (request.getParameter("addTdPersonaId") == null) {
        System.out.println("Tipo de persona vacío, viene del register");
        miTdP = jpacTdP.findTipoPersona(Long.valueOf(2));
        System.out.println("El Tipo de Persona obtenido fue: " + miTdP.getDescripcion() + " - " + miTdP.getId());

      } else {//Llenando datos  que únicamente se reciben desde Persona.jsp
//      Obteniendo el Tipo dePpersona en base al Id obtenido de la vista
        miTdP = jpacTdP.findTipoPersona(Long.valueOf(request.getParameter("addTdPersonaId")));
        System.out.println("El Tipo de Persona obtenido fue: " + miTdP.getDescripcion() + " - " + miTdP.getId());
      }

//      Llenando los parámetros independientes del tipo de persona obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      miPersona.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      miPersona.setNombres(request.getParameter("addNombres"));
      miPersona.setApellidos(request.getParameter("addApellidos"));
      miPersona.setDni(request.getParameter("addDni"));
      miPersona.setEmail(request.getParameter("addEmail"));

      contrasenia = bpe.encryptPassword(String.valueOf(request.getParameter("addPassword")));
      miPersona.setPassword(contrasenia);

      miPersona.setEstado("activo");
      miPersona.setTipoPersonaId(miTdP);
      miPersona.setCreatedAt(ts);
      miPersona.setUpdatedAt(ts);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpacPersona.create(miPersona);

      miListaDePersonas = jpacPersona.findPersonaEntities();
      miDistrito = jpacDistrito.findDistrito(Long.valueOf(request.getParameter("addDistritoId")));
      
      for (Persona per : miListaDePersonas) {
        System.out.println("Persona:" + per);
        if (per.getUniqueId().equals(miPersona.getUniqueId())) {
          System.out.println("Persona encontrada!");

          miTelefono.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
          miTelefono.setDescripcion(request.getParameter("addTelefono"));
          miTelefono.setPersonaId(per);
          miTelefono.setEstado("activo");
          miTelefono.setCreatedAt(ts);
          miTelefono.setUpdatedAt(ts);

          jpacTelefono.create(miTelefono);
          
          miDireccion.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
          miDireccion.setDescripcion(request.getParameter("addDireccion"));
          miDireccion.setReferencia(request.getParameter("addReferencia"));
          miDireccion.setDistritoId(miDistrito);
          miDireccion.setPersonaId(per);
          miDireccion.setEstado("activo");
          miDireccion.setCreatedAt(ts);
          miDireccion.setUpdatedAt(ts);
          
          jpacDireccion.create(miDireccion);
        }
      }

      if (request.getParameter("addTdPersonaId") == null) {
        request.getRequestDispatcher("/EmailRegistroPersonaServlet").include(request, response);
        response.sendRedirect("auth/login.jsp");
      } else {
//      Llamando al listALGO.jsp
        PersonaListServlet call = new PersonaListServlet();
        call.processRequest(request, response);
      }
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
