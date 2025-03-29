package cce;

import java.io.IOException;

import java.util.Date;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Cce {
    public Cce() {
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cce?zeroDateTimeBehavior=CONVERT_TO_NULL";

        Connection connection = DriverManager.getConnection(url, "cce", "cce");
        System.out.println("Connessione OK \n");

        return connection;
    }

    private void releaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
    }

    public void testConnection() {
        try {
            Connection connection = getConnection();
            releaseConnection(connection);
        } catch (Exception e) {
            System.err.println("Connessione Fallita \n");
            System.err.println(e);
        }
    }

    public void release() {
        try {
            DBConnectionPool.releaseAllConnection();
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        }
    }

    public void basicRetrieve() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();
            st = con.createStatement();

            String sql = "SELECT * FROM account";
            System.out.println("QUERY: " + sql);

            rs = st.executeQuery(sql);
            while (rs.next()) {
                int idAccount = rs.getInt("idAccount");
                String numero_telefono = rs.getString("numero_telefono");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String luogo_nascita = rs.getString("luogo_nascita");
                String email = rs.getString("email");
                Date data_nascita = rs.getDate("data_nascita");

                System.out.println(
                        "idAccount: " + idAccount +
                                ", email: " + email +
                                ", username: " + username +
                                ", password: " + password +
                                ", nome: " + nome +
                                ", cognome: " + cognome +
                                ", data_nascita: " + data_nascita +
                                ", luogo di nascita: " + luogo_nascita +
                                ", numero telefono: " + numero_telefono
                );
            }
        } catch (SQLException s){
            System.err.println("SQLException:> " + s.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println("SQLException:> " + s.getMessage());
            }
        }
    }

    public void retrive() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();
            st = con.createStatement();

            String sql = "SELECT * FROM account";
            System.out.println("QUERY: " + sql);

            rs = st.executeQuery(sql);

            Account.printTitle();
            while (rs.next()) {
                Account account = new Account();
                account.setIdAccount(rs.getInt("idAccount"));
                account.setEmail(rs.getString("email"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setNome(rs.getString("nome"));
                account.setCognome(rs.getString("cognome"));
                account.setData_nascita(rs.getDate("data_nascita"));
                account.setLuogo_nascita(rs.getString("luogo_nascita"));
                account.setNumero_telefono( rs.getString("numero_telefono"));
                account.print();
            }
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void delete() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            st = con.createStatement();

            String sql = "DELETE FROM account WHERE idAccount = 3";
            System.out.println("QUERY: " + sql);

            int result = st.executeUpdate(sql);
            if (result > 0) {
                System.out.println("Cancellazione effettuata");
            } else {
                System.out.println("Impossibile cancellare il/i record");
            }
            con.commit();
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void delete(String param_id) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            st = con.createStatement();

            String sql = "DELETE FROM account WHERE idAccount ='" + param_id + "'";
            System.out.println("QUERY: " + sql);

            int result = st.executeUpdate(sql);
            if (result > 0) {
                System.out.println("Cancellazione effettuata");
            } else {
                System.out.println("Impossibile cancellare il/i record");
            }
            con.commit();
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void create() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            st = con.createStatement();
            String sql = "CREATE TABLE `account_1` ("
                    + "`idAccount` INT(11) NOT NULL AUTO_INCREMENT, "
                    + "`email` VARCHAR(50) NOT NULL UNIQUE, "
                    + "`username` VARCHAR(20) NOT NULL UNIQUE, "
                    + "`nome` VARCHAR(30) NOT NULL, "
                    + "`cognome` VARCHAR(30) NOT NULL, "
                    + "`data_nascita` DATE NOT NULL, "
                    + "`password` VARCHAR(255) NOT NULL, "
                    + "`numero_telefono` CHAR(10) NOT NULL, "
                    + "`luogo_nascita` VARCHAR(30) NOT NULL, "
                    + "PRIMARY KEY (`idAccount`)"
                    + ");";

            System.out.println("QUERY: " + sql);

            int result = st.executeUpdate(sql);

            if (result == 0) {
                System.out.println("Creazione effettuata");
            } else {
                System.out.println("Impossibile eseguire la creazione");
            }
            con.commit();
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void drop() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            st = con.createStatement();

            String sql = "DROP TABLE account_1";
            System.out.println("QUERY: " + sql);

            int result = st.executeUpdate(sql);

            if (result == 0) {
                System.out.println("Cancellazione effettuata");
            } else {
                System.out.println("Impossibile eseguire la cancellazione");
            }
            con.commit();
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void insert() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            st = con.createStatement();

            String sql = "INSERT INTO account VALUES (4, 'gennaro.tozza@example.com', 'gennaro.tozza', 'Gennaro', 'Tozza', '2004-04-25', SHA1('password4'), 3928011093, 'Battipaglia')";
            System.out.println("QUERY: " + sql);

            int result = st.executeUpdate(sql);

            if (result > 0) {
                System.out.println("Inserimento effettuato");
            } else {
                System.out.println("Impossibile inserire il/i record");
            }
            con.commit();
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void update() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            st = con.createStatement();

            String sql = "UPDATE account SET email = 'gennarocarmine@example.com' WHERE email = 'gennaro.tozza@example.com'";
            System.out.println("QUERY: " + sql);

            int result = st.executeUpdate(sql);

            if (result > 0) {
                System.out.println("Update effettuato");

            } else {
                System.out.println("Impossibile effettuare l'update");
            }
            con.commit();
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void parametricRetrieve(String param_email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            String sql = "SELECT * FROM account WHERE email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, param_email);
            System.out.println("QUERY: " + ps);

            rs = ps.executeQuery();

            Account.printTitle();
            while (rs.next()) {
                Account account = new Account();
                account.setIdAccount(rs.getInt("idAccount"));
                account.setEmail(rs.getString("email"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setNome(rs.getString("nome"));
                account.setCognome(rs.getString("cognome"));
                account.setData_nascita(rs.getDate("data_nascita"));
                account.setLuogo_nascita(rs.getString("luogo_nascita"));
                account.setNumero_telefono( rs.getString("numero_telefono"));
                account.print();
            }
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void parametricRetrieveCondition(String param_email, String param_numtel) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            String sql = "SELECT * FROM account WHERE email = ? AND  numero_telefono = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, param_email);
            ps.setString(2, param_numtel);
            System.out.println("QUERY: " + ps);

            rs = ps.executeQuery();

            Account.printTitle();
            while (rs.next()) {
                Account account = new Account();
                account.setIdAccount(rs.getInt("idAccount"));
                account.setEmail(rs.getString("email"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setNome(rs.getString("nome"));
                account.setCognome(rs.getString("cognome"));
                account.setData_nascita(rs.getDate("data_nascita"));
                account.setLuogo_nascita(rs.getString("luogo_nascita"));
                account.setNumero_telefono( rs.getString("numero_telefono"));
                account.print();
            }
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void parametricInsert(Account account) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();
            String sql = "INSERT INTO account VALUES (?,?,?,?,?,?,MD5(?),?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, account.getIdAccount());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getUsername());
            ps.setString(4,account.getNome());
            ps.setString(5,account.getCognome());
            ps.setString(7,account.getPassword());
            ps.setString(8,account.getNumero_telefono());
            ps.setString(9,account.getLuogo_nascita());
            if (account.getData_nascita() != null) {
                ps.setDate(6, Utility.toSqlDate(account.getData_nascita()));
            } else {
                ps.setObject(6, null);
            }

            System.out.println("QUERY: " + ps);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Inserimento effettuato");
            } else {
                System.out.println("Impossibile inserire il/i record");
            }
            con.commit();
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }

    }

    public void printTable(String tableName) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            st = con.createStatement();

            String sql = "SELECT * FROM " + tableName;
            System.out.println("QUERY: " + sql);

            rs = st.executeQuery(sql);


            ResultSetMetaData md = rs.getMetaData();

            // print the column labels
            for (int i = 1; i <= md.getColumnCount(); i++)
                System.out.print(md.getColumnLabel(i) + " ");
            System.out.println();

            // loop through the result set
            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void printFormattedTable(String tableName) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            st = con.createStatement();

            String sql = "SELECT * FROM " + tableName;
            System.out.println("QUERY: " + sql);

            rs = st.executeQuery(sql);

            PrintTable.printResultSet(rs);
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    public void describeTable(String table) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();

            st = con.createStatement();

            String sql = "SELECT * FROM " + table;
            System.out.println("QUERY: " + sql);

            rs = st.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();

            int columns = rsmd.getColumnCount();
            String tableName = rsmd.getTableName(1);
            System.out.println("Table: " + tableName);

            for (int i = 1; i <= columns; i++) {
                String colLabel = rsmd.getColumnLabel(i);
                String colName = rsmd.getColumnName(i);
                int jdbcType = rsmd.getColumnType(i);
                String type = rsmd.getColumnTypeName(i);
                System.out.print(colName + " " + colLabel + " of type " + type + " (" + jdbcType + ")\n");
            }

        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }

    }

    public void list() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();
            st = con.createStatement();

            String sql = "SHOW TABLES";
            System.out.println("QUERY: " + sql);

            rs = st.executeQuery(sql);

            PrintTable.printResultSet(rs);
        } catch (SQLException s) {
            System.err.println(s.getMessage());
            Utility.printSQLException(s);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (st != null)
                    st.close();
                DBConnectionPool.releaseConnection(con);
            } catch (SQLException s) {
                System.err.println(s.getMessage());
                Utility.printSQLException(s);
            }
        }
    }

    private void ui() throws IOException {
        int scelta;
        Scanner in = new Scanner(System.in);

        do{
            System.out.println("\nOperazioni CRUD:");
            System.out.println("*****************************");
            System.out.println("[0] - visualizza account basic");
            System.out.println("[1] - visualizza account");
            System.out.println("[2] - visualizza account data l'email");
            System.out.println("[3] - visualizza account data l'email e il numero di telefono");
            System.out.println("[4] - cancella account");
            System.out.println("[5] - cancella account dato l'id");
            System.out.println("[6] - crea tabella");
            System.out.println("[7] - cancella tabella");
            System.out.println("[8] - inserimento account");
            System.out.println("[9] - inserimento account con dati");
            System.out.println("[10] - aggiornamento account");
            System.out.println("[11] - visualizza tabella dato il nome");
            System.out.println("[12] - visualizza tabella formattata dato il nome");
            System.out.println("[13] - descrivi tabella");
            System.out.println("[14] - elenca tabelle");
            System.out.println("[15] - per uscire");
            System.out.println("*****************************");

            System.out.print("Inserisci scelta: ");
            scelta = in.nextInt();

            switch (scelta){
                case 0 : {
                    this.basicRetrieve();
                    break;
                }
                case 1 : {
                    retrive();
                    break;
                }
                case 2 : {
                    String param1 = Utility.readLine("Email",null,true);
                    this.parametricRetrieve(param1);
                    break;
                }
                case 3 : {
                    String param1 = Utility.readLine("Email",null,true);
                    String param2 = Utility.readLine("Numero telefono","numero intero",true);
                    this.parametricRetrieveCondition(param1,param2);
                    break;
                }
                case 4 : {
                    System.out.println("Cancella account");
                    this.delete();
                    break;
                }
                case 5 : {
                    String param1 = Utility.readLine("idAccount",null,true);
                    this.delete(param1);
                    break;
                }
                case 6: {
                    System.out.println("Crea tabella");
                    this.create();
                    break;
                }
                case 7: {
                    System.out.println("Cancella tabella");
                    this.drop();
                    break;
                }
                case 8: {
                    System.out.println("Inserimento account");
                    this.insert();
                    break;
                }
                case 9 : {
                    String param1 = Utility.readLine("Inserisci id", "int(11)", true);
                    String param2 = Utility.readLine("Inserisci email", "varchar(50)", true);
                    String param3 = Utility.readLine("Inserisci username", "varchar(20)", true);
                    String param4 = Utility.readLine("Inserisci nome", "varchar(30)", true);
                    String param5 = Utility.readLine("Inserisci cognome", "varchar(30)", true);
                    String param6 = Utility.readLine("Inserisci data di nascita", "yyyy-mm-dd", false);
                    String param7 = Utility.readLine("Inserisci password", "varchar(255)", true);
                    String param8 = Utility.readLine("Inserisci numero di telefono", "int(10)", false);
                    String param9 = Utility.readLine("Inserisci luogo di nascita", "varchar(30)", true);
                    Account account = new Account();
                    account.setIdAccount(Integer.parseInt(param1));
                    account.setEmail(param2);
                    account.setUsername(param3);
                    account.setNome(param4);
                    account.setCognome(param5);
                    account.setPassword(param7);
                    account.setNumero_telefono(param8);
                    account.setLuogo_nascita(param9);

                    account.setData_nascita(Utility.formatStringToDate(param6));
                    this.parametricInsert(account);
                    break;
                }
                case 10: {
                    this.update();
                    break;
                }
                case 11: {
                    String param1 = Utility.readLine("Nome tabella", null, true);
                    this.printTable(param1);
                    break;
                }
                case 12: {
                    String param1 = Utility.readLine("Nome tabella", null, true);
                    this.printFormattedTable(param1);
                    break;
                }
                case 13: {
                    System.out.println("Descrivi tabella");
                    String param1 = Utility.readLine("Nome tabella", null, true);
                    this.describeTable(param1);
                    break;
                }
                case 14: {
                    this.list();
                    break;
                }
            }
        } while (scelta != 15);
    }

    public static void main(String[] args) {
        Cce db = new Cce();
        System.out.println("*** Cce DB ***");
        db.testConnection();

        try {
            db.ui();
        } catch (IOException e) {

        }

        db.release();
    }
}