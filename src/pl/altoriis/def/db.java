package pl.altoriis.def;

import java.sql.*;
import java.util.ArrayList;

public class db {

	private final properties properties = new properties();
	private Connection cn = null;
	private Statement st = null;
	
	
	public db() {
		try {

			String url = this.properties.url;
			String user = this.properties.user;
			String password = this.properties.password;

			cn = DriverManager.getConnection(url, user, password);
			st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	} /* end of db() constructor method */

	protected void finalize() {
		try {
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}/* end of finalize() method */

	
	public ArrayList<ArrayList<String>> getData(String query) {

		ResultSet rs = null;
	
	
		ArrayList<ArrayList<String>> arResult = new ArrayList<ArrayList<String>>(); 
		ArrayList<String> colNames = new ArrayList<String>(); 

		try {

			rs = st.executeQuery(query);
			for (int v =1;v <= rs.getMetaData().getColumnCount();v++){
			colNames.add(rs.getMetaData().getColumnLabel(v));
			}
			arResult.add(colNames);
	
			while (rs.next()) {
			ArrayList<String> arRow = new ArrayList<String>();
					for (int w = 1; w <= rs.getMetaData().getColumnCount(); w++) {
						
						arRow.add(rs.getString(w));
						}
					
					arResult.add(arRow);
			
				
			}
					
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		
		return arResult;
	} /* end of get_data() method */

}/* end of db class */
