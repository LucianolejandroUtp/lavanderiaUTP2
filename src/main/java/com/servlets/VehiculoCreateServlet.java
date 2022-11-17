/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.DepartamentoJpaController;
import com.dao.DistritoJpaController;
import com.dao.PersonaJpaController;
import com.dao.VehiculoJpaController;
import com.dto.Departamento;
import com.dto.Distrito;
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
 * @author Acer
 */
@WebServlet(name = "VehiculoCreateServlet", urlPatterns = {"/VehiculoCreateServlet"})
public class VehiculoCreateServlet extends HttpServlet {

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
    System.out.println("Bandera servlet create vehiculo");
    try {
      VehiculoJpaController jpac_object_vehiculo = new VehiculoJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      PersonaJpaController jpac_object_persona = new PersonaJpaController(Persistence.createEntityManagerFactory("com.lav_lavanderia115_war_1.0PU"));
      Vehiculo mi_objeto_vehiculo = new Vehiculo();
      Persona mi_objeto_persona = new Persona();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);
      
//      Obteniendo el Vehiculo en base al Id obtenido de la vista
      mi_objeto_persona = jpac_object_persona.findPersona(Long.valueOf(request.getParameter("addNombreId")));
      System.out.println("El Vehiculo obtenido fue: " + mi_objeto_vehiculo.getPlaca() +" - "+ mi_objeto_vehiculo.getModelo());

//      Llenando los parámetros del vehiculo obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_vehiculo.setUniqueId(String.valueOf(java.util.UUID.randomUUID()));
      mi_objeto_vehiculo.setPlaca(request.getParameter("addPlaca"));
      mi_objeto_vehiculo.setMarca(request.getParameter("addMarca"));
      mi_objeto_vehiculo.setModelo(request.getParameter("addModelo"));
      mi_objeto_vehiculo.setEstado("activo");
      mi_objeto_vehiculo.setPersonaId(mi_objeto_persona);
      mi_objeto_vehiculo.setCreatedAt(ts);
      mi_objeto_vehiculo.setUpdatedAt(ts);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpac_object_vehiculo.create(mi_objeto_vehiculo);

//      Llamando al listALGO.jsp
      VehiculoListServlet call = new VehiculoListServlet();
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
