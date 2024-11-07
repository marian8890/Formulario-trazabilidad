
package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FormularioFin extends JFrame {
    
    JPanel panel;
    JLabel id_Formulario, horaIni, horaFin, chofer, vehiculo, nombre, datosCarga, apellido, dni, linea, dominio, kmIni, kmFin, kmProg, odometro, cargaEnviada, pallets, sacas, piezas, observac; 
    JTextField ID, NOMBRE, APELLIDO, DNI, DOMINIO, KM_INI, KM_FIN, HORA_INI, HORA_FIN, KM_PROG, PALLETS, SACAS, PIEZAS, OBSERVAC; 
    JComboBox LINEA;
    JButton atras, finalizar, imprimir;
    Connection connection = ConexionBD.getConnection();
    
    public FormularioFin(){
        setBounds(50,50,800,1020);
        setTitle("Formulario de finalización de trazabilidad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        colocarPanel();
        colocarLabels();
        colocarTextFields();
        colocarComboBox();
        colocarBotones();
    }
    
    private void colocarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
    }
    
    private void colocarLabels(){
        id_Formulario = new JLabel ("FORMULARIO NÚMERO: ");
        chofer = new JLabel("DATOS CHOFER");
        nombre = new JLabel("Nombre:");
        apellido = new JLabel("Apellido:");
        dni = new JLabel("DNI:");
        vehiculo = new JLabel("DATOS VEHÍCULO");
        linea = new JLabel("Número de línea:");
        dominio = new JLabel("Dominio:");
        odometro = new JLabel("ODÓMETRO");
        kmIni = new JLabel("Km de inicio:");
        kmFin = new JLabel("Km de finalización:");
        cargaEnviada = new JLabel("CARGA RECIBIDA");
        pallets = new JLabel("Cant. de pallets:");
        sacas = new JLabel("Cant. de sacas:");
        piezas = new JLabel("Cant. de piezas:");
        observac = new JLabel("Observaciones:");
        horaIni = new JLabel("Horario de Inicio: ");
        horaFin = new JLabel ("Horario de Finaliazción: ");
        
        id_Formulario.setBounds(100, 0, 200, 35);
        chofer.setBounds(80, 20, 100, 35);
        nombre.setBounds(80, 70, 100, 35);
        apellido.setBounds(80, 120, 100, 35);
        dni.setBounds(80, 170, 100, 35);
        vehiculo.setBounds(80, 220, 220, 35);
        linea.setBounds(80, 270, 100, 35);
        dominio.setBounds(80, 320, 100, 35);
        odometro.setBounds(80, 410, 100, 35);
        kmIni.setBounds(80, 460, 100, 35);
        kmFin.setBounds(360, 460, 140, 35);
        horaIni.setBounds(80, 510, 100, 35);
        horaFin.setBounds(360, 510, 140, 35);
        cargaEnviada.setBounds(80, 590, 100, 35);
        pallets.setBounds(80, 640, 100, 35);
        sacas.setBounds(80, 690, 100, 35);
        piezas.setBounds(80, 740, 100, 35);
        observac.setBounds(80, 820, 100, 35);
        
        panel.add(id_Formulario);
        panel.add(horaIni);
        panel.add(horaFin);
        panel.add(chofer);
        panel.add(nombre);
        panel.add(apellido);
        panel.add(dni);
        panel.add(vehiculo);
        panel.add(linea);
        panel.add(dominio);
        panel.add(odometro);
        panel.add(kmFin);
        panel.add(kmIni);
        panel.add(cargaEnviada);
        panel.add(pallets);
        panel.add(sacas);
        panel.add(piezas);
        panel.add(observac);
        
    }
    
    private void colocarTextFields(){
        ID = new JTextField();
        NOMBRE = new JTextField();
        APELLIDO = new JTextField();
        DNI = new JTextField();
        DOMINIO = new JTextField();
        KM_INI = new JTextField();
        KM_FIN = new JTextField();
        HORA_INI = new JTextField();
        HORA_FIN = new JTextField();
        OBSERVAC = new JTextField();
        PALLETS = new JTextField();
        SACAS = new JTextField();
        PIEZAS = new JTextField();
        
        ID.setBounds(180, 0, 100, 35);
        NOMBRE.setBounds(180, 70, 480, 35);
        APELLIDO.setBounds(180, 120, 480, 35);
        DNI.setBounds(180, 170, 480, 35);
        DOMINIO.setBounds(180, 320, 480, 35);
        KM_INI.setBounds(180, 460, 130, 35);
        KM_FIN.setBounds(500, 460, 130, 35);
        HORA_INI.setBounds(180, 510, 130, 35);
        HORA_FIN.setBounds(500, 510, 130, 35);
        OBSERVAC.setBounds(180, 800, 480, 75);
        PALLETS.setBounds(180, 640, 50, 35);
        SACAS.setBounds(180, 690, 50, 35);
        PIEZAS.setBounds(180, 740, 50, 35);
        
        panel.add(ID);
        panel.add(NOMBRE);
        panel.add(APELLIDO);
        panel.add(DNI);
        panel.add(DOMINIO);
        panel.add(KM_INI);
        panel.add(KM_FIN);
        panel.add(HORA_INI);
        panel.add(HORA_FIN);
        panel.add(OBSERVAC);
        panel.add(PALLETS);
        panel.add(SACAS);
        panel.add(PIEZAS);
    }
    
    private void colocarComboBox(){
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        
        //Añadir opciones al modelo
        modelo.addElement("LTC 18 - SAN JUAN - MENDOZA - SAN JUAN");
        modelo.addElement("LTN 03 - BUENOS AIRES - MENDOZA - BUENOS AIRES");
        modelo.addElement("MDZ 01 - SAN RAFAEL - GENERAL ALVEAR");
        modelo.addElement("MDZ 02 - VALLE DE UCO");
        modelo.addElement("MDZ 08 - MALARGUE");
        modelo.addElement("MDZ 105 - COLECTA UNIDADES POSTALES DE MAIPÚ");
        modelo.addElement("MDZ 106 - RODEO DEL MEDIO");
        modelo.addElement("MDZ 114 - DORREGO - VILLA NUEVA");
        modelo.addElement("MDZ 123 - RODEO DE LA CRUZ - BERMEJO");
       
        
        //Junto ComboBox con modelo
        LINEA = new JComboBox<>(modelo);
        
        LINEA.setBounds(180, 270, 480, 35);
        
        panel.add(LINEA);
    }
    
    private void colocarBotones(){
        finalizar = new JButton("Finalizar");
        finalizar.setBounds(505, 890, 100, 38);
        finalizar.addActionListener(e -> enviarDatos());
        
        imprimir = new JButton("Imprimir");
        imprimir.setBounds(610, 890, 100, 38);
        imprimir.addActionListener(e -> imprimirFormulario());
        
        atras = new JButton("Atrás");
        atras.setBounds(400, 890, 100, 38);
        atras.addActionListener(e -> irAtras());
        
        panel.add(finalizar);
       // panel.add(imprimir);
        panel.add(atras);
    }
    
    private void enviarDatos(){
        
        try (Connection connection = ConexionBD.getConnection()) {  
                String nombreChofer = NOMBRE.getText();  
                String apellidoChofer = APELLIDO.getText();  
                String dniChofer = DNI.getText();  
                String numeroDeLinea = (String)LINEA.getSelectedItem();  
                String dominioVehiculo = DOMINIO.getText();  
                int kmInicio = Integer.parseInt(KM_INI.getText()); 
                int kmFin = Integer.parseInt(KM_FIN.getText());
                String horaInicio = HORA_INI.getText();
                String horaFin = HORA_FIN.getText();
                int cantPalets = Integer.parseInt(PALLETS.getText());  
                int cantSacas = Integer.parseInt(SACAS.getText());  
                int cantPiezas = Integer.parseInt(PIEZAS.getText());  
                String Observaciones = OBSERVAC.getText();  

                if (!nombreChofer.matches("[a-zA-Z]+")) {  
                    JOptionPane.showMessageDialog(null, "El nombre del chofer solo puede contener letras.");  
                    return; // Salir del método si la validación falla  
                }  
                if (!apellidoChofer.matches("[a-zA-Z]+")) {  
                    JOptionPane.showMessageDialog(null, "El apellido del chofer solo puede contener letras.");  
                    return; // Salir del método si la validación falla  
                }  
                
                if (!dniChofer.matches("\\d+")) {  
                    JOptionPane.showMessageDialog(null, "El DNI solo puede contener números.");  
                    return; // Salir del método si la validación falla  
                }   

                String sql = "INSERT INTO formulario_fin (Nombre_chofer, Apellido_chofer, dni_chofer, linea_vehiculo, dominio_vehiculo, km_inicio, km_fin, horaIni, horaFin, Pallets, Sacas, Piezas, Observaciones) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";  


                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  

                statement.setString(1, nombreChofer);  
                statement.setString(2, apellidoChofer);  
                statement.setString(3, dniChofer);  
                statement.setString(4, numeroDeLinea);  
                statement.setString(5, dominioVehiculo);  
                statement.setInt(6, kmInicio);  
                statement.setInt (7, kmFin);
                statement.setString(8, horaInicio); 
                statement.setString(9, horaFin);
                statement.setInt(10, cantPalets);  
                statement.setInt(11, cantSacas);  
                statement.setInt(12, cantPiezas);  
                statement.setString(13, Observaciones);  

                int rowsAffected = statement.executeUpdate();  

                if (rowsAffected > 0) {  
                    // Obtener el ID generado  
                    ResultSet generatedKeys = statement.getGeneratedKeys();  
                    if (generatedKeys.next()) {  
                        int idFormulario = generatedKeys.getInt(1); // Asumiendo que el ID es el primer campo  
                        // Mostrar el nuevo ID en tu panel  
                        ID.setText(String.valueOf(idFormulario));   
                        JOptionPane.showMessageDialog(null, "Los datos han sido ingresados correctamente. ID Formulario: " + idFormulario);  
                        
                        // Limpiar los campos  
                        NOMBRE.setText("");  
                        APELLIDO.setText("");  
                        DNI.setText("");  
                        LINEA.setSelectedIndex(0); // Si es un JComboBox, seleccionar el primer elemento (puedes ajustar esto)  
                        DOMINIO.setText("");  
                        KM_INI.setText("");  
                        KM_FIN.setText("");  
                        HORA_INI.setText("");  
                        HORA_FIN.setText("");  
                        PALLETS.setText("");  
                        SACAS.setText("");  
                        PIEZAS.setText("");  
                        OBSERVAC.setText("");  
                        ID.setText(""); //
                    }  
                } else {  
                    JOptionPane.showMessageDialog(null, "No se pudo insertar los datos.");  
                }  
            } catch (SQLException e) {  
                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage());  
            } catch (NumberFormatException e) {  
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos: " + e.getMessage());  
            } catch (Exception e) {  
                JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());  
            }
        
    }
    
    private void imprimirFormulario(){
        
    }
    
    private void irAtras(){
        InicioFin panelInicioFin = new InicioFin();
        panelInicioFin.setVisible(true);
        dispose();
    }
}
