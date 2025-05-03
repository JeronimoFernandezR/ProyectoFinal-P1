package org.uniquindio.edu.co.poo.proyectofinalp1.model;




public class Cajero extends Usuario{

    private String codigoEmpleado;
    private boolean activo;
    
    public Cajero(String nombre,String correo, String id,String contraseña,String codigoEmpleado,boolean activo){
        super(nombre, correo, id, contraseña);
        this.codigoEmpleado=codigoEmpleado;
        this.activo=activo;
    }

    /**
     * @return the codigoEmpleado
     */
    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    /**
     * @param codigoEmpleado the codigoEmpleado to set
     */
    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
