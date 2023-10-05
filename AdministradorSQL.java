
package model;

import java.sql.*;
import javax.swing.JOptionPane;
import model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AdministradorSQL {
    private Connection _objConnection;
    private String user = "sa";
    private String pass = "12345";
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=db_Supermercado";
    
    public boolean ConnectionBD(){
        try{
            this._objConnection = DriverManager.getConnection(url, user, pass);
            JOptionPane.showMessageDialog(null, "Conexión establecida con DB correctamente.");
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    //Sentencias SQL
    
    public List<ModelRol> ConsultarRoles(){
        List<ModelRol> list = new ArrayList<>();
        try{
            String query = "SELECT * FROM Rol";
            Statement statement = this._objConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                ModelRol rol = new ModelRol();
                
                rol.setRol(resultSet.getString("rol"));
                rol.setIdRol(Integer.parseInt(resultSet.getString("idRol")));
                
                list.add(rol);
            }
            return list;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return list;
        }
    }
    
    public void RegistrarCliente(String nameUser, int identification, String date, int rol){
        try{
            String query = "INSERT INTO Usuario VALUES ("+identification+", '"+nameUser+"', '"+date+"', "+rol+")";
            this._objConnection.prepareStatement(query).execute();
            JOptionPane.showMessageDialog(null, "El cliente se registro correctamente.");
          
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public List<ModelUser> ConsultarClientes(){
        List<ModelUser> list = new ArrayList<>();
        try{
            String query = "SELECT u.*, r.rol FROM Usuario u INNER JOIN Rol r ON (u.idRol = r.idRol)";
            Statement statement = this._objConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                ModelUser user = new ModelUser();
                
                user.setNameUser(resultSet.getString("nameUser"));                
                user.setIdentification(Integer.parseInt(resultSet.getString("identification")));
                user.setDate(resultSet.getString("date"));
                user.setNomRol(resultSet.getString("rol"));

                list.add(user);
            }
            return list;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return list;
        }
    }
}
