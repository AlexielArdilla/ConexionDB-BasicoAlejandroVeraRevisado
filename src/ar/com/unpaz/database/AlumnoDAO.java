package ar.com.unpaz.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.unpaz.database.MiConexion;
import ar.com.unpaz.model.Alumno;

public class AlumnoDAO {

	public List<Alumno> getAlumnos(){
		
		List<Alumno> alumnos = new ArrayList<Alumno>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = MiConexion.getConnection();
			ps = con.prepareStatement(
					"SELECT ALUMNO.ID, ALUMNO.APEL_NOMBRE FROM ALUMNO");
			rs = ps.executeQuery();

			while (rs.next()) {

				Alumno alumno = new Alumno();
			
				alumno.setId(rs.getInt(1));
				alumno.setNombre(rs.getString(2));
				alumnos.add(alumno);
			}
			
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}										
		}
						
		return alumnos;
		
	}
	
	public ResultSet getAllAlumnos() {

		Connection con = MiConexion.getConnection();
		ResultSet rs = null;
		try {
			Statement s = con.createStatement();
			rs = s.executeQuery(
					"select id as Legajo, Apel_Nombre as [Apellido y Nombre], dbo.fn_porcentaje_carrera(a.id) as Porcentaje from Alumno a ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}
	
	public int agregarAlumno(String apel_nombre) {

		Connection cn = MiConexion.getConnection();
		int r = 0;

		try {
			String query = "INSERT into alumno (id, apel_nombre) values (?,?)";
			PreparedStatement p = cn.prepareStatement(query);

			String query_max_id = "select MAX (ID) from ALUMNO";
			PreparedStatement max_id = cn.prepareStatement(query_max_id);
			ResultSet rss = max_id.executeQuery();

			int id_to_int = 0;

			while (rss.next()) {

				id_to_int = rss.getInt(1);

			}

			p.setInt(1, id_to_int + 1);
			p.setString(2, apel_nombre);

			r = p.executeUpdate();

			if (r > 0) {
				System.out.println("insert Alumno exitoso");

			}
			cn.close();
		} catch (SQLException e) {

			System.out.println("Algo ha fallado!!!");

			e.printStackTrace();
		}

		return r;

	}
	
	public String getNombreAlumnoByID(int id_alumno){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Alumno alumno = null;
		
		try {
			con = MiConexion.getConnection();
			ps = con.prepareStatement(
					"SELECT ALUMNO.ID, ALUMNO.APEL_NOMBRE FROM ALUMNO WHERE ALUMNO.ID = "+ id_alumno);
			rs = ps.executeQuery();

			while (rs.next()) {

				alumno = new Alumno();
			    alumno.setId(rs.getInt(1));
				alumno.setNombre(rs.getString(2));
				
			}
			
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}										
		}
		
		
		return alumno.getNombre();
	}

}
