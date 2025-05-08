/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uniquindio.edu.co.poo.proyectofinalp1.model;

import java.util.ArrayList;

/**
 *
 * @author river
 */
public class Administrador extends Persona{
    private ArrayList<Cajero> listaCajeros;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Cuenta> listaCuentas;
    
    public Administrador(String nombre,String correo, String id,String contraseña,String codigo,boolean empleadoActivo){
        super(nombre, correo, id,codigo,empleadoActivo);
    }   

    
    
}
