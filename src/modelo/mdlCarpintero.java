package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import vista.jfrCrud;


/**
 *
 * @author exequ
 */
public class mdlCarpintero {

      private String nombre_carpintero;
    private int edad_carpintero;
    private int peso_carpintero;
    private String correo_carpintero;
    

    /**
     * @return the nombre_carpintero
     */
    public String getNombre_carpintero() {
        return nombre_carpintero;
    }

    /**
     * @param nombre_carpintero the nombre_carpintero to set
     */
    public void setNombre_carpintero(String nombre_carpintero) {
        this.nombre_carpintero = nombre_carpintero;
    }

    /**
     * @return the edad_carpintero
     */
    public int getEdad_carpintero() {
        return edad_carpintero;
    }

    /**
     * @param edad_carpintero the edad_carpintero to set
     */
    public void setEdad_carpintero(int edad_carpintero) {
        this.edad_carpintero = edad_carpintero;
    }

    /**
     * @return the peso_carpintero
     */
    public int getPeso_carpintero() {
        return peso_carpintero;
    }

    /**
     * @param peso_carpintero the peso_carpintero to set
     */
    public void setPeso_carpintero(int peso_carpintero) {
        this.peso_carpintero = peso_carpintero;
    }

    /**
     * @return the correo_carpintero
     */
    public String getCorreo_carpintero() {
        return correo_carpintero;
    }

    /**
     * @param correo_carpintero the correo_carpintero to set
     */
    public void setCorreo_carpintero(String correo_carpintero) {
        this.correo_carpintero = correo_carpintero;
    }

    ////////////////////////1- Parametros
  
    

    
    

    ////////////////////////3- Métodos 
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO tbCarpintero(UUID, Nombre_Carpintero, Edad_Carpintero, Peso_Carpintero , Correo_Carpintero) VALUES (?, ?, ?, ?, ?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getNombre_carpintero());
            pstmt.setInt(3, getEdad_carpintero());
            pstmt.setInt(4, getPeso_carpintero());
           pstmt.setString(5, getCorreo_carpintero());

            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }

    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID", "Nombre_Carpintero", "Edad_Carpintero", "Peso_Carpintero" ,  "Correo_Carpintero"});
        try {
            //Consulta a ejecutar
            String query = "SELECT * FROM tbCarpintero";
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(query);
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID"), 
                    rs.getString("Nombre_Carpintero"), 
                    rs.getInt("Edad_Carpintero"), 
                    rs.getInt("Peso_Carpintero"),
                    rs.getString("Correo_Carpintero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }

    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from tbCarpintero where UUID = ?";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }

    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update tbCarpintero set Nombre_Carpintero = ?, Edad_Carpintero = ?, Peso_Carpintero = ?, Correo_Carpintero = ?  where UUID = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre_carpintero());
                updateUser.setInt(2, getEdad_carpintero());
                updateUser.setInt(3, getPeso_carpintero());
                updateUser.setString(3, getCorreo_carpintero());
                updateUser.setString(4, miUUId);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }

    public void Buscar(JTable tabla, JTextField miTextField) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID", "Nombre_Carpintero", "Edad_Carpintero", "Peso_Carpintero" , "Correo_Carpintero"});
        try {
            String sql = "SELECT * FROM tbCarpintero WHERE Nombre_Carpintero LIKE ? || '%'";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miTextField.getText());
            ResultSet rs = deleteEstudiante.executeQuery();

            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID"), rs.getString("Nombre_Carpintero"), rs.getInt("Edad_Carpintero"), rs.getInt("Peso_Carpintero"), rs.getString("Correo_Carpintero")});
            }

            
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }

    public void limpiar(jfrCrud vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }

    public void cargarDatosTabla(jfrCrud vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.tbCarpintero.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.tbCarpintero.getValueAt(filaSeleccionada, 0).toString();
            String Nombre_carpinteroDeTB = vista.tbCarpintero.getValueAt(filaSeleccionada, 1).toString();
            String Edad_carpinteroDeTB = vista.tbCarpintero.getValueAt(filaSeleccionada, 2).toString();
            String Peso_carpinteroDeTB = vista.tbCarpintero.getValueAt(filaSeleccionada, 3).toString();
            String Correo_carpinteroDeTB = vista.tbCarpintero.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(Nombre_carpinteroDeTB);
            vista.txtEdad.setText(Edad_carpinteroDeTB);
            vista.txtPeso.setText(Peso_carpinteroDeTB);
            vista.txtCorreo.setText(Correo_carpinteroDeTB);
        }
    }

}
