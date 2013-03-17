package pl.altoriis.def;

import java.sql.*;
import java.util.ArrayList;

public class db {

	private static properties properties = new properties();
	private Connection cn = null;
	private Statement st = null;
	
	
	public db()  {
		
		
		try {
			
			cn = DriverManager.getConnection(properties.url , properties.user, properties.password);
			st = cn.createStatement();
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

	
	
	public void updateData(String update){
		
		try {
			System.out.println(update);	
			st.executeUpdate(update);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<ArrayList<String>> getPragma(String tName) {
	
	 return getData("pragma table_info("+tName+")");	
	}
	
	
	
	public ArrayList<ArrayList<String>> getData(String query) {

		ResultSet rs = null;
	
	
		ArrayList<ArrayList<String>> arResult = new ArrayList<ArrayList<String>>(); 

		try {

			rs = st.executeQuery(query);
			
	
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
