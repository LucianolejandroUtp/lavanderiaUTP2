/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

/**
 *
 * @author Acer
 */
public class Conexion {
    
    Connection con;
    public Conexion(){
        
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/lavanderiautpmigrations","root","");
            out.print("Conexion en Linea  ");
            }catch(Exception ex){
            out.print("Error:  !!!!!!!!!!!!!!  "+ex.getMessage());
            }
    }
    public Connection getCon(){
        return con;
    }
}
