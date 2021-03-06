import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/library_catalog_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteBookQuery = "DELETE FROM books *;";
      String deleteAuthorQuery = "DELETE FROM authors *;";
      String deletePatronQuery = "DELETE FROM patrons *;";
      String deleteLogbookQuery = "DELETE FROM log_records *;";
      con.createQuery(deleteBookQuery).executeUpdate();
      con.createQuery(deleteAuthorQuery).executeUpdate();
      con.createQuery(deletePatronQuery).executeUpdate();
      con.createQuery(deleteLogbookQuery).executeUpdate();
    }
  }
}
