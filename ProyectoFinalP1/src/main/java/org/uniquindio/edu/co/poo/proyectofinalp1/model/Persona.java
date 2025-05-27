package org.uniquindio.edu.co.poo.proyectofinalp1.model;

/*
 * Usuarios
    Clientes: Personas que poseen cuentas en el banco y pueden realizar transacciones.
    Cajeros: Encargados de gestionar transacciones para los clientes.
    Administradores: Personal con acceso total para la gestión del banco.
 */

public abstract class Persona{
    protected String nombre;
    protected String correo;
    protected String id;
    protected String codigo;
    private boolean empleadoActivo;

    /**
     * Crea una nueva persona con los datos básicos.
     * @param nombre Nombre de la persona.
     * @param correo Correo electrónico.
     * @param id Identificador único.
     */
    public Persona(String nombre, String correo, String id) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
        
        
    }
    
    /**
     * Crea una nueva persona con todos los datos.
     * @param nombre Nombre de la persona.
     * @param correo Correo electrónico.
     * @param id Identificador único.
     * @param codigo Código interno.
     * @param empleadoActivo Estado de actividad del empleado.
     */
    public Persona(String nombre, String correo, String id, String codigo, boolean empleadoActivo) {
        this.nombre = nombre;
        this.correo = correo;
        this.id = id;
        this.codigo=codigo;
        this.empleadoActivo=empleadoActivo;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the empleadoActivo
     */
    public boolean isEmpleadoActivo() {
        return empleadoActivo;
    }

    /**
     * @param empleadoActivo the empleadoActivo to set
     */
    public void setEmpleadoActivo(boolean empleadoActivo) {
        this.empleadoActivo = empleadoActivo;
    }
    

}
