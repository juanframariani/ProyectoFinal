/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.JF_Productos;
import modelo.Producto;

/**
 *
 * @author sanfra
 */
public class ProyFinalMariani {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        JF_Productos menuVista = new JF_Productos();
        Producto producto = new Producto();
        ControladorProducto controlador1 = new ControladorProducto(menuVista, producto);
        controlador1.clickBotones();

    }
    
}
