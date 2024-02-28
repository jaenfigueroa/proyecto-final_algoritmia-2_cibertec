package frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import clases.Cliente;
import managers.ClienteManager;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ClientesFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_nombres;
	private JTextField tf_apellidos;
	private JTextField tf_direccion;
	private JTextField tf_dni;
	private JTextField tf_telefono;
	private JTextField tf_codigoCliente;
	private JTable tblTabla;
	private DefaultTableModel modelo;

	/**
	 * Create the frame.
	 */
	public ClientesFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombres");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 43, 96, 13);
		contentPane.add(lblNewLabel);

		tf_nombres = new JTextField();
		tf_nombres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_nombres.setBounds(107, 40, 289, 19);
		contentPane.add(tf_nombres);
		tf_nombres.setColumns(10);

		tf_apellidos = new JTextField();
		tf_apellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_apellidos.setColumns(10);
		tf_apellidos.setBounds(107, 66, 289, 19);
		contentPane.add(tf_apellidos);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblApellidos.setBounds(10, 69, 96, 13);
		contentPane.add(lblApellidos);

		tf_direccion = new JTextField();
		tf_direccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_direccion.setColumns(10);
		tf_direccion.setBounds(107, 95, 289, 19);
		contentPane.add(tf_direccion);

		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDireccin.setBounds(10, 98, 96, 13);
		contentPane.add(lblDireccin);

		JLabel lblNewLabel_1_1 = new JLabel("DNI");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 156, 96, 13);
		contentPane.add(lblNewLabel_1_1);

		tf_dni = new JTextField();
		tf_dni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_dni.setColumns(10);
		tf_dni.setBounds(107, 153, 289, 19);
		contentPane.add(tf_dni);

		tf_telefono = new JTextField();
		tf_telefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_telefono.setColumns(10);
		tf_telefono.setBounds(107, 124, 289, 19);
		contentPane.add(tf_telefono);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefono.setBounds(10, 127, 96, 13);
		contentPane.add(lblTelefono);

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCodigo.setBounds(10, 13, 133, 13);
		contentPane.add(lblCodigo);

		tf_codigoCliente = new JTextField();
		tf_codigoCliente.setText("0");
		tf_codigoCliente.setEditable(false);
		tf_codigoCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_codigoCliente.setColumns(10);
		tf_codigoCliente.setBounds(107, 10, 289, 19);
		contentPane.add(tf_codigoCliente);

		JComboBox cb_opciones = new JComboBox();
		cb_opciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcionElegida = cb_opciones.getSelectedIndex();

				if (opcionElegida == 1 || opcionElegida == 2 || opcionElegida == 3) {
					tf_codigoCliente.setEditable(true);
				} else {
					tf_codigoCliente.setEditable(false);
				}
			}
		});
		cb_opciones.setModel(
				new DefaultComboBoxModel(new String[] { "Crear", "Modificar", "Consultar", "Eliminar", "Listar" }));
		cb_opciones.setBounds(459, 12, 226, 19);
		contentPane.add(cb_opciones);

		JButton btn_ok = new JButton("OK");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Recoger los valores ingresados por el usuario
				String nombres = tf_nombres.getText();
				String apellidos = tf_apellidos.getText();
				String direccion = tf_direccion.getText();
				String telefono = tf_telefono.getText();
				String dni = tf_dni.getText();

				// Recoger la accion a realizar
				int accionARealizar = cb_opciones.getSelectedIndex();

				// Elegir la accion a realizar
				
				Cliente cliente = null;
				int codigoCliente = Integer.parseInt(tf_codigoCliente.getText());

				switch (accionARealizar) {

				// CREAR
				case 0:
					cliente = new Cliente(nombres, apellidos, direccion, telefono, dni);
					ClienteManager.agregarCliente(cliente);

					// Mostrar el codigo de cliente recién creado
					tf_codigoCliente.setText(cliente.getCodigoCliente() + "");
					rellenartabla();
					break;

				// MODIFICAR
				case 1:
					Cliente clienteModificado = ClienteManager.modificarCliente(
							codigoCliente,
							nombres,
							apellidos,
							direccion,
							telefono,
							dni);
					rellenartabla();
					break;

				// CONSULTAR
				case 2:
					Cliente clienteEncontrado = ClienteManager.consultarCliente(codigoCliente);
					limpiarTabla();
					rellenartabla(clienteEncontrado);
					break;

				// ELIMINAR
				case 3:
					ClienteManager.eliminarCliente(codigoCliente);
					rellenartabla();
					break;			

				// LISTAR
				case 4:
					rellenartabla();
					break;	
			}}
		});
		btn_ok.setBounds(600, 41, 85, 21);
		contentPane.add(btn_ok);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 202, 675, 380);
		contentPane.add(scrollPane_1);

		tblTabla = new JTable();
		tblTabla.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(tblTabla);

		modelo = new DefaultTableModel();
		modelo.addColumn("Código");
		modelo.addColumn("Nombres");
		modelo.addColumn("Apellidos");
		modelo.addColumn("Dirección");
		modelo.addColumn("Teléfono");
		modelo.addColumn("DNI");

		tblTabla.setModel(modelo);
		
		JButton btnNewButton_1 = new JButton("Limpiar tabla");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
			}
		});
		btnNewButton_1.setBounds(565, 171, 120, 21);
		contentPane.add(btnNewButton_1);
	}

	// Metodos	
	public void limpiarTabla() {		
		while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
	}
	
	public void rellenartabla(Cliente x) {		
		modelo.setRowCount(0);
		Object[] fila = {
				x.getCodigoCliente(),
				x.getNombres(),
				x.getApellidos(),
				x.getDireccion(),
				x.getTelefono(),
				x.getDni(),
		};
		modelo.addRow(fila);
	}
	
	public void rellenartabla() {
		ArrayList<Cliente> clientes = ClienteManager.listarClientes();
		
		for (int i = 0; i < clientes.size(); i++) {
			
			modelo.setRowCount(i);
			Object[] fila = {
					clientes.get(i).getCodigoCliente(),
					clientes.get(i).getNombres(),
					clientes.get(i).getApellidos(),
					clientes.get(i).getDireccion(),
					clientes.get(i).getTelefono(),
					clientes.get(i).getDni(),
			};
			modelo.addRow(fila);
		}
	}
}
