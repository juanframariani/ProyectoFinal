/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author sanfra
 */
import datos.SQLQuery;
import modelo.Producto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ProductoDAO extends SQLQuery{

    private DefaultTableModel tablaProducto = new DefaultTableModel();
    
    public ProductoDAO() {
        try {
            this.conectar("localhost", "productos", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ArrayList<Producto> mostrarTodos(){
        ArrayList<Producto> prodTodos = new ArrayList();
       
        try {
            this.consulta = this.conn.prepareStatement("SELECT codigo,linea,marca,descripcion,stock FROM productos");
            this.datos = this.consulta.executeQuery();
            
            while (this.datos.next()) {  
                Producto prod = new Producto();
                prod.setCodigo(this.datos.getString("codigo"));
                prod.setLinea(this.datos.getString("linea"));                   
                prod.setMarca(this.datos.getString("marca"));
                prod.setDescripcion(this.datos.getString("descripcion"));
                prod.setStock(this.datos.getInt("stock"));
                prodTodos.add(prod);
             }
            
        }catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prodTodos; 
    }
    
    
    public boolean insertarProductoDAO(Producto prod1){
               
        try {
            this.s = this.conn.createStatement();
            this.s.executeUpdate("INSERT INTO productos (codigo, linea, marca, descripcion, stock) VALUES ('" + prod1.getCodigo()+ "','" + prod1.getLinea() + "','" + prod1.getMarca() + "','" + prod1.getDescripcion() + "', '" + prod1.getStock()+ "' )");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    
    public boolean actualizarProducto(Producto prod1){
               
        try {
            this.s = this.conn.createStatement();
            s.executeUpdate("UPDATE productos SET "
                    + "linea  = '" + prod1.getLinea()+ "', "
                    + "marca = '" + prod1.getMarca() + "', " + "descripcion = '" + prod1.getDescripcion() + "'," + "stock = '" + prod1.getStock() + "' WHERE codigo =  '" + prod1.getCodigo() + "';");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 

    public boolean eliminarProducto(String codigo){
               
        try {
            this.s = this.conn.createStatement();
            
            s.executeUpdate("DELETE FROM productos WHERE codigo =  '" + codigo + "'");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
     
    
