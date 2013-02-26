package pl.altoriis.def;

import java.sql.*;

public class db {

	public final properties properties = new properties();
	public Connection cn = null;
	public Statement st = null;

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

	public String[][] get_data(String query, Integer columns) {

		ResultSet rs = null;
		int rsSize = 0;
		String[][] wynik = null;

		try {

			rs = st.executeQuery(query);

			try {
				rs.last();
				rsSize = rs.getRow();
				rs.beforeFirst();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			wynik = new String[rsSize][columns];

			while (rs.next()) {
				for (int w = 0; w < columns; w++) {

					wynik[rs.getRow() - 1][w] = rs.getString(w + 1);
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		return wynik;
	} /* end of get_data() method */

}/* end of db class */
