/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets;

import com.dao.CitaJpaController;
import com.dao.PersonaJpaController;
import com.dao.TipoPersonaJpaController;
import com.dao.VehiculoJpaController;
import com.dto.Cita;
import com.dto.Persona;
import com.dto.Vehiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "CitaCreateServlet", urlPatterns = {"/CitaCreateServlet"})
public class CitaCreateServlet extends HttpServlet {

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
        System.out.println("Bandera servlet create Cita");
    try {
      CitaJpaController jpac_object_cita = new CitaJpaController();
      PersonaJpaController jpac_object_persona = new PersonaJpaController();
      VehiculoJpaController jpac_object_vehiculo = new VehiculoJpaController();
      
      Cita mi_objeto_cita = new Cita();
      Persona mi_objeto_persona = new Persona();
      Vehiculo mi_objeto_vehiculo = new Vehiculo();

      Date dt = new Date();
      Timestamp ts = new Timestamp(dt.getTime());
      System.out.println(ts);
      
      
      String date = request.getParameter("addFecha");
      String times = request.getParameter("addHora");
      
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
      
      Date testDate = null;
      Date time = null;
      try{
            testDate = df.parse(date);
            time = sdf.parse(times);
            System.out.println("Ahora hemos creado un objeto date con la fecha indicada, "+testDate);
        } catch (Exception e){ System.out.println("invalid format");}
      


      
      
      
      
//      Obteniendo el cliente en base al Id obtenido de la vista
      mi_objeto_persona = jpac_object_persona.findPersona(Long.valueOf(request.getParameter("addNombreId")));
      System.out.println("El nombre obtenido fue: " + mi_objeto_vehiculo.getPlaca() +" - "+ mi_objeto_vehiculo.getModelo());
      
      mi_objeto_persona = jpac_object_persona.findPersona(Long.valueOf(request.getParameter("addApellidoId")));
      System.out.println("El apellido obtenido fue: " + mi_objeto_vehiculo.getPlaca() +" - "+ mi_objeto_vehiculo.getModelo());
      
      mi_objeto_vehiculo = jpac_object_vehiculo.findVehiculo(Long.valueOf(request.getParameter("addPlacaId")));
      System.out.println("El apellido obtenido fue: " + mi_objeto_vehiculo.getPlaca() +" - "+ mi_objeto_vehiculo.getModelo());

//      Llenando los parámetros del vehiculo obtenidos de la vista
//            mi_distrito.setIdTelefono(566);                        //No necesario, tiene auto_increment
      mi_objeto_cita.setFecha(testDate);
      mi_objeto_cita.setHora(time);
      mi_objeto_cita.setVehiculoId(mi_objeto_vehiculo);
      mi_objeto_cita.setEstado("activo");
      mi_objeto_cita.setPersonaId(mi_objeto_persona);
      mi_objeto_cita.setCreatedAt(ts);
      mi_objeto_cita.setUpdatedAt(ts);

//      Llamando al método crear del controlador y pasándole el objeto Distrito
      jpac_object_cita.create(mi_objeto_cita);

//      Llamando al listALGO.jsp
      CitaListServlet call = new CitaListServlet();
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
