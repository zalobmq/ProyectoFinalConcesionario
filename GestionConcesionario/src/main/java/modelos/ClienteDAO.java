package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.Conexion;
//import utils.InsertarPorTeclado;
import utils.UtilidadXml;
//import vistas.vistasListas;

public class ClienteDAO extends Cliente {

	// SENTENCIAS SQL

	private final static String ASIGNAR = "UPDATE coche SET dni_cliente=? WHERE matricula=?";
	private final static String RETIRAR = "UPDATE coche SET dni_cliente=NULL WHERE matricula=? AND dni_cliente=?";
	private final static String SELECT_COCHES_CLIENTE = "SELECT * FROM coche WHERE dni_cliente=(SELECT dni FROM cliente WHERE dni=?)";
	private final static String SELECT_INFO_CLIENTE = "SELECT * FROM cliente WHERE dni=?";
	private final static String SELECT_ALL_CLIENTE = "SELECT * FROM cliente";

	// CONSTRUCTORES

	public ClienteDAO() {

	}

	public ClienteDAO(String dni, String nombre, int edad, int telefono) {
		super(dni, nombre, edad, telefono);
	}

	// HACER EL CONSTRUCTOR CONTODOS LOSD DATOS DEL CLIENTE , PARACUANDO LO
	// SELECIONE DE LA LISTA TENGA EL CLIENTE ENTERO
	public ClienteDAO(String dni) {
		this.dni = dni;
	}

	public ClienteDAO(Cliente cl) {

		this.dni = cl.dni;
		this.nombre = cl.nombre;
		this.edad = cl.edad;
		this.telefono = cl.telefono;
	}

	// METODOS

	/*
	 * Asignar:
	 * 
	 * Recibe el dni del cliente y la matricula del coche. Asignara el coche al
	 * cliente.
	 */

	public int asignar(String matricula) {
		int rs = 0;
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(ASIGNAR);
				q.setString(1, dni);
				q.setString(2, matricula);
				rs = q.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	/*
	 * Retirar:
	 * 
	 * Recibe el dni del cliente y la matricula del coche. Retirara el coche al
	 * cliente.
	 */

	public int retirar(String matricula) {
		int rs = 0;

		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(RETIRAR);
				q.setString(1, matricula);
				q.setString(2, dni);
				rs = q.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	/*<y
	 * MostrarInfoCliente:
	 * 
	 * Recibe el dni del cliente. Devolvera la informacion de dicho cliente.
	 */
	public void MostrarInfoCliente(String dni) {

		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		Cliente cl = new Cliente();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECT_INFO_CLIENTE);
				q.setString(1, dni);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					cl.setDni(rs.getString("dni"));
					cl.setNombre(rs.getString("nombre"));
					cl.setEdad(rs.getInt("edad"));
					cl.setTelefono(rs.getInt("telefono"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println(cl);
	}
	/*
	 * getClientePorDNI:
	 * 
	 * Recibe el dni del cliente. Devolvera un ClienteDAO.
	 */

	public static ClienteDAO getClientePorDNI(String dni) {

		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		ClienteDAO result = new ClienteDAO();

		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECT_INFO_CLIENTE);
				q.setString(1, dni);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					result.setDni(rs.getString("dni"));
					result.setNombre(rs.getString("nombre"));
					result.setEdad(rs.getInt("edad"));
					result.setTelefono(rs.getInt("telefono"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override

	/*
	 * getMisCoches:
	 * 
	 * Devolvera la lista de coches de un cliente.
	 */

	public List<Coche> getMisCoches() {
		List<Coche> result = super.getMisCoches();
		if (result == null) {
			Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
			if (con != null) {
				result = new ArrayList<>();
				try {
					PreparedStatement q = con.prepareStatement(SELECT_COCHES_CLIENTE);
					q.setString(1, this.dni);
					ResultSet rs = q.executeQuery();
					while (rs.next()) {
						Coche c = new Coche();
						c.setMatricula(rs.getString("matricula"));
						c.setMarca(rs.getString("marca"));
						c.setPotencia(rs.getInt("potencia"));
						c.setColor(rs.getString("color"));
						c.setPrecio(rs.getDouble("precio"));
						c.setCliente(this);
						result.add(c);
					}
					super.setMisCoches(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/*
	 * MostrarTodos
	 * 
	 * Devolvera una lista con la informacion de todos los clientes.
	 */

	public static List<Cliente> MostrarTodos() {
		List<Cliente> result = new ArrayList<Cliente>();
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if (con != null) {

			try {
				PreparedStatement q = con.prepareStatement(SELECT_ALL_CLIENTE);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					Cliente cl = new Cliente();
					cl.setDni(rs.getString("dni"));
					cl.setNombre(rs.getString("nombre"));
					cl.setEdad(rs.getInt("edad"));
					cl.setTelefono(rs.getInt("telefono"));

					result.add(cl);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}
	/*
	public ClienteDAO clienteParaAsignacion() {
		List<Cliente> lista = ClienteDAO.MostrarTodos();
		vistasListas.listaClientes(lista);
		System.out.println("ELIGE UN CLIENTE");
		int num_seleccionado = InsertarPorTeclado.getIntWithRange(0, lista.size() - 1);
		ClienteDAO c = null;
		for (int i = 0; i < lista.size(); i++) {
			if (num_seleccionado == i) {
				c = new ClienteDAO(lista.get(i).getDni());
				System.out.println("Inserta la matricula del coche");
				String matricula = InsertarPorTeclado.getString();
				if (CocheDAO.ComprMatriculaExiste(matricula)) {
					c.asignar(matricula);
					System.out.println("COCHE ASIGNADO");
				} else {
					System.out.println("EL COCHE NO EXISTE, NO SE PUDO ASIGNAR");
				}
			}
		}
		return c;
	}

	public ClienteDAO clienteParaDesAsignacion() {

		ClienteDAO c = null;
		// PARA CLIENTES
		List<Cliente> listaClientes = ClienteDAO.MostrarTodos();
		vistasListas.listaClientes(listaClientes);
		System.out.println("ELIGE UN CLIENTE");
		int num_seleccionadoCliente = InsertarPorTeclado.getIntWithRange(0, listaClientes.size() - 1);

		for (int i = 0; i < listaClientes.size(); i++) {
			
			if (num_seleccionadoCliente == i) {
				c = new ClienteDAO(listaClientes.get(i).getDni());
				// PARA COCHES
				List<Coche> listaCoches = c.getMisCoches();
				if(listaCoches.size() !=0) {
				vistasListas.listaCoches(listaCoches);
				System.out.println("ELIGE UN COCHE");
				int num_seleccionadoCoche = InsertarPorTeclado.getIntWithRange(0, listaClientes.size() - 1);

				for (int j = 0; j < listaCoches.size(); j++) {

					if (num_seleccionadoCoche == j) {

						c.retirar(listaCoches.get(j).getMatricula());
						System.out.println("COCHE RETIRARO");
					} else {
						System.out.println("EROR AL RETIRARLO");
					}

				}
				}else {
					System.out.println("EL CLIENTE NO TIENE COCHES");
				}
			}
		}
		return c;
	}
	*/
}