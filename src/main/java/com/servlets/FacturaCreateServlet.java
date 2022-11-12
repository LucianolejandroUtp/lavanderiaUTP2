/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.CategoriaJpaController;
import com.dao.FacturaJpaController;
import com.dao.PersonaJpaController;
import com.dao.ServicioJpaController;
import com.dto.Categoria;
import com.dto.Factura;
import com.dto.Persona;
import com.dto.Servicio;
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
@WebServlet(name = "FacturaCreateServlet", urlPatterns = {"/FacturaCreateServlet"})
public class FacturaCreateServlet extends HttpServlet {

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
    
    System.out.println("Bandera servlet create Factura");
    try {
      FacturaJpaController jpac_object_factura = new FacturaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_object_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Factura mi_objeto_factura = new Factura();
      Persona mi_objeto_persona = new Persona();

      SimpleDateFormat sdf_fecha = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf_hora = new SimpleDateFormat("HH:mm:ss");
      
      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);
      
//      Obteniendo el departamento en base al Id obtenido de la vista
      mi_objeto_persona = jpac_object_persona.findPersona(Long.valueOf(request.getParameter("addPersonaId")));
      System.out.println("La Persona obtenida fue: " + mi_objeto_persona.getNombres()+" - "+ mi_objeto_persona.getId());

//      Llenando los parámetros del distrito obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_factura.setNumero(request.getParameter("addNumero"));
      mi_objeto_factura.setSerie(request.getParameter("addSerie"));
      mi_objeto_factura.setTipo(request.getParameter("addTipo"));
      
      System.out.println(request.getParameter("addFecha"));
      System.out.println(request.getParameter("addHora"));
      
      String fecha1, hora1;
      fecha1 = request.getParameter("addFecha");
      hora1 = request.getParameter("addHora");
      
      Date date_fecha, date_hora;
      date_fecha = sdf_fecha.parse(fecha1);
      date_hora = sdf_hora.parse(hora1);
      
      mi_objeto_factura.setFecha(date_fecha);
      mi_objeto_factura.setHora(date_hora);
      mi_objeto_factura.setPersonaId(mi_objeto_persona);
      mi_objeto_factura.setEstado("activo");
      mi_objeto_factura.setCreatedAt(ts);
      mi_objeto_factura.setUpdatedAt(ts);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpac_object_factura.create(mi_objeto_factura);

//      Llamando al listALGO.jsp
      FacturaListServlet call = new FacturaListServlet();
      call.processRequest(request, response);
//      response.sendRedirect("Distrito/List.jsp").forward(request, response);

    } catch (IOException | ServletException theException) {
      System.out.println(theException);
    } catch (ParseException ex) {
      Logger.getLogger(FacturaCreateServlet.class.getName()).log(Level.SEVERE, null, ex);
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
