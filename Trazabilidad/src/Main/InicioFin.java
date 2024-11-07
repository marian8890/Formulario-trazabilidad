
package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;  
import java.sql.*; 


public class InicioFin extends JFrame {
    JPanel panel;
    JLabel trazabilidad;
    JComboBox inicioFin;
    JTable formIniLleno, FormFinLleno;
    JButton continuar, salir, buscar;
    
    
    public InicioFin (){
        setBounds(50,50,600,350);
        setTitle("Tramo de la trazabilidad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        iniciarComponentes();
    }
    
    private void iniciarComponentes(){
        colocarPanel();
        colocarLabels();
        colocarComboBox();
        colocarBotones();
        colocarTabla();
        
    }
    
    private void colocarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
    }
    
    private void colocarLabels(){
        trazabilidad = new JLabel("Seleccione: Inicio o Final de la trazabilidad");
        
        trazabilidad.setBounds(100, 40, 300, 35);
        
        panel.add(trazabilidad);
    }
    
    private void colocarComboBox(){
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        
        modelo.addElement("Inicio de la trazabilidad");
        modelo.addElement("Fin de la trazabilidad");
        
        inicioFin = new JComboBox<>(modelo);
        
        inicioFin.setBounds(100, 90, 400, 40);
        
        panel.add(inicioFin);

    }
    
    private void colocarBotones(){
        continuar = new JButton("Continuar");
        continuar.setBounds(240, 220, 95, 40);
        continuar.addActionListener(e -> abrirFormulario());
        
        salir = new JButton("Salir");
        salir.setBounds(360, 220, 95, 40);
        salir.addActionListener(e -> salirDeSeleccion());
        
        buscar = new JButton("Buscar");
        buscar.setBounds(120, 220, 95, 40);
        buscar.addActionListener(e -> BuscarFormulario());
        
        panel.add(continuar);
        panel.add(salir);
        panel.add(buscar);
    }
    
    private void abrirFormulario(){
        String seleccion = (String) inicioFin.getSelectedItem();  

        if ("Inicio de la trazabilidad".equals(seleccion)) {  
            // Abrir formulario de inicio  
            FormularioInicio formularioInicio = new FormularioInicio();  
            formularioInicio.setVisible(true);  
            dispose();
        } else if ("Fin de la trazabilidad".equals(seleccion)) {  
            // Abrir formulario de fin  
            FormularioFin formularioFin = new FormularioFin();  
            formularioFin.setVisible(true); 
            dispose();
        }  
    }
    
    private void salirDeSeleccion(){
        dispose();
    }
    
    private void BuscarFormulario(){
      
    try {  
        // Paso 1: Pregunta si es un formulario de inicio o de fin  
        String[] opcionesFormulario = {"Formulario de Inicio", "Formulario de Fin"};  
        int seleccionFormulario = JOptionPane.showOptionDialog(  
                null,  
                "Selecciona el tipo de formulario que deseas buscar:",  
                "Seleccionar Formulario",  
                JOptionPane.DEFAULT_OPTION,  
                JOptionPane.QUESTION_MESSAGE,  
                null,  
                opcionesFormulario,  
                opcionesFormulario[0]);  

        // Si el usuario cancela  
        if (seleccionFormulario == JOptionPane.CLOSED_OPTION) {  
            return;  
        }  

        //Opciones para avanzar en la búsqueda  
        String nombreTabla = seleccionFormulario == 0 ? "formulario_inicio" : "formulario_fin";  
        String idColumna = seleccionFormulario == 0 ? "idformulario_inicio" : "idformulario_fin";  

        //Más opciones para avanzar después de seleccionar tipo de formulario  
        String[] opcionesBusqueda = {"ID de formulario", "DNI del chofer", "Dominio del vehículo"};  
        int seleccionBusqueda = JOptionPane.showOptionDialog(  
                null,  
                "¿Por qué deseas buscar?",  
                "Seleccionar Método de Búsqueda",  
                JOptionPane.DEFAULT_OPTION,  
                JOptionPane.QUESTION_MESSAGE,  
                null,  
                opcionesBusqueda,  
                opcionesBusqueda[0]);  

          
        if (seleccionBusqueda == JOptionPane.CLOSED_OPTION) {  
            return;  
        }  

        //Rellenar el campo según el método seleccionado   
        String criterioBusqueda = "";  
        if (seleccionBusqueda == 0) { // ID de formulario  
            criterioBusqueda = JOptionPane.showInputDialog("Ingresa el " + idColumna + ":");  
        } else if (seleccionBusqueda == 1) { // DNI del chofer  
            criterioBusqueda = JOptionPane.showInputDialog("Ingresa el DNI del chofer:");  
        } else if (seleccionBusqueda == 2) { // Dominio del vehículo  
            criterioBusqueda = JOptionPane.showInputDialog("Ingresa el dominio del vehículo:");  
        }  

        //Valida según el método de consulta 
        if (criterioBusqueda == null || criterioBusqueda.trim().isEmpty()) {  
            return; // Si se cancela o no se ingresa nada, salir del método  
        }  

        //Hago consulta SQL  
        String consulta = "SELECT * FROM " + nombreTabla + " WHERE ";  
        if (seleccionBusqueda == 0) { // ID de formulario  
            consulta += idColumna + " = ?";  
        } else if (seleccionBusqueda == 1) { // DNI del chofer  
            consulta += "dni_chofer = ?";  
        } else if (seleccionBusqueda == 2) { // Dominio del vehículo  
            consulta += "dominio_vehiculo = ?";  
        }  

        //Ejecutar la consulta y mostrar resultado
        Connection connection = ConexionBD.getConnection();  
        PreparedStatement pS = connection.prepareStatement(consulta);  
        pS.setString(1, criterioBusqueda);  
        
        ResultSet rS = pS.executeQuery();  
        
          
        if (seleccionFormulario == 0) {  
            mostrarTablaFormularioInicio(rS); // Método para mostrar la tabla de inicio  
        } else {  
            mostrarTablaFormularioFin(rS); // Método para mostrar la tabla de fin  
        }  

        // Cerrar conexiones  
        rS.close();  
        pS.close();  
        connection.close();  

    } catch (SQLException e) {  
        e.printStackTrace();   
    }  
}  

      
    private void mostrarTablaFormularioInicio(ResultSet rS) {  
          
        DefaultTableModel modelo = new DefaultTableModel();  
        modelo.addColumn("ID");  
        modelo.addColumn("NOMBRE CHOFER");
        modelo.addColumn("APELLIDO CHOFER");  
        modelo.addColumn("DNI CHOFER");  
        modelo.addColumn("LÍNEA VEHÍCULO");
        modelo.addColumn("DOMINION VEHÍCULO");
        modelo.addColumn("KM INICIO");
        modelo.addColumn("HORA INICIO");
        modelo.addColumn("PALETS");
        modelo.addColumn("SACAS");
        modelo.addColumn("PIEZAS");
        modelo.addColumn("OBSERVACIONES");
        
        /*Este try catch maneja excepciones por ejemplo si no me puedo conectar a la BD 
        o si tengo un error en la sintaxis o si no puedo acceder a los datos del resulSet
        En este caso el bloque catch se ejecuta cuando ocurre un error con la BD. Se imprime en 
        la consola la traza de la excepción, que me sirve para saber qué salió mal¨*/
        try {  
            while (rS.next()) {  
                modelo.addRow(new Object[]{  
                    rS.getInt("idformulario_inicio"), 
                    rS.getString("Nombre_chofer"),
                    rS.getString("Apellido_chofer"),
                    rS.getString("dni_chofer"),
                    rS.getString("linea_vehiculo"),
                    rS.getString("dominio_vehiculo"),
                    rS.getString("km_inicio"),
                    rS.getString("horaIni"),
                    rS.getInt("Pallets"),
                    rS.getInt("Sacas"),
                    rS.getInt("Piezas"),
                    rS.getString("Observaciones"),
                    
                });  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  

        // Mostrar la tabla (puedes usar un JTable en un JScrollPane)  
        JTable tabla = new JTable(modelo);  
        JScrollPane scrollPane = new JScrollPane(tabla);  
        JOptionPane.showMessageDialog(null, scrollPane, "Resultados - Formulario de Inicio", JOptionPane.PLAIN_MESSAGE);  
    }  

    // Método para mostrar la tabla de formulario de fin  
    private void mostrarTablaFormularioFin(ResultSet rS) {  
          
        DefaultTableModel modelo = new DefaultTableModel();  
        
        modelo.addColumn("ID");  
        modelo.addColumn("NOMBRE CHOFER");
        modelo.addColumn("APELLIDO CHOFER");  
        modelo.addColumn("DNI CHOFER");  
        modelo.addColumn("LÍNEA VEHÍCULO");
        modelo.addColumn("DOMINION VEHÍCULO");
        modelo.addColumn("KM INICIO");
        modelo.addColumn("KM FIN");
        modelo.addColumn("HORA INICIO");
        modelo.addColumn("HORA FIN");
        modelo.addColumn("PALETS");
        modelo.addColumn("SACAS");
        modelo.addColumn("PIEZAS");
        modelo.addColumn("OBSERVACIONES");  
        
        
        
        JTable tabla1 = new JTable(modelo);
            tabla1.getColumnModel().getColumn(0).setPreferredWidth(50);   
            tabla1.getColumnModel().getColumn(1).setPreferredWidth(150);  
            tabla1.getColumnModel().getColumn(2).setPreferredWidth(150);  
            tabla1.getColumnModel().getColumn(3).setPreferredWidth(100);   
            tabla1.getColumnModel().getColumn(4).setPreferredWidth(100);   
            tabla1.getColumnModel().getColumn(5).setPreferredWidth(100);  
            tabla1.getColumnModel().getColumn(6).setPreferredWidth(100);  
            tabla1.getColumnModel().getColumn(7).setPreferredWidth(100); 
            tabla1.getColumnModel().getColumn(8).setPreferredWidth(100);   
            tabla1.getColumnModel().getColumn(9).setPreferredWidth(100);   
            tabla1.getColumnModel().getColumn(10).setPreferredWidth(100);  
            tabla1.getColumnModel().getColumn(11).setPreferredWidth(100);   
            tabla1.getColumnModel().getColumn(12).setPreferredWidth(100);   
            tabla1.getColumnModel().getColumn(13).setPreferredWidth(200);
        
        try {  
            
            while (rS.next()) {  
                modelo.addRow(new Object[]{  
                    rS.getInt("idformulario_fin"), 
                    rS.getString("Nombre_chofer"),
                    rS.getString("Apellido_chofer"),
                    rS.getString("dni_chofer"),
                    rS.getString("linea_vehiculo"),
                    rS.getString("dominio_vehiculo"),
                    rS.getString("km_inicio"),
                    rS.getString("km_fin"),
                    rS.getString("horaIni"),
                    rS.getString("horaFin"),
                    rS.getInt("Pallets"),
                    rS.getInt("Sacas"),
                    rS.getInt("Piezas"),
                    rS.getString("Observaciones"),  
                    // Agrega más columnas según sea necesario  
                });  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        
        

        //Mostrar la tabla (puedes usar un JTable en un JScrollPane)
         
        JScrollPane scrollPane = new JScrollPane(tabla1);  
        JOptionPane.showMessageDialog(null, scrollPane, "Resultados - Formulario de Fin", JOptionPane.PLAIN_MESSAGE);  


        }
    private void colocarTabla(){
        formIniLleno = new JTable();
        formIniLleno.setBounds(2000, 80, 550, 300);
        formIniLleno.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "NOMBRE CHOFER", "APELLIDO CHOFER", "DNI CHOFER", "N° DE LÍNEA", "DOMINIO", "KM INICIO", "HORA INICIO", "N° PALETS", "N° SACAS", "N° PIEZAS", "OBSERVACIONES"}
        ));       
        panel.add(formIniLleno);
    }
       
}
