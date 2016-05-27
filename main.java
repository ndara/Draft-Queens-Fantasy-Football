public class main {
	public static void main(String[] args) throws Exception {
      Connection conn = getConnection();
      Statement statement = null;
      ResultSet results = null;

      String query = "SELECT * FROM Teachers";

      try {
         // Get a statement from the connection
         statement = conn.createStatement();

         // Execute the query
         results = statement.executeQuery(query);

         while (results.next()) {
            String last = results.getString(1);
            String first = results.getString("first");
            int room = results.getInt(3);

            System.out.println(String.format("%s, %s -- %d", last, first, room));
         }
      } catch (SQLException sqlEx) {
         System.err.println("Error doing query: " + sqlEx);
         sqlEx.printStackTrace(System.err);
      } finally {
         try {
            if (results != null) {
               results.close();
               results = null;
            }

            if (statement != null) {
               statement.close();
               statement = null;
            }
         } catch (Exception ex) {
            System.err.println("Error closing query: " + ex);
            ex.printStackTrace(System.err);
         }

         close(conn);
      }
   }
}