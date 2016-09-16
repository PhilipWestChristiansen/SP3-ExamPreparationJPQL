package test;

import java.util.HashMap;
import javax.persistence.Persistence;

public class main {
    
    public static void main(String[] args) {
        
        HashMap<String, Object> puproperties = new HashMap<>();
        
        //puproperties.put("javax.persistence.sql-load-script-source", "scripts/ClearDB.sql");
        puproperties.put("javax.persistence.schema-generation.database.action", "drop-and-create");
        
        Persistence.generateSchema("PersistenceUnit", puproperties);
        Persistence.generateSchema("PersistenceUnit", null);
        
    }
    
}
