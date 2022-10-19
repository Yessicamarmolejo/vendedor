package com.marmolejo.vendedor.model;

/**
 *
 * @author Yessica
 */

public class Vendedor {
	private int id;
	private String codigo;
	private String nombre;
	private String telefono;
	private String email;
	private double salario;
	
	public Vendedor(int id, String codigo, String nombre, String telefono ,String email, double salario) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.salario = salario;
	}
	//getters y setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String gettelefono() {
		return telefono;
	}
	public void settelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public double getsalario() {
		return salario;
	}
	public void setsalario(double salario) {
		this.salario = salario;
	}
}
