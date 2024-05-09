import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FerrecorApp extends JFrame implements ActionListener, ListSelectionListener {
    private JLabel productoLabel, descripcionLabel, precioLabel, cantidadLabel, ejemploUsoLabel, herramientasLabel;
    private JTextField Campoproducto, Campodescripcion, Campoprecio, Campocantidad, campoejemploUso, Campoherramientas;
    private JButton Agregarboton, borrarBoton, venderBoton, recibirSuminstro, estadisticasBoton;
    private JList<String> listadoproducto;
    private String ultimoProducto;
    private DefaultListModel<String> listModel;
    private List<Producto> ListaProducto;

    public FerrecorApp() {

        setTitle("Ferrecor");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        // Etiquetas y campos de entrada
        productoLabel = new JLabel("Nombre del producto:");
        Campoproducto = new JTextField(10);

        descripcionLabel = new JLabel("Descripción:");
        Campodescripcion = new JTextField(20);

        precioLabel = new JLabel("Precio:");
        Campoprecio = new JTextField(10);

        cantidadLabel = new JLabel("Cantidad:");
        Campocantidad = new JTextField(5);

        ejemploUsoLabel = new JLabel("Ejemplo de uso:");
        campoejemploUso = new JTextField(20);

        herramientasLabel = new JLabel("Herramientas para su uso:");
        Campoherramientas = new JTextField(20);

        Agregarboton = new JButton("Agregar");
        Agregarboton.addActionListener(this);

        borrarBoton = new JButton("Borrar producto");
        borrarBoton.addActionListener(this);

        venderBoton = new JButton("Vender producto");
        venderBoton.addActionListener(this);

        recibirSuminstro = new JButton("Recibir suministro");
        recibirSuminstro.addActionListener(this);

        estadisticasBoton = new JButton("Mostrar estadísticas");
        estadisticasBoton.addActionListener(this);

        listModel = new DefaultListModel<>();
        listadoproducto = new JList<>(listModel);
        listadoproducto.addListSelectionListener(this);

        JScrollPane scrollPane = new JScrollPane(listadoproducto);
        scrollPane.setPreferredSize(new Dimension(450, 200));

        // Añadir componentes al panel
        container.add(productoLabel);
        container.add(Campoproducto);
        container.add(descripcionLabel);
        container.add(Campodescripcion);
        container.add(precioLabel);
        container.add(Campoprecio);
        container.add(cantidadLabel);
        container.add(Campocantidad);
        container.add(ejemploUsoLabel);
        container.add(campoejemploUso);
        container.add(herramientasLabel);
        container.add(Campoherramientas);
        container.add(Agregarboton);
        container.add(borrarBoton);
        container.add(venderBoton);
        container.add(recibirSuminstro);
        container.add(estadisticasBoton);
        container.add(scrollPane);

        ListaProducto = new ArrayList<>();

        setVisible(true);
    }

    public static void main(String[] args) {

        new FerrecorApp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Agregarboton) {
            AgregarProducto();
            mostrarProducto();
        } else if (e.getSource() == borrarBoton) {
            borrarProducto();
        } else if (e.getSource() == venderBoton) {
            venderProducto();
        } else if (e.getSource() == recibirSuminstro) {
            RecibirSuminstro();
        } else if (e.getSource() == estadisticasBoton) {
            mostrarEstadisticas();
        }
    }

    private void AgregarProducto() {
        String nombre = Campoproducto.getText();
        String descripcion = Campodescripcion.getText();
        double precio = Double.parseDouble(Campoprecio.getText());
        int cantidad = Integer.parseInt(Campocantidad.getText());
        String herramientas = Campoherramientas.getText();
        String ejemploUso = campoejemploUso.getText();

        Producto producto = new Producto(nombre, descripcion, precio, cantidad, descripcion, ejemploUso,
                herramientas);
        ListaProducto.add(producto);

    }

    private void mostrarProducto() {
        listModel.removeAllElements();

        for (Producto p : ListaProducto) {
            listModel.addElement(p.getNombreDelProducto());
        }
    }

    private void borrarProducto() {
        String nombreAborrar = JOptionPane.showInputDialog(this, "Ingrese el nombre del producto a borrar: ");

        if (nombreAborrar != null && !nombreAborrar.isEmpty()) {
            for (Producto producto : ListaProducto) {

                if (producto.getNombreDelProducto().equals(nombreAborrar.trim())) {
                    ListaProducto.remove(producto);
                    mostrarProducto();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "No se encontró el producto con ese nombre.", "Producto no encontrado",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void venderProducto() {
        String nombreAvender = JOptionPane.showInputDialog(this, "Ingrese nombre del producto a vender: ");
        if (nombreAvender != null && !nombreAvender.isEmpty()) {
            for (Producto producto : ListaProducto) {
                if (producto.getNombreDelProducto().equals(nombreAvender.trim())) {
                    int cantidadAVender = Integer.parseInt(
                            JOptionPane.showInputDialog(this, "Ingrese la cantidad a vender: "));
                    if (cantidadAVender > 0 && cantidadAVender <= producto.getCantidad()) {
                        producto.setCantidad(producto.getCantidad() - cantidadAVender);
                        mostrarProducto();
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this, "La cantidad ingresada es inválida o supera el stock disponible",
                                "Cantidad inválida", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "No se encontró el producto con ese nombre.", "Producto no encontrado",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void RecibirSuminstro() {
        String nombreARecibir = JOptionPane.showInputDialog(this, "Nombre de producto a recibir suministro: ");
        if (nombreARecibir != null && !nombreARecibir.isEmpty()) {
            for (Producto producto : ListaProducto) {
                if (producto.getNombreDelProducto().equals(nombreARecibir.trim())) {
                    int cantidadARecibir = Integer
                            .parseInt(JOptionPane.showInputDialog(this, "Ingrese la cantidad a recibir: "));
                    if (cantidadARecibir > 0) {
                        producto.setCantidad(producto.getCantidad() + cantidadARecibir);
                        mostrarProducto();
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this, "La cantidad a ingresar no es válida", "Cantidad inválida",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "No se encontró ningún producto con ese nombre.", "Producto no encontrado",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarEstadisticas() {
        // a. Precio total de todos los productos en inventario
        double precioTotal = 0;
        for (Producto producto : ListaProducto) {
            precioTotal += producto.getPrecioDelProducto() * producto.getCantidad();
        }
        JOptionPane.showMessageDialog(this, "Precio total de todos los productos en inventario: $" + precioTotal,
                "Estadísticas", JOptionPane.INFORMATION_MESSAGE);

        // b. Los 2 productos con más stock y los 2 que menos stock tienen
        ListaProducto.sort(Comparator.comparingInt(Producto::getCantidad));
        StringBuilder stockMessage = new StringBuilder("Productos con más stock:\n");
        for (int i = ListaProducto.size() - 1; i >= Math.max(0, ListaProducto.size() - 2); i--) {
            Producto producto = ListaProducto.get(i);
            stockMessage.append(producto.getNombreDelProducto()).append(": ").append(producto.getCantidad())
                    .append(" unidades\n");
        }
        stockMessage.append("\nProductos con menos stock:\n");
        for (int i = 0; i < Math.min(2, ListaProducto.size()); i++) {
            Producto producto = ListaProducto.get(i);
            stockMessage.append(producto.getNombreDelProducto()).append(": ").append(producto.getCantidad())
                    .append(" unidades\n");
        }
        JOptionPane.showMessageDialog(this, stockMessage.toString(), "Estadísticas", JOptionPane.INFORMATION_MESSAGE);

        // c. Herramientas que más se usan para los productos
        Map<String, Integer> herramientasMap = new HashMap<>();
        for (Producto producto : ListaProducto) {
            String[] herramientasArray = producto.getHerramientaParaUsar().split(",");
            for (String herramienta : herramientasArray) {
                herramientasMap.put(herramienta.trim(), herramientasMap.getOrDefault(herramienta.trim(), 0) + 1);
            }
        }
        int maxUsos = 0;
        String herramientaMasUsada = "";
        for (Map.Entry<String, Integer> entry : herramientasMap.entrySet()) {
            if (entry.getValue() > maxUsos) {
                maxUsos = entry.getValue();
                herramientaMasUsada = entry.getKey();
            }
        }
        JOptionPane.showMessageDialog(this, "Herramienta más usada para los productos: " + herramientaMasUsada,
                "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void MostraraDetalleProducto() {
        System.out.println("mostrar detalles...");
        String selectedProducto = listadoproducto.getSelectedValue();
        System.out.println("producto seleccionado" + selectedProducto);

        if (selectedProducto != null) {
            for (Producto producto : ListaProducto) {

                if (producto.getNombreDelProducto().equals(selectedProducto.trim())) {
                    JOptionPane.showMessageDialog(this,
                            "Nombre: " + producto.getNombreDelProducto() + "\n" + "Descripcion: "
                                    + producto.getDescripcionDelProducto() + "\n" + "Precio: "
                                    + producto.getPrecioDelProducto() + "\n" + "Cantidad: " + producto.getCantidad()
                                    + "\n" + "Material de elaboracion: " + producto.getMaterialDelProducto() + "\n"
                                    + "Ejemplo de uso: " + producto.getEjemploDeUso() + "\n"
                                    + "Herramientas para su uso: " + producto.getHerramientaParaUsar() + "\n",
                            "Detalles del producto", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String selectedProducto = listadoproducto.getSelectedValue();
            if (selectedProducto != null && !selectedProducto.equals(ultimoProducto)) {
                MostraraDetalleProducto();
                ultimoProducto = selectedProducto;
            }

        }
    }
}

