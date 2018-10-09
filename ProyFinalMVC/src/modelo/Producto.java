/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import datos.ProductoDAO;
import java.util.ArrayList;

/**
 *
 * @author sanfra
 */
public class Producto {
    
    private String codigo;
    private String linea;
    private String marca;
    private String descripcion;
    private int stock;
    private ProductoDAO productoDAO;

    public Producto(String codigo, String linea, String marca, String descripcion, int stock) {
        this.codigo = codigo;
        this.linea = linea;
        this.marca = marca;
        this.descripcion = descripcion;
        this.stock = stock;
        this.productoDAO = new ProductoDAO();
    }

    public Producto() {
        this.productoDAO = new ProductoDAO();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<Producto> mostrarTodosProductos(){
        return this.productoDAO.mostrarTodos();
    }
 
    public boolean insertar (Producto prod1){
        return this.productoDAO.insertarProductoDAO(prod1);
    }
    
    public boolean actualizar (Producto prod1){
        return this.productoDAO.actualizarProducto(prod1);
    }
    
    public boolean eliminar (String codigo){
        return this.productoDAO.eliminarProducto(codigo);
    }
}
