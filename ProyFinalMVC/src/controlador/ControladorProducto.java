/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import vista.JF_Productos;

/**
 *
 * @author sanfra
 */
public class ControladorProducto implements ActionListener, MouseListener{
    
    private DefaultTableModel tablaProducto = new DefaultTableModel();
    private ArrayList<Producto> arrayListProducto = new ArrayList();
    private Producto producto = new Producto();
    private JF_Productos vista = new JF_Productos();
    private JComboBox<String> jC_Linea;
    
   
    public ControladorProducto(JF_Productos vista, Producto producto) {
        this.vista = vista;
        this.producto = producto;
        this.arrayListProducto = this.producto.mostrarTodosProductos();
        
        vista.setVisible(true);
        vista.setTitle("ABM PRODUCTOS");
        vista.setLocationRelativeTo(null);
    }
    
    public void clickBotones(){
        
        this.vista.getjB_Actualizar().addActionListener(this);
        this.vista.getjB_Eliminar().addActionListener(this);
        this.vista.getjB_Insertar().addActionListener(this);
        this.vista.getjB_Limpiar().addActionListener(this);
        this.vista.getjB_MostrarTodos().addActionListener(this);
        this.vista.getjT_Productos().addMouseListener(this);
        
    }
    
    public DefaultTableModel tablaProducto(ArrayList<Producto> arrayListProducto) {
        int contadorTabla = 0;
        String x[][] = {};
        String nombresColumnas[] = {"Codigo", "Linea", "Marca", "Descripcion", "Stock"};
        tablaProducto = new DefaultTableModel(x, nombresColumnas);
        if (arrayListProducto != null) {
            for (int i = 0; i < arrayListProducto.size(); i++) {
                tablaProducto.insertRow(contadorTabla, new Object[]{});
                tablaProducto.setValueAt(arrayListProducto.get(i).getCodigo(), contadorTabla, 0);
                tablaProducto.setValueAt(arrayListProducto.get(i).getLinea(), contadorTabla, 1);
                tablaProducto.setValueAt(arrayListProducto.get(i).getMarca(), contadorTabla, 2);
                tablaProducto.setValueAt(arrayListProducto.get(i).getDescripcion(), contadorTabla, 3);
                tablaProducto.setValueAt(arrayListProducto.get(i).getStock(), contadorTabla, 4);
                contadorTabla++;
            }
        }
        return tablaProducto;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(this.vista.getjB_Actualizar())) {                //ACTUALIZAR
            this.producto.setCodigo(this.vista.getjTxt_Codigo().getText());
            this.producto.setDescripcion(this.vista.getjTxt_Descripcion().getText());
            this.producto.setMarca(this.vista.getjTxt_Marca().getText());
            this.producto.setLinea(this.vista.getjC_Linea().getSelectedItem().toString());          
            this.producto.setStock(Integer.valueOf(this.vista.getjTxt_Stock().getText()));
            this.producto.actualizar(producto);
            this.arrayListProducto = this.producto.mostrarTodosProductos();
            this.vista.getjT_Productos().setModel(this.tablaProducto(arrayListProducto));
            
        } else if (e.getSource().equals(this.vista.getjB_Insertar())) {           //INSERTAR
           // this.producto.setCodigo(toString(this.vista.getjTxt_Codigo().getText()));
            this.producto.setCodigo(this.vista.getjTxt_Codigo().getText());
            this.producto.setDescripcion(this.vista.getjTxt_Descripcion().getText());
            this.producto.setMarca(this.vista.getjTxt_Marca().getText());
            this.producto.setLinea(this.vista.getjC_Linea().getSelectedItem().toString());          
            this.producto.setStock(Integer.valueOf(this.vista.getjTxt_Stock().getText()));
            this.producto.insertar(producto);
            this.arrayListProducto = this.producto.mostrarTodosProductos();
            this.vista.getjT_Productos().setModel(this.tablaProducto(arrayListProducto));
                     
        } else if(e.getSource().equals(this.vista.getjB_Eliminar())){               //ELIMIAR
            if (this.vista.getjTxt_Codigo().getText().equals("")) {                      //condicion para que borre solo si esta el codigo
                JOptionPane.showMessageDialog(null, "Debe ingresar un Codigo");
            
            } else {
                this.producto.eliminar(this.vista.getjTxt_Codigo().getText());
                this.arrayListProducto = this.producto.mostrarTodosProductos();
                this.vista.getjT_Productos().setModel(this.tablaProducto(arrayListProducto));
            }
            
        } else if(e.getSource().equals(this.vista.getjB_MostrarTodos())){           //MOSTRAR TODOS
            this.arrayListProducto = this.producto.mostrarTodosProductos();
            this.vista.getjT_Productos().setModel(this.tablaProducto(arrayListProducto));
        
        } else if(e.getSource().equals(this.vista.getjB_Limpiar())){                //LIMPIAR LOS CAMPOS
            this.vista.setjTxt_Codigo("");
            this.vista.getjTxt_Codigo().enable(true);
            this.vista.setjTxt_Descripcion("");
            this.vista.setjTxt_Stock("");
            this.vista.setjTxt_Marca("");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()==1) {
            int fila = this.vista.getjT_Productos().rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vista.setjTxt_Codigo(String.valueOf(this.vista.getjT_Productos().getValueAt(fila, 0)));
                this.vista.getjTxt_Codigo().enable(false);
                this.vista.getjC_Linea().setSelectedItem(this.vista.getjT_Productos().getValueAt(fila, 1));  //CORRECCION 
                this.vista.setjTxt_Marca(String.valueOf(this.vista.getjT_Productos().getValueAt(fila, 2)));
                this.vista.setjTxt_Descripcion(String.valueOf(this.vista.getjT_Productos().getValueAt(fila, 3)));
                this.vista.setjTxt_Stock(String.valueOf(this.vista.getjT_Productos().getValueAt(fila, 4)));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    
}
