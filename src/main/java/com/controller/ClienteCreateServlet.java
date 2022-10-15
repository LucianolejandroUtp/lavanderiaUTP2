/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import com.dao.ClienteJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Cliente;
/**
 *
 * @author desti
 */
@WebServlet(name = "ClienteCreateServlet", urlPatterns = {"/ClienteCreateServlet"})
public class ClienteCreateServlet extends HttpServlet {

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
        System.out.println("Bandera Entrada a Servlet: ClienteCreateServlet");
        try {
            
            LocalDate myObj = LocalDate.now(); // Create a date object
            System.out.println(myObj); // Display the current date
            Date dt = new Date();
            Timestamp ts = new Timestamp(dt.getTime());
            System.out.println(ts);
            
            Cliente cli = new Cliente();
//            cli.setIdCliente(Long.valueOf(9));
            cli.setIdCliente(89);
            cli.setNombre(request.getParameter("nombre"));
            cli.setApellido(request.getParameter("apellido"));
            cli.setDni(44444444);
            cli.setCorreo("algo@gg.com");
//            cli.setCreatedAt(ts);
//            cli.setUpdatedAt(ts);
            
            System.out.println(cli.getNombre()+cli.getApellido());
            
            ClienteJpaController cjpac = new ClienteJpaController();
            cjpac.create(cli);

        } catch (Throwable theException) {
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
