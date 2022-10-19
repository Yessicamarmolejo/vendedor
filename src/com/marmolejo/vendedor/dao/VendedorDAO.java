package com.marmolejo.vendedor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.marmolejo.vendedor.model.Vendedor;
import com.marmolejo.vendedor.model.Conexion;

/**
 *
 * @author Yessica
 */

public class VendedorDAO {
	private Conexion con;
	private Connection connection;

	public VendedorDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}

	// insertar artículo
	public boolean insertar(Vendedor Vendedor) throws SQLException {
		String sql = "INSERT INTO Vendedor (id, codigo, nombre, telefono, email, salario) VALUES (?, ?, ?,?,?,?)";
		System.out.println(Vendedor.gettelefono());
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, null);
		statement.setString(2, Vendedor.getCodigo());
		statement.setString(3, Vendedor.getNombre());
		statement.setString(4, Vendedor.gettelefono());
		statement.setString(5, Vendedor.getemail());
		statement.setDouble(6, Vendedor.getsalario());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}

	// listar todos los productos
	public List<Vendedor> listarVendedor() throws SQLException {

		List<Vendedor> listaVendedor = new ArrayList<Vendedor>();
		String sql = "SELECT * FROM articulos";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);

		while (resulSet.next()) {
			int id = resulSet.getInt("id");
			String codigo = resulSet.getString("codigo");
			String nombre = resulSet.getString("nombre");
			String telefono = resulSet.getString("telefono");
			String email = resulSet.getString("email");
			Double salario = resulSet.getDouble("salario");
			Vendedor Vendedor = new Vendedor(id, codigo, nombre, telefono, email, salario);
			listaVendedor.add(Vendedor);
		}
		con.desconectar();
		return listaVendedor;
	}

	// obtener por id
	public Vendedor obtenerPorId(int id) throws SQLException {
		Vendedor Vendedor = null;

		String sql = "SELECT * FROM Vendedor WHERE id= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet res = statement.executeQuery();
		if (res.next()) {
			Vendedor = new Vendedor(res.getInt("id"), res.getString("codigo"), res.getString("nombre"),
					res.getString("telefono"), res.getString("email"), res.getDouble("salario"));
		}
		res.close();
		con.desconectar();

		return Vendedor;
	}

	// actualizar
	public boolean actualizar(Vendedor Vendedor) throws SQLException {
		boolean rowActualizar = false;
		String sql = "UPDATE Vendedor SET codigo=?,nombre=?,telefono=?,email=?, salario=? WHERE id=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, Vendedor.getCodigo());
		statement.setString(2, Vendedor.getNombre());
		statement.setString(3, Vendedor.gettelefono());
		statement.setString(4, Vendedor.getemail());
		System.out.println(Vendedor.getsalario());
		statement.setDouble(5, Vendedor.getsalario());
		statement.setInt(6, Vendedor.getId());

		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Vendedor Vendedor) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM Vendedor WHERE ID=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, Vendedor.getId());

		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();

		return rowEliminar;
	}

    public List<Vendedor> listaVendedor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
