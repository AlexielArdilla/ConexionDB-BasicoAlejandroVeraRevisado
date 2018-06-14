package ar.com.unpaz.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.com.unpaz.model.Materia;

public class MateriaDAO {
	
	public List<Materia> getMaterias(){
		
		List<Materia> materias = new LinkedList<>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = MiConexion.getConnection();
			ps = con.prepareStatement("SELECT MATERIA.ID, MATERIA.DESCRIPCION FROM MATERIA");
			rs = ps.executeQuery();

			while (rs.next()) {

				Materia materia = new Materia();
				materia.setId(rs.getInt(1));
				materia.setDescripcion(rs.getString(2));
				
				materias.add(materia);
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
						
		return materias;
		
	}
		
		
	public String nombreMateriaPorID(int id_materia){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Materia materia = null;

		try {
			con = MiConexion.getConnection();
			ps = con.prepareStatement("SELECT MATERIA.ID, MATERIA.DESCRIPCION FROM MATERIA WHERE MATERIA.ID = "+ id_materia);
			rs = ps.executeQuery();

			while (rs.next()) {

				materia = new Materia();
				materia.setId(rs.getInt(1));
				materia.setDescripcion(rs.getString(2));
				
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
		
		
		
		return materia.getDescripcion();
	}	
	
	
	public ResultSet getAllMateriasByNota(double nota) {

		Connection con = MiConexion.getConnection();
		String sql = "SELECT ALUMNO.APEL_NOMBRE, MATERIA.DESCRIPCION, FINALES.NOTA FROM MATERIA "
				+ "JOIN FINALES ON MATERIA.ID = FINALES.ID_MATERIA JOIN ALUMNO ON FINALES.ID_ALUMNO = ALUMNO.ID "
				+ "WHERE FINALES.NOTA = " + nota;
		ResultSet rs = null;
		try {
			PreparedStatement s = con.prepareStatement(sql);
			rs = s.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;

	}
	
	public ResultSet getMateriasYSusIDs() {

		Connection con = MiConexion.getConnection();
		String sql = "SELECT MATERIA.DESCRIPCION, MATERIA.ID FROM MATERIA";
		ResultSet rs = null;
		try {
			PreparedStatement s = con.prepareStatement(sql);
			rs = s.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;

	}
	
	

}
