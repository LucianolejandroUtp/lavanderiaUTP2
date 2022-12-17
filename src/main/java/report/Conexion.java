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
        
        
        try{
            Connection con = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/lavanderiautpmigrations","root","");
            out.println("Conexion en Linea  !!");
            }catch(Exception ex){
            out.print("Error:  !!!!!!!!!!!!!!  "+ex.getMessage());
            }
    }
    public Connection getCon(){
        return con;
    }
}
