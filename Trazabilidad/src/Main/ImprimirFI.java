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
import javax.swing.table.DefaultTableModel;


public class ImprimirFI extends JFrame {
    
    JPanel panel;
    JLabel id_Formulario, horaIni, chofer, vehiculo, nombre, datosCarga, apellido, dni, linea, dominio, kmIni, kmProg, odometro, cargaEnviada, pallets, sacas, piezas, observac; 
    JTextField ID, NOMBRE, APELLIDO, DNI, DOMINIO, LINEA, KM_INI, HORA_INI, KM_PROG, PALLETS, SACAS, PIEZAS, OBSERVAC; 
    
    JButton atras, finalizar, imprimir;
    Connection connection = ConexionBD.getConnection();
    
    public ImprimirFI(){
        setBounds(50,50,800,1020);
        setTitle("Formulario de inicio de trazabilidad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }

    ImprimirFI(DefaultTableModel modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        LINEA = new JTextField();
        OBSERVAC = new JTextField();
        PALLETS = new JTextField();
        SACAS = new JTextField();
        PIEZAS = new JTextField();
        
        ID.setEditable(false);  
        NOMBRE.setEditable(false);  
        APELLIDO.setEditable(false);  
        DNI.setEditable(false);  
        DOMINIO.setEditable(false);  
        KM_INI.setEditable(false);  
        HORA_INI.setEditable(false);  
        LINEA.setEditable(false);  
        OBSERVAC.setEditable(false);  
        PALLETS.setEditable(false);  
        SACAS.setEditable(false);  
        PIEZAS.setEditable(false); 
        
        ID.setBounds(240, 0, 100, 35);
        NOMBRE.setBounds(200, 70, 400, 35);
        APELLIDO.setBounds(200, 120, 400, 35);
        DNI.setBounds(200, 170, 400, 35);
        DOMINIO.setBounds(200, 360, 400, 35);
        KM_INI.setBounds(200, 510, 130, 35);
        LINEA.setBounds(200, 310, 400, 35);
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
    
    
    
    public void llenarTextFields(int idFormulario) { // Supongamos que pasas un ID para buscar  
        //Connection connection = null; // Conexión a la base de datos  
        

        try (Connection connection = ConexionBD.getConnection()){  
             

            // Consulta SQL  
            String sql = "SELECT idformulario_inicio, Nombre_chofer, Apellido_chofer, dni_chofer, linea_vehiculo, dominio_vehiculo, " +  
                         "km_inicio, horaIni, Pallets, Sacas, Piezas, Observaciones FROM formulario_inicio WHERE idformulario_inicio = ?";  
            PreparedStatement preparedStatement = null;  
            ResultSet resultSet = null;  
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
            
        }  
    }
        
        public JPanel getPanel() {  
        return panel;  
    
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
