package pl.altoriis.def;

import com.j256.ormlite.*;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "pClass")
public class pClass {

	
	@DatabaseField(id = true)
    private String name;
    @DatabaseField
    private String password;
    
    public pClass() {
        // ORMLite needs a no-arg constructor 
    }
    public pClass(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
	
	
}
