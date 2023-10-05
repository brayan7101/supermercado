package com.mycompany.proy_supermercado;
import view.View;
import controller.Controller;
import model.*;

public class Proy_Supermercado {

    public static void main(String[] args) {
        View objView = new View();
        ModelUser objModel = new ModelUser();        
        AdministradorSQL objAdministradorSQL = new AdministradorSQL();

        Controller objController = new Controller(objView, objModel, objAdministradorSQL);
    }
}
