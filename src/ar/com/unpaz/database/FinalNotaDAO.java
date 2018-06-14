package ar.com.unpaz.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ar.com.unpaz.database.MiConexion;
import ar.com.unpaz.model.FinalNota;

public class FinalNotaDAO {
	
	List<FinalNota> finales = new LinkedList<>();
	
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public List<FinalNota> getNotasByIDAlumno(int id_alumno) {

		String query = "SELECT FINALES.ID, FINALES.ID_ALUMNO, FINALES.ID_MATERIA, FINALES.NOTA "
				+ "FROM FINALES WHERE FINALES.ID_ALUMNO = " + id_alumno;

		try {

			setConnection();
			passPrepStatementAndExQuery(query);
			executeResultSetReturn();
			CloseConnection();
			return finales;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return finales;
	}//*******************************
	
	public List<FinalNota> getFullQueryByNota(double nota) {

		String query = "SELECT FINALES.ID, FINALES.ID_ALUMNO, FINALES.ID_MATERIA, FINALES.NOTA "
				+ "FROM FINALES WHERE FINALES.NOTA = " + nota;

		try {

			setConnection();
			passPrepStatementAndExQuery(query);
			executeResultSetReturn();
			CloseConnection();
			return finales;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return finales;
	}//*******************************
	
	public void setConnection () throws SQLException{
		
		con = MiConexion.getConnection();
		
	}
	
	public void passPrepStatementAndExQuery(String query)throws SQLException{
		
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		
	}
	
	public void CloseConnection() throws SQLException{
		
		rs.close();
		ps.close();
		con.close();
		
	}
	
	public void executeResultSetReturn()throws SQLException{
		
		while (rs.next()) {

			FinalNota finalNota = new FinalNota();

			finalNota.setId(rs.getInt(1));
			finalNota.setId_alumno(rs.getInt(2));
			finalNota.setId_materia(rs.getInt(3));
			finalNota.setNota(rs.getDouble(4));
			
			
			finales.add(finalNota);
		}
		
	}

	public ResultSet getAllFinlasByAlumno(int idAlumno) {

		Connection con = MiConexion.getConnection();
		String sql = "select M.ID CODIGO, DESCRIPCION, NOTA, "
				+ "CASE WHEN NOTA >= 4 THEN 'APROBADA' ELSE 'NO APROBADA' END RESULTADO "
				+ " FROM FINALES F INNER JOIN MATERIA M ON F.ID_MATERIA = M.ID " + "WHERE F.ID_ALUMNO = ?";
		ResultSet rs = null;
		try {
			PreparedStatement s = con.prepareStatement(sql);
			s.setInt(1, idAlumno);
			rs = s.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;

	}
	
	public int borrarFinal(int idAlumno, int idMateria, BigDecimal nota) {
		Connection con = MiConexion.getConnection();
		String sql = "DELETE FROM FINALES " + "WHERE ID_ALUMNO = ? " + " AND ID_MATERIA = ? " + " AND NOTA = ? ";

		int r = 0;
		try {
			PreparedStatement s = con.prepareStatement(sql);
			s.setInt(1, idAlumno);
			s.setInt(2, idMateria);
			s.setBigDecimal(3, nota);

			r = s.executeUpdate();
		} catch (Exception e) {
			r = 0;
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return r;

	}

	public int agregarFinal(int idAlumno, int idMateria, BigDecimal nota) {

		Connection cn = MiConexion.getConnection();
		int r = 0;

		try {
			String query = "INSERT into finales (Id_alumno, id_materia, nota,Id) values (?,?,?,?)";
			PreparedStatement p = cn.prepareStatement(query);

			String query_max_id = "select MAX (ID) from FINALES";
			PreparedStatement max_id = cn.prepareStatement(query_max_id);
			ResultSet rss = max_id.executeQuery();

			int id_to_int = 0;

			while (rss.next()) {

				id_to_int = rss.getInt(1);

			}

			p.setInt(1, idAlumno);
			p.setInt(2, idMateria);
			p.setBigDecimal(3, nota);
			p.setInt(4, id_to_int + 1);

			r = p.executeUpdate();

			if (r > 0) {
				System.out.println("insert exitoso");

			}
			cn.close();
		} catch (SQLException e) {

			System.out.println("Algo ha fallado!!!");

			e.printStackTrace();
		}

		return r;
	}

}
