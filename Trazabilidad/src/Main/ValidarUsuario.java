
package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ValidarUsuario {
    
    private ConexionBD connection;
    
    public boolean validarCredencial(String usuario, String contraseña){
        
        boolean encontrado = false;
        PreparedStatement preparedStatement = null;
        ResultSet resultado = null;
        String query = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
        
        try(PreparedStatement preparedStatemente = connection.prepareStatement(query)){
            
            //Consulta
            //preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contraseña);
            
            //Ejecutar la consulta
            resultado = preparedStatement.executeQuery();
            
            
            }catch (SQLException e){
                    e.printStackTrace();
            }finally{
                try{
                    if (resultado != null){
                    resultado.close();
                    }
                    if (preparedStatement != null){
                    preparedStatement.close();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return encontrado;
        }
    
}
