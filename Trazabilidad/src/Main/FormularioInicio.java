
package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FormularioInicio extends JFrame {
    
    JPanel panel;
    JLabel id_Formulario, horaIni, chofer, vehiculo, nombre, datosCarga, apellido, dni, linea, dominio, kmIni, kmProg, odometro, cargaEnviada, pallets, sacas, piezas, observac; 
    JTextField ID, NOMBRE, APELLIDO, DNI, DOMINIO, KM_INI, HORA_INI, KM_PROG, PALLETS, SACAS, PIEZAS, OBSERVAC; 
    JComboBox LINEA;
    JButton atras, finalizar, imprimir;
    Connection connection = ConexionBD.getConnection();
    
    public FormularioInicio(){
        setBounds(50,50,800,1020);
        setTitle("Formulario de inicio de trazabilidad");
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
        //kmProg = new JLabel("Km recorrido");
        odometro = new JLabel("ODÓMETRO");
        kmIni = new JLabel("Km de Inicio:");
        cargaEnviada = new JLabel("CARGA ENVIADA");
        pallets = new JLabel("Cant. de pallets:");
        sacas = new JLabel("Cant. de sacas:");
        piezas = new JLabel("Cant. de piezas:");
        observac = new JLabel("Observaciones:");
        horaIni = new JLabel("Horario de Inicio: ");
        
        id_Formulario.setBounds(100, 0, 200, 35);
        chofer.setBounds(100, 20, 100, 35);
        nombre.setBounds(100, 70, 100, 35);
        apellido.setBounds(100, 120, 100, 35);
        dni.setBounds(100, 170, 100, 35);
        vehiculo.setBounds(100, 260, 220, 35);
        linea.setBounds(100, 310, 100, 35);
        dominio.setBounds(100, 360, 100, 35);
        //kmProg.setBounds(100, 400, 220, 35);
        odometro.setBounds(100, 460, 100, 35);
        kmIni.setBounds(100, 510, 100, 35);
        horaIni.setBounds(350, 510, 100, 35);
        cargaEnviada.setBounds(100, 590, 100, 35);
        pallets.setBounds(100, 640, 100, 35);
        sacas.setBounds(100, 690, 100, 35);
        piezas.setBounds(100, 740, 100, 35);
        observac.setBounds(100, 820, 100, 35);
        
        panel.add(id_Formulario);
        panel.add(horaIni);
        panel.add(chofer);
        panel.add(nombre);
        panel.add(apellido);
        panel.add(dni);
        panel.add(vehiculo);
        panel.add(linea);
        panel.add(dominio);
        //panel.add(kmProg);
        panel.add(odometro);
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
        HORA_INI = new JTextField();
        OBSERVAC = new JTextField();
        PALLETS = new JTextField();
        SACAS = new JTextField();
        PIEZAS = new JTextField();
        
        ID.setBounds(240, 0, 100, 35);
        NOMBRE.setBounds(200, 70, 400, 35);
        APELLIDO.setBounds(200, 120, 400, 35);
        DNI.setBounds(200, 170, 400, 35);
        DOMINIO.setBounds(200, 360, 400, 35);
        KM_INI.setBounds(200, 510, 130, 35);
        HORA_INI.setBounds(450, 510, 130, 35);
        OBSERVAC.setBounds(200, 800, 400, 75);
        PALLETS.setBounds(200, 640, 50, 35);
        SACAS.setBounds(200, 690, 50, 35);
        PIEZAS.setBounds(200, 740, 50, 35);
        
        panel.add(ID);
        panel.add(NOMBRE);
        panel.add(APELLIDO);
        panel.add(DNI);
        panel.add(DOMINIO);
        panel.add(KM_INI);
        panel.add(HORA_INI);
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
        
        LINEA.setBounds(200, 310, 400, 35);
        
        panel.add(LINEA);
    }
    
    private void colocarBotones(){
        finalizar = new JButton("Finalizar");
        finalizar.setBounds(505, 890, 100, 38);
        finalizar.addActionListener(e -> enviarDatos());
        
        imprimir = new JButton("Imprimir");
        imprimir.setBounds(610, 890, 100, 38);
        //imprimir.addActionListener(e -> imprimirFormulario());
        
        atras = new JButton("Atrás");
        atras.setBounds(400, 890, 100, 38);
        atras.addActionListener(e -> irAtras());
        
        
        panel.add(finalizar);
        //panel.add(imprimir);
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
                String horaInicio = HORA_INI.getText();  
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

                String sql = "INSERT INTO formulario_inicio (Nombre_chofer, Apellido_chofer, dni_chofer, linea_vehiculo, dominio_vehiculo, km_inicio, horaIni, Pallets, Sacas, Piezas, Observaciones) VALUES (?,?,?,?,?,?,?,?,?,?,?)";  


                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  

                statement.setString(1, nombreChofer);  
                statement.setString(2, apellidoChofer);  
                statement.setString(3, dniChofer);  
                statement.setString(4, numeroDeLinea);  
                statement.setString(5, dominioVehiculo);  
                statement.setInt(6, kmInicio);  
                statement.setString(7, horaInicio);  
                statement.setInt(8, cantPalets);  
                statement.setInt(9, cantSacas);  
                statement.setInt(10, cantPiezas);  
                statement.setString(11, Observaciones);  

                int rowsAffected = statement.executeUpdate();  

                if (rowsAffected > 0) {  
                    // Obtener el ID generado  
                    ResultSet generatedKeys = statement.getGeneratedKeys();  
                    if (generatedKeys.next()) {  
                        int idFormulario = generatedKeys.getInt(1);  
                        // Mostrar el nuevo ID en tu panel  
                        ID.setText(String.valueOf(idFormulario));  
                        JOptionPane.showMessageDialog(null, "Los datos han sido ingresados correctamente. ID Formulario: " + idFormulario);  
                        
                        NOMBRE.setText("");  
                        APELLIDO.setText("");  
                        DNI.setText("");  
                        LINEA.setSelectedIndex(0); // Si es un JComboBox, seleccionar el primer elemento (puedes ajustar esto)  
                        DOMINIO.setText("");  
                        KM_INI.setText("");  
                        HORA_INI.setText("");  
                        PALLETS.setText("");  
                        SACAS.setText("");  
                        PIEZAS.setText("");  
                        OBSERVAC.setText("");  
                        ID.setText("");
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
   
    
    private void irAtras(){
        InicioFin panelInicioFin = new InicioFin();
        panelInicioFin.setVisible(true);
        dispose();
    }
}
