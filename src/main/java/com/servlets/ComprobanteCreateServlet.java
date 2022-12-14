/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.ComprobanteJpaController;
import com.dao.PersonaJpaController;
import com.dto.Comprobante;
import com.dto.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ComprobanteCreateServlet", urlPatterns = {"/ComprobanteCreateServlet"})
public class ComprobanteCreateServlet extends HttpServlet {

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
    
    System.out.println("Bandera servlet create Comprobante");
    try {
      ComprobanteJpaController jpac_obj_comprobante = new ComprobanteJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_obj_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Comprobante mi_objeto_comprobante = new Comprobante();
      Persona mi_objeto_persona = new Persona();

      SimpleDateFormat sdf_fecha = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf_hora = new SimpleDateFormat("HH:mm");
      
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);
      
//      Obteniendo el departamento en base al Id obtenido de la vista
      mi_objeto_persona = jpac_obj_persona.findPersona(Long.valueOf(request.getParameter("addPersonaId")));
      System.out.println("La Persona obtenida fue: " + mi_objeto_persona.getNombres()+" - "+ mi_objeto_persona.getId());

//      Llenando los par??metros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_comprobante.setNumero(request.getParameter("addNumero"));
      mi_objeto_comprobante.setSerie(request.getParameter("addSerie"));
      mi_objeto_comprobante.setTipo(request.getParameter("addTipo"));
      
      System.out.println(request.getParameter("addFecha"));
      System.out.println(request.getParameter("addHora"));
      
      String fecha1, hora1;
      fecha1 = request.getParameter("addFecha");
      hora1 = request.getParameter("addHora");
      
      Date date_fecha, date_hora;
      date_fecha = sdf_fecha.parse(fecha1);
      date_hora = sdf_hora.parse(hora1);
      
      mi_objeto_comprobante.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      mi_objeto_comprobante.setFecha(date_fecha);
      mi_objeto_comprobante.setHora(date_hora);
      mi_objeto_comprobante.setPersonaId(mi_objeto_persona);
      mi_objeto_comprobante.setEstado("activo");
      mi_objeto_comprobante.setCreatedAt(ts);
      mi_objeto_comprobante.setUpdatedAt(ts);

//      Llamando al m??todo crear del controlador y pas??ndole el objeto Distrito
      jpac_obj_comprobante.create(mi_objeto_comprobante);

//      Llamando al listALGO.jsp
      ComprobanteListServlet call = new ComprobanteListServlet();
      call.processRequest(request, response);
//      response.sendRedirect("Distrito/List.jsp").forward(request, response);

    } catch (IOException | ServletException theException) {
      System.out.println(theException);
    } catch (ParseException ex) {
      Logger.getLogger(ComprobanteCreateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
