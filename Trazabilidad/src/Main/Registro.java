
package Main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Registro extends JFrame {
    
    JPanel panel;
    JLabel usu, password, newPassword, Datos_contraseña, mayuscula, carEspecial, cantCaract;
    JTextField usuario, contraseña, repetirContraseña;
    JButton btnregistrar, btncancelar;
    Connection connection = ConexionBD.getConnection();
    
    public Registro(){
        setBounds(50,50,600,500);
        setTitle("Registrarse");
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
        usu = new JLabel("Usuario:");
        password = new JLabel("Contraseña:");
        newPassword = new JLabel("Repetir contraseña:");
        Datos_contraseña = new JLabel("La contraseña debe contener:");
        cantCaract = new JLabel ("-Al menos 6 caracteres");
        mayuscula = new JLabel ("-Al menos una mayúscula");
        carEspecial = new JLabel ("-Al menos un caracter especial");
        
        
        usu.setBounds(100, 20, 100, 35);
        password.setBounds(100, 90, 100, 35);
        newPassword.setBounds(100, 160, 120, 35);
        Datos_contraseña.setBounds(100, 235, 200, 25);
        cantCaract.setBounds(120, 250, 200, 25);
        mayuscula.setBounds(120, 265, 200, 25);
        carEspecial.setBounds(120, 280, 200, 25);
        
        panel.add(usu);
        panel.add(password);
        panel.add(newPassword);
        panel.add(Datos_contraseña);
        panel.add(cantCaract);
        panel.add(mayuscula);
        panel.add(carEspecial);
        
    }
    
    private void colocarTextFields(){
        usuario = new JTextField();
        //password = new JTextField();
        contraseña = new javax.swing.JPasswordField();
        repetirContraseña = new javax.swing.JPasswordField();
        
        usuario.setBounds(100, 50, 375, 40);
        contraseña.setBounds(100, 120, 375, 40);
        repetirContraseña.setBounds(100, 190, 375, 40);
        
        panel.add(usuario);
        panel.add(contraseña);
        panel.add(repetirContraseña);
    }
    
    private void colocarBotones(){
        btnregistrar = new JButton("Registrarse");
        btnregistrar.setBounds(150, 350, 140, 40);
        btnregistrar.addActionListener(e -> RegistrarUsuario());
        
        btncancelar = new JButton("Cancelar");
        btncancelar.setBounds(310, 350, 140, 40);
        btncancelar.addActionListener(e -> SalirDeRegistro());
        
        panel.add(btnregistrar);
        panel.add(btncancelar);
        
    }
    
    private void RegistrarUsuario(){
        String nuevoUsuario = usuario.getText();
        String p1 = contraseña.getText();
        String p2 = repetirContraseña.getText();
            
            if(!nuevoUsuario.isEmpty()){
                if(!p1.isEmpty()){
                    if(p1.equals(p2)){
                        if(ValidarContraseña(p1)){
                           //Consulta en la base de datos para ver si el usuario ya existe
                           String querySelect = "SELECT * FROM usuarios WHERE usuario = ?";
                           
                           try(PreparedStatement stmtSelect = connection.prepareStatement(querySelect)){
                               stmtSelect.setString(1, nuevoUsuario);
                               ResultSet rs = stmtSelect.executeQuery();
                               if(rs.next()){
                                   JOptionPane.showMessageDialog(null, "El usuario ya existe. \n Ingrese uno nuevo");
                               } else{
                                   //Si el usuario no existe lo agrega en la base de datos
                                   String queryInsert = "INSERT INTO usuarios (usuario, contraseña) VALUES (?,?)";
                                   System.out.println(nuevoUsuario);
                                   try (PreparedStatement stmtInsert = connection.prepareStatement(queryInsert)){
                                       stmtInsert.setString(1, nuevoUsuario);
                                       stmtInsert.setString(2, hashPassword(p1));
                                       int rowsAffected = stmtInsert.executeUpdate();
                                       
                                       if (rowsAffected > 0){
                                           JOptionPane.showMessageDialog(null, "Se ha registrado con éxito");
                                           Login ingreso = new Login();
                                           ingreso.setVisible(true);
                                           dispose();
                                        } else {
                                           JOptionPane.showMessageDialog(null, "No se ha podido registar la contraseña");
                                       }
                                       
                                   } catch (SQLException ex){
                                       JOptionPane.showMessageDialog(null,"No se pudo registrar el usuario" + ex.getMessage());
                                   }
                               }
                            }catch (SQLException e){
                                JOptionPane.showMessageDialog(null, "Error al validar el usuario");
                            }catch(Exception e){
                                
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La contraseña no pudo ser validada.");
                        }
                    }else {  
                        JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales. Por favor, inténtelo de nuevo.");  
    }  
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese una contraseña, el campo no puede estar vacío");
                }
            }else{
                JOptionPane.showMessageDialog(null, "El usuario está vacío. \n Por favor llene el campo");
            }
    }
    
    private void SalirDeRegistro(){
        Login panelLogin = new Login();
        panelLogin.setVisible(true);
        dispose();
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
    
    public static boolean ValidarContraseña (String p1){
        // Expresión regular para verificar la contraseña
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).{6,}$";

        // Compila la expresión regular en un patrón
        Pattern pattern = Pattern.compile(regex);

        // Crea un matcher para la contraseña dada
        Matcher matcher = pattern.matcher(p1);
       // Devuelve verdadero si la contraseña cumple con la expresión regular
        return matcher.matches();
    }
    
    
    
}
