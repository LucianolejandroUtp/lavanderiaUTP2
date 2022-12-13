/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;

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
        
        
        
        
        try {
            //Drive
            Class.forName("com.mysql.jdbc.Drive");
            con = DriverManager.getConnection("jdbc:mysql:/localhost:3306/lavanderiautpmigrations","root","");
        } catch (Exception e) {
        
        }
    }
    public Connection getCon(){
        return con;
    }
}
