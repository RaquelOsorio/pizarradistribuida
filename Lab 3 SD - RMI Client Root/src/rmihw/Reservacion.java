/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rmihw;

import java.io.Serializable;

/**
 *
 * @author alonso
 */
public class Reservacion implements Serializable  {
private String modulo;
	private String hora;
	private String reserva;
	private String descripcion;

	public Reservacion(String modulo, String hora, String reserva, String descripcion) {
		this.modulo = modulo;
		this.hora = hora;
		this.reserva = reserva;
		this.descripcion = descripcion;
	}



	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getReserva() {
		return reserva;
	}

	public void setReserva(String reserva) {
		this.reserva = reserva;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
