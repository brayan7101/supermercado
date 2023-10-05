package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.*;
import view.View;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;


public class Controller {
    
    private ModelUser _objModel;
    private View _objView;
    private AdministradorSQL _objAdministradorSQL;
    
    
    public Controller(View objView, ModelUser objModel, AdministradorSQL objAdministradorSQL){
        this._objModel = objModel;
        this._objView = objView;
        this._objAdministradorSQL = objAdministradorSQL;
        StartView();
        this._objView.btnSave.addActionListener(btnSave);        
        this._objView.btnCancel.addActionListener(btnCancel);        
        this._objView.btnRefresh.addActionListener(btnRefresh);

    }
    
    private void StartView(){
        this._objView.setVisible(true);
        this._objView.setLocationRelativeTo(null);
      
        boolean connectionState = this._objAdministradorSQL.ConnectionBD();
        if(!connectionState){
            this._objView.btnSave.setEnabled(false);
            this._objView.btnCancel.setEnabled(false);            
            this._objView.btnRefresh.setEnabled(false);

        }
        List<ModelRol> listRol = this._objAdministradorSQL.ConsultarRoles();
       
        for(ModelRol rol : listRol){
            String item = rol.getIdRol()+"-"+rol.getRol();
            this._objView.selRol.addItem(item);
        }
        CargarDatosCliente();
        
    }
    
    private void SetearDatosCliente(){
        Date fecha = this._objView.selDate.getDate();
        this._objModel.setNameUser(this._objView.txtNameUser.getText());
        String rol = this._objView.selRol.getSelectedItem().toString();
        String[] infoRol = rol.split("-");
        this._objModel.setIdentification(Integer.parseInt(this._objView.txtIdentification.getText()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this._objModel.setDate(dateFormat.format(fecha));
        this._objModel.setRol(Integer.parseInt(infoRol[0]));
    }
    
    private void LimpiarForm(){
        this._objView.selDate.setDate(null);
        this._objView.txtNameUser.setText("");
        this._objView.txtIdentification.setText("");
    }
    
    private void CargarDatosCliente(){
        DefaultTableModel tableModel = new DefaultTableModel();
        List<ModelUser> listClientes = this._objAdministradorSQL.ConsultarClientes();
        
        ArrayList<Object> nombreColumna = new ArrayList<Object>();
        nombreColumna.add("Identificación");
        nombreColumna.add("Nombre");
        nombreColumna.add("Fecha");
        nombreColumna.add("Rol");
        
        for(Object columna : nombreColumna){
            tableModel.addColumn(columna);
        }
       
        for(ModelUser cliente : listClientes){
            Object[] fila = new Object[]{cliente.getIdentification(), cliente.getNameUser(), cliente.getDate(), cliente.getNomRol()};
            tableModel.addRow(fila);
        }
        
        this._objView.tableClientes.setModel(tableModel);
    }
    
    ActionListener btnSave = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            SetearDatosCliente();
            _objAdministradorSQL.RegistrarCliente(_objModel.getNameUser(), _objModel.getIdentification(), _objModel.getDate(), _objModel.getRol());
            LimpiarForm();
        }
    };
    
    ActionListener btnCancel = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            LimpiarForm();
        }
    };

    ActionListener btnRefresh = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            CargarDatosCliente();
        }
    };
    
}
