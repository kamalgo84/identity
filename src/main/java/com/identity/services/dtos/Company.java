package com.identity.services.dtos;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Company")
public class Company extends User {
	
	private String nombre_empresa;
	private String nombre_acr;
	private String apellidos_1;
	private String apellidos_2;
	private String fecha_establecimiento;
	private String lugar_de_actividad;
	private String provicia_actividad;
	private String direccion_habitual;
	private String tipo_de_via;
	private String nombre_de_la_calle;
	private String numero;
	private String bloque;
	private String portal;
	private String planta;
	private String planta_letra;
	private String codigo_postal;
	private String municipio;
	private String provincia;
	private String pais_nombre;
	private String codigo_pais;
	public String getNombre_empresa() {
		return nombre_empresa;
	}
	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}
	public String getNombre_acr() {
		return nombre_acr;
	}
	public void setNombre_acr(String nombre_acr) {
		this.nombre_acr = nombre_acr;
	}
	public String getApellidos_1() {
		return apellidos_1;
	}
	public void setApellidos_1(String apellidos_1) {
		this.apellidos_1 = apellidos_1;
	}
	public String getApellidos_2() {
		return apellidos_2;
	}
	public void setApellidos_2(String apellidos_2) {
		this.apellidos_2 = apellidos_2;
	}
	public String getFecha_establecimiento() {
		return fecha_establecimiento;
	}
	public void setFecha_establecimiento(String fecha_establecimiento) {
		this.fecha_establecimiento = fecha_establecimiento;
	}
	public String getLugar_de_actividad() {
		return lugar_de_actividad;
	}
	public void setLugar_de_actividad(String lugar_de_actividad) {
		this.lugar_de_actividad = lugar_de_actividad;
	}
	public String getProvicia_actividad() {
		return provicia_actividad;
	}
	public void setProvicia_actividad(String provicia_actividad) {
		this.provicia_actividad = provicia_actividad;
	}
	public String getDireccion_habitual() {
		return direccion_habitual;
	}
	public void setDireccion_habitual(String direccion_habitual) {
		this.direccion_habitual = direccion_habitual;
	}
	public String getTipo_de_via() {
		return tipo_de_via;
	}
	public void setTipo_de_via(String tipo_de_via) {
		this.tipo_de_via = tipo_de_via;
	}
	public String getNombre_de_la_calle() {
		return nombre_de_la_calle;
	}
	public void setNombre_de_la_calle(String nombre_de_la_calle) {
		this.nombre_de_la_calle = nombre_de_la_calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBloque() {
		return bloque;
	}
	public void setBloque(String bloque) {
		this.bloque = bloque;
	}
	public String getPortal() {
		return portal;
	}
	public void setPortal(String portal) {
		this.portal = portal;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getPlanta_letra() {
		return planta_letra;
	}
	public void setPlanta_letra(String planta_letra) {
		this.planta_letra = planta_letra;
	}
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais_nombre() {
		return pais_nombre;
	}
	public void setPais_nombre(String pais_nombre) {
		this.pais_nombre = pais_nombre;
	}
	public String getCodigo_pais() {
		return codigo_pais;
	}
	public void setCodigo_pais(String codigo_pais) {
		this.codigo_pais = codigo_pais;
	}
	@Override
	public String toString() {
		return "Company [nombre_empresa=" + nombre_empresa + ", nombre_acr=" + nombre_acr + ", apellidos_1="
				+ apellidos_1 + ", apellidos_2=" + apellidos_2 + ", fecha_establecimiento=" + fecha_establecimiento
				+ ", lugar_de_actividad=" + lugar_de_actividad + ", provicia_actividad=" + provicia_actividad
				+ ", direccion_habitual=" + direccion_habitual + ", tipo_de_via=" + tipo_de_via
				+ ", nombre_de_la_calle=" + nombre_de_la_calle + ", numero=" + numero + ", bloque=" + bloque
				+ ", portal=" + portal + ", planta=" + planta + ", planta_letra=" + planta_letra + ", codigo_postal="
				+ codigo_postal + ", municipio=" + municipio + ", provincia=" + provincia + ", pais_nombre="
				+ pais_nombre + ", codigo_pais=" + codigo_pais + "]";
	}
	
	
	
	
	



}
