
package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConexionBD {
    
    
   private static String url = "jdbc:mysql://localhost:3306/trazabilidad";
   private static String usu = "root" ;
   private static String contraseñaa = "root";
   
   public static Connection getConnection(){
       Connection connection = null;
       try{
           connection = DriverManager.getConnection(url, usu, contraseñaa);
           
       }catch (SQLException e){
           JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
       }
       return connection;
   
}

    PreparedStatement prepareStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
