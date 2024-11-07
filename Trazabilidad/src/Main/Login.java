
package Main;

import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Login extends JFrame{
    
    JPanel panel;
    JLabel usu, password;
    JTextField usuario, contraseña;
    JButton btnIngresar, btnRegistrarse;
    Connection connection = ConexionBD.getConnection();
    ValidarUsuario validarUsuario = new ValidarUsuario();
    
    public Login (){
        setBounds(50,50,600,350);
        setTitle("Ingresar al Sistema");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        colocarPanel();
        colocarLabels();
        colocarTextFields();
        colocarBotones();
    }
    
    private void colocarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
    }
    
    private void colocarLabels(){
        usu = new JLabel("Usuario: ");
        password = new JLabel ("Contraseña: ");
        
        usu.setBounds(100, 40, 100, 35);
        password.setBounds(100, 110, 100, 35);
        
        panel.add(password);
        panel.add(usu);
    }
    
    private void colocarTextFields(){
        usuario = new JTextField();
        //password = new JTextField();
        contraseña = new javax.swing.JPasswordField();
        
        usuario.setBounds(180, 40, 300, 40);
        contraseña.setBounds(180, 110, 300, 40);
        
        panel.add(usuario);
        panel.add(contraseña);
    }
    
    private void colocarBotones(){
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(180, 220, 140, 40);
        btnIngresar.addActionListener(e -> btnIngresarAlSistema());
        
        
        btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setBounds(340, 220, 140, 40);
        btnRegistrarse.addActionListener(e -> registrarUsuario());
        
        panel.add(btnIngresar);
        panel.add(btnRegistrarse);
    }
    
    private void btnIngresarAlSistema(){
        String c = contraseña.getText();
        String u = usuario.getText();
        
        if(!u.isEmpty()){
            if(!c.isEmpty()){
                if(ValidarContraseña(c)){
                    String querySelect = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
                    try(PreparedStatement stmtSelect = connection.prepareStatement(querySelect)){
                        stmtSelect.setString(1, u);
                        stmtSelect.setString(2, hashPassword(c) );
                        ResultSet rS = stmtSelect.executeQuery();
                        if(rS.next()){
                            JOptionPane.showMessageDialog(null, "Los datos ingresados son correctos");
                            InicioFin inicioFin = new InicioFin();
                            inicioFin.setVisible(true);
                            dispose();
                        } else
                            JOptionPane.showMessageDialog(null, "El usuario ingresado no existe. \n Debe registarse");
                    }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, "Error al validar usuario" + e);
                 }    
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña no válida");
                  }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese una nueva contraseña");
              }
        } else {
            JOptionPane.showMessageDialog(this, "El campo no puede estar vacío");
          }
    }
    
    private void registrarUsuario(){
        Registro registrar = new Registro();
        registrar.setVisible(true);
        dispose();
    }
    
    public static boolean ValidarContraseña(String contraseña){
     // Expresión regular para verificar la contraseña
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{6,}$";

        // Compila la expresión regular en un patrón
        Pattern pattern = Pattern.compile(regex);

        // Crea un matcher para la contraseña dada
        Matcher matcher = pattern.matcher(contraseña);
       // Devuelve verdadero si la contraseña cumple con la expresión regular
        return matcher.matches();
    }
    
    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }  
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
}
