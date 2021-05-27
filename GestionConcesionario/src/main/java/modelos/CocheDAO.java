package modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.Conexion;
import utils.UtilidadXml;

public class CocheDAO  extends Coche{

    //SENTENCIAS SQL
	
	private final static String INSERT="INSERT INTO coche (matricula,marca,potencia,color,precio,dni_cliente)"
			+ "VALUES (?,?,?,?,?,NULL)";
	private final static String DELETE="DELETE FROM coche WHERE matricula=?";
	private final static String SELECT_ALL_COCHES="SELECT * FROM coche";
	private final static String SELECT_COCHES_SIN_PROPIETARIO="SELECT * FROM coche WHERE dni_cliente IS NULL";
	private final static String SELECT_COCHES_CON_PROPIETARIO="SELECT * FROM coche WHERE dni_cliente IS NOT NULL";
	private final static String SELECT_MATRICULA="SELECT * FROM coche WHERE matricula=?";
	private final static String SELECT_MARCA="SELECT * FROM coche WHERE marca=?";
	private final static String SELECT_COLOR="SELECT * FROM coche WHERE color=?";
	private final static String SELECT_ALL_MATRICULAS="SELECT matricula FROM coche";

    //CONSTRUCTORES

	Connection con;
	
	public CocheDAO () {
		try {
			con= Conexion.conectar(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public CocheDAO (String matricula, String marca, int potencia, String color, double precio,Cliente cliente) {
	super(matricula,marca,potencia,color,precio,cliente);
	try {
		con=Conexion.conectar(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	public CocheDAO (String matricula, String marca, int potencia, String color ,double precio) {
		super(matricula,marca,potencia,color,precio);
		try {
			con=Conexion.conectar(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public CocheDAO(Coche c) {
		
		this.matricula = c.matricula;
		this.marca = c.marca;
		this.potencia = c.potencia;
		this.color = c.color;
		this.precio = c.precio;
		this.cliente = c.cliente;
		
	}
	public CocheDAO(String matricula) {
		
		this.matricula=matricula;
	}
	
    //METODOS

	/*
	 * añadir
	 * 
	 * Inserta un coche en la base de datos.
	 * */
	public int añadir() {
		int result = 0;
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if(con != null) {
			
			try {
				PreparedStatement q = con.prepareStatement(INSERT);
				q.setString(1, this.matricula);
				q.setString(2, this.marca);
				q.setInt(3, this.potencia);
				q.setString(4, this.color);
				q.setDouble(5, this.precio);
				result = q.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	/*
	 * eliminar:
	 * 
	 * Recibe una matricula de coche.
	 * Borra un coche de la base de datos.
	 * */
	public int eliminar(String matricula) {
		int rs=0;
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if(con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setString(1, matricula);
				rs = q.executeUpdate();
				this.matricula="-1";
				this.marca="";
				this.potencia=-1;
				this.color="";
				this.precio=-1;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return rs;
	}
	/*
	 * mostarMatricula:
	 * 
	 * Recibe la matricula de un coche.
	 * Imprime la informacion del coche.
	 * */
	public  void mostarMatricula(String matricula){
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if(con != null) {
			
			try {
				PreparedStatement q = con.prepareStatement(SELECT_MATRICULA);
				q.setString(1, matricula);
				ResultSet rs= q.executeQuery();
					while(rs.next()) {
						Coche c=new Coche();
						c.setMatricula(rs.getString("matricula"));
						c.setMarca(rs.getString("marca"));
						c.setPotencia(rs.getInt("potencia"));
						c.setColor(rs.getString("color"));
						c.setPrecio(rs.getDouble("precio"));
						c.setCliente(ClienteDAO.getClientePorDNI(rs.getString("dni_cliente")));
						
						System.out.println(c);
						
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	/*
	 * ComprMatriculaExiste:
	 * 
	 * Recibe la matricula de un coche.
	 * Comprueba si la matricula existe en listaMatriculas.
	 * */
	public static boolean ComprMatriculaExiste(String matricula) {
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		boolean result=false;
		if(con != null) {
			
			List<Coche> listaComprobacionCoches = CocheDAO.listaMatriculas();
			for (int i=0;i<listaComprobacionCoches.size();i++) {
				
				if(listaComprobacionCoches.get(i).getMatricula().equals(matricula)) {
					
					//EL COCHE YA EXISTE
					result=true;	
				}
			}
		}
		return result;
	}
	/*
	 * ComprSiTienePropPorMatricula:
	 * 
	 * Recibe la matricula de un coche.
	 * Comprueba si el coche tiene propietario.
	 * */
	public boolean ComprSiTienePropPorMatricula(String matricula){
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		boolean result=false;
		if(con != null) {
			
			try {
				PreparedStatement q = con.prepareStatement(SELECT_MATRICULA);
				q.setString(1, matricula);
				ResultSet rs= q.executeQuery();
					while(rs.next()) {
						Coche c=new Coche();
						Object o=rs.getObject("dni_cliente");
						if(o != null) {
							c.setCliente(ClienteDAO.getClientePorDNI(rs.getString("dni_cliente")));
							result = true;
							
						}
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	/*
	 * mostrarTodosLosCoches:
	 *
	 * Devuelbe una lista de todos los coches.
	 * */
	
	public static List<Coche> mostrarTodosLosCoches(){
		List<Coche> result = new ArrayList<Coche>();
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if(con != null) {
			
			try {
				PreparedStatement q = con.prepareStatement(SELECT_ALL_COCHES);
				ResultSet rs= q.executeQuery();
					while(rs.next()) {
						Coche c=new Coche();
						c.setMatricula(rs.getString("matricula"));
						c.setMarca(rs.getString("marca"));
						c.setPotencia(rs.getInt("potencia"));
						c.setColor(rs.getString("color"));
						c.setPrecio(rs.getDouble("precio"));
						c.setCliente(ClienteDAO.getClientePorDNI(rs.getString("dni_cliente")));
						
						result.add(c);
						
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}
	
	/*
	 * mostrarCochesSinPropietario:
	 *
	 * Devuelbe una lista de todos los coches sin propietario.
	 * */
	
	public static List<Coche> mostrarCochesSinPropietario(){
		List<Coche> result = new ArrayList<Coche>();
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if(con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECT_COCHES_SIN_PROPIETARIO);
				ResultSet rs= q.executeQuery();
				while(rs.next()) {
					Coche c=new Coche();
					c.setMatricula(rs.getString("matricula"));
					c.setMarca(rs.getString("marca"));
					c.setPotencia(rs.getInt("potencia"));
					c.setColor(rs.getString("color"));
					c.setPrecio(rs.getDouble("precio"));
					c.setCliente(ClienteDAO.getClientePorDNI(rs.getString("dni_cliente")));
					
					result.add(c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return result;
	}
	
	/*
	 * mostrarCochesConPropietario:
	 *
	 * Devuelbe una lista de todos los coches con propietario.
	 * */
	
	public static List<Coche> mostrarCochesConPropietario(){

		List<Coche> result = new ArrayList<Coche>();
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if(con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECT_COCHES_CON_PROPIETARIO);
				ResultSet rs= q.executeQuery();
				while(rs.next()) {
					Coche c=new Coche();
					c.setMatricula(rs.getString("matricula"));
					c.setMarca(rs.getString("marca"));
					c.setPotencia(rs.getInt("potencia"));
					c.setColor(rs.getString("color"));
					c.setPrecio(rs.getDouble("precio"));
					c.setCliente(ClienteDAO.getClientePorDNI(rs.getString("dni_cliente")));
					
					result.add(c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return result;
	
	}
	
	/*
	 * mostarMarca:
	 * Recibe la marca de coche
	 * Devuelbe una lista de todos los coches de esa marca.
	 * */
	
	public static List<Coche> mostarMarca(String marca){
		List<Coche> result = new ArrayList<Coche>();
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if(con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECT_MARCA);
				q.setString(1, marca);
				ResultSet rs= q.executeQuery();
				while(rs.next()) {
					Coche c=new Coche();
					c.setMatricula(rs.getString("matricula"));
					c.setMarca(rs.getString("marca"));
					c.setPotencia(rs.getInt("potencia"));
					c.setColor(rs.getString("color"));
					c.setPrecio(rs.getDouble("precio"));
					c.setCliente(ClienteDAO.getClientePorDNI(rs.getString("dni_cliente")));
					
					result.add(c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return result;	
	}
	
	/*
	 * mostarColor:
	 * Recibe el color de coche
	 * Devuelbe una lista de todos los coches de ese color.
	 * */
	
	public static List<Coche> mostarColor(String color){
		List<Coche> result = new ArrayList<Coche>();
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		if(con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SELECT_COLOR);
				q.setString(1, color);
				ResultSet rs= q.executeQuery();
				while(rs.next()) {
					Coche c=new Coche();
					c.setMatricula(rs.getString("matricula"));
					c.setMarca(rs.getString("marca"));
					c.setPotencia(rs.getInt("potencia"));
					c.setColor(rs.getString("color"));
					c.setPrecio(rs.getDouble("precio"));
					c.setCliente(ClienteDAO.getClientePorDNI(rs.getString("dni_cliente")));
					
					result.add(c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return result;	
	}
	
	/*
	 * listaMatriculas:
	 * 
	 * Devuelbe una lista de todas las matriculas de los coches 
	 * que existen en la base de datos.
	 * */
	
	private static List<Coche> listaMatriculas(){
		List<Coche> result = new ArrayList<Coche>();
		Connection con = Conexion.getConexion(UtilidadXml.loadFile("es.iesfranciscodelosrios.conexion.xml"));
		try {
			PreparedStatement q = con.prepareStatement(SELECT_ALL_MATRICULAS);
			ResultSet rs= q.executeQuery();
				while(rs.next()) {
					Coche c=new Coche();
					c.setMatricula(rs.getString("matricula"));
					
					result.add(c);
					
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return result;
	}
	
	
}