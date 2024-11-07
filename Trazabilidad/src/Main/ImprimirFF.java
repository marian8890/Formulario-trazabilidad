package Main;

import java.sql.Connection;
import java.sql.DriverManager;
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
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ImprimirFF extends JFrame {
    
    JPanel panel;
    JLabel id_Formulario, horaIni, horaFin, chofer, vehiculo, nombre, datosCarga, apellido, dni, linea, dominio, kmIni, kmFin, odometro, cargaEnviada, pallets, sacas, piezas, observac; 
    JTextField ID, NOMBRE, APELLIDO, DNI, DOMINIO, LINEA, HORA_INI, HORA_FIN, KM_INI, KM_FIN, PALLETS, SACAS, PIEZAS, OBSERVAC; 
    
    JButton atras, finalizar, imprimir;
    Connection connection = ConexionBD.getConnection();
    
    public ImprimirFF(){
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
        kmIni = new JLabel("Km de Inicio:");
        kmFin = new JLabel("Km de Finalización:");
        cargaEnviada = new JLabel("CARGA ENVIADA");
        pallets = new JLabel("Cant. de pallets:");
        sacas = new JLabel("Cant. de sacas:");
        piezas = new JLabel("Cant. de piezas:");
        observac = new JLabel("Observaciones:");
        horaIni = new JLabel("Horario de Inicio: ");
        horaFin = new JLabel ("Horario de Finaliazción: ");
        
        id_Formulario.setBounds(100, 0, 200, 35);
        chofer.setBounds(100, 20, 100, 35);
        nombre.setBounds(100, 70, 100, 35);
        apellido.setBounds(100, 120, 100, 35);
        dni.setBounds(100, 170, 100, 35);
        vehiculo.setBounds(100, 260, 220, 35);
        linea.setBounds(100, 310, 100, 35);
        dominio.setBounds(100, 360, 100, 35);
        kmFin.setBounds(360, 460, 140, 35);
        odometro.setBounds(100, 460, 100, 35);
        kmIni.setBounds(100, 510, 100, 35);
        horaIni.setBounds(350, 510, 100, 35);
        horaFin.setBounds(360, 510, 140, 35);
        cargaEnviada.setBounds(100, 590, 100, 35);
        pallets.setBounds(100, 640, 100, 35);
        sacas.setBounds(100, 690, 100, 35);
        piezas.setBounds(100, 740, 100, 35);
        observac.setBounds(100, 820, 100, 35);
        
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
        HORA_FIN = new JTextField();
        LINEA = new JTextField();
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
        //KM_FIN.setBounds(500, 460, 130, 35);
        LINEA.setBounds(200, 310, 400, 35);
        HORA_INI.setBounds(450, 510, 130, 35);
        HORA_FIN.setBounds(500, 510, 130, 35);
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
        panel.add(KM_FIN);
        panel.add(HORA_FIN);
        panel.add(HORA_INI);
        panel.add(OBSERVAC);
        panel.add(PALLETS);
        panel.add(SACAS);
        panel.add(PIEZAS);
    }
    

    private void colocarBotones(){
        finalizar = new JButton("Salir");
        finalizar.setBounds(505, 890, 100, 38);
        finalizar.addActionListener(e -> Salir());
        
        imprimir = new JButton("Imprimir");
        imprimir.setBounds(610, 890, 100, 38);
        imprimir.addActionListener(e -> imprimirFormulario());
        
        atras = new JButton("Atrás");
        atras.setBounds(400, 890, 100, 38);
        atras.addActionListener(e -> irAtras());
        
        
        panel.add(finalizar);
        panel.add(imprimir);
        panel.add(atras);
    }
    
   
    private void imprimirFormulario(){
        
        
    }
    
    public void llenarTextFields() {  
        
      /*
        // Crear la conexión a la base de datos  
        Connection connection = null;  
        PreparedStatement statement = null;  
        ResultSet resultSet = null;  

        try {  
            // Obtener conexión a la base de datos  
            connection = ConexionBD.getConnection();  

            // Crear la consulta SQL (puedes modificar la consulta según tus necesidades)  
            String query = "SELECT Nombre_chofer, Apellido_chofer, dni_chofer, linea_vehiculo, dominio_vehiculo, " +  
                    "km_inicio, km_fin, horaIni, horaFin, Pallets, Sacas, Piezas, Observaciones FROM formulario_fin";  

            // Preparar la declaración  
            statement = connection.prepareStatement(query);  
            resultSet = statement.executeQuery();  

            // Suponiendo que solo hay un registro que deseas mostrar (puedes ajustar si es necesario)  
            if (resultSet.next()) {  
                // Llenar los campos de texto con los datos de la base de datos  
                NOMBRE.setText(resultSet.getString("Nombre_chofer"));  
                APELLIDO.setText(resultSet.getString("Apellido_chofer"));  
                DNI.setText(resultSet.getString("dni_chofer"));  
                LINEA.setText(resultSet.getString("linea_vehiculo"));  
                DOMINIO.setText(resultSet.getString("dominio_vehiculo"));  
                KM_INI.setText(resultSet.getString("km_inicio"));  
                KM_FIN.setText(resultSet.getString("km_fin"));  
                HORA_INI.setText(resultSet.getString("horaIni"));  
                HORA_FIN.setText(resultSet.getString("horaFin"));  
                PALLETS.setText(resultSet.getString("Pallets"));  
                SACAS.setText(resultSet.getString("Sacas"));  
                PIEZAS.setText(resultSet.getString("Piezas"));  
                OBSERVAC.setText(resultSet.getString("Observaciones"));  
            } else {  
                // Manejar caso donde no hay datos encontrados  
                JOptionPane.showMessageDialog(null, "No se encontraron datos.");  
            }  

        } catch (SQLException e) {  
            e.printStackTrace(); // Manejo de errores de la base de datos  
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);  
        } finally {  
            // Cerrar recursos  
            try { if (resultSet != null) resultSet.close(); } catch (SQLException e) { e.printStackTrace(); }  
            try { if (statement != null) statement.close(); } catch (SQLException e) { e.printStackTrace(); }  
            try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }  
        }  
    }  

    /*try (Connection connection = ConexionBD.getConnection()) {  
        // Consulta SQL para obtener el registro correspondiente al ID  
        String sql = "SELECT * FROM formulario_fin";  
        
        // Preparar el statement  
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {  
            

            // Ejecutar la consulta  
            ResultSet resultSet = preparedStatement.executeQuery();  

            // Verificar si se obtuvo algún resultado  
            if (resultSet.next()) {  
                // Llenar los text fields con los datos del result set  
                ID.setText(String.valueOf(resultSet.getInt("idformulario_fin")));  
                NOMBRE.setText(resultSet.getString("Nombre_chofer"));  
                APELLIDO.setText(resultSet.getString("Apellido_chofer"));  
                DNI.setText(resultSet.getString("dni_chofer"));  
                LINEA.setText(resultSet.getString("linea_vehiculo"));  
                DOMINIO.setText(resultSet.getString("dominio_vehiculo"));  
                KM_INI.setText(String.valueOf(resultSet.getInt("km_inicio")));  
                KM_FIN.setText(String.valueOf(resultSet.getInt("km_fin")));  
                HORA_INI.setText(resultSet.getString("horaIni"));  
                HORA_FIN.setText(resultSet.getString("horaFin"));  
                PALLETS.setText(String.valueOf(resultSet.getInt("Pallets")));  
                SACAS.setText(String.valueOf(resultSet.getInt("Sacas")));  
                PIEZAS.setText(String.valueOf(resultSet.getInt("Piezas")));  
                OBSERVAC.setText(resultSet.getString("Observaciones"));  
            } else {  
                JOptionPane.showMessageDialog(null, "No se encontró formulario");  
            }  
        }  
    } catch (SQLException e) {  
        // Manejo de excepciones  
        e.printStackTrace();  
        JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos: " + e.getMessage());  
    }  
}
        /*ID.setText(String.valueOf(id));  
        NOMBRE.setText(nombre);  
        APELLIDO.setText(apellido);  
        DNI.setText(dni);  
        DOMINIO.setText(dominio);  
        KM_INI.setText(String.valueOf(km_inicio));
        KM_FIN.setText(String.valueOf(km_fin))
        HORA_INI.setText(horaIni); 
        HORA_FIN.setText(horaFin);
        PALLETS.setText(String.valueOf(pallets));  
        SACAS.setText(String.valueOf(sacas));  
        PIEZAS.setText(String.valueOf(piezas));  
        OBSERVAC.setText(observaciones);  
    }*/
    
    
        /*try {  
            // Establecer conexión a la base de datos  
            Connection connection = ConexionBD.getConnection();  

            // Consulta SQL  
            String sql = "SELECT idformulario_inicio, Nombre_chofer, Apellido_chofer, dni_chofer, linea_vehiculo, dominio_vehiculo, " +  
                         "km_inicio, horaIni, Pallets, Sacas, Piezas, Observaciones FROM formulario_inicio WHERE idformulario_inicio = ?";  

            preparedStatement = connection.prepareStatement(sql);  
            preparedStatement.setInt(1, idFormulario); // Asumiendo que el ID es un entero  
            resultSet = preparedStatement.executeQuery();  

            // Si hay resultados, llenamos los JTextFields  
            if (resultSet.next()) {  
                ID.setText(String.valueOf(resultSet.getInt("idformulario_inicio")));  
                NOMBRE.setText(resultSet.getString("Nombre_chofer"));  
                APELLIDO.setText(resultSet.getString("Apellido_chofer"));  
                DNI.setText(resultSet.getString("dni_chofer"));  
                LINEA.setText(resultSet.getString("linea_vehiculo"));  
                DOMINIO.setText(resultSet.getString("dominio_vehiculo"));  
                KM_INI.setText(String.valueOf(resultSet.getInt("km_inicio")));  
                HORA_INI.setText(resultSet.getString("horaIni"));  
                PALLETS.setText(String.valueOf(resultSet.getInt("Pallets")));  
                SACAS.setText(String.valueOf(resultSet.getInt("Sacas")));  
                PIEZAS.setText(String.valueOf(resultSet.getInt("Piezas")));  
                OBSERVAC.setText(resultSet.getString("Observaciones"));  
            } else {  
                JOptionPane.showMessageDialog(null, "No se encontró el formulario con ID: " + idFormulario);  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } finally {  
            // Cerrar recursos  
            try {  
                if (resultSet != null) resultSet.close();  
                if (preparedStatement != null) preparedStatement.close();  
                if (connection != null) connection.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  */
    }

    private void irAtras(){
        InicioFin panelInicioFin = new InicioFin();
        panelInicioFin.setVisible(true);
        dispose();
    }
    
    private void Salir(){
        dispose();
    }
}

