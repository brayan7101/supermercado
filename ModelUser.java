package model;

public class ModelUser {
    private String nameUser;
    private int identification;
    private String date;
    private int rol;
    private String nomRol;

    public String getNameUser() {
        return nameUser;
    }

    public int getIdentification() {
        return identification;
    }

    public String getDate() {
        return date;
    }

    public int getRol() {
        return rol;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getNomRol() {
        return nomRol;
    }

    public void setNomRol(String nomRol) {
        this.nomRol = nomRol;
    }
    
}
