package tropikhotel.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tropikhotel.Con;
import tropikhotel.GetSet.Ajouter;

public class DaoAjouter {
    Con con = new Con();
    String sql = "";

    public void add(int NumRepas, int NumCommander, int QtAjouter) throws SQLException, ClassNotFoundException {
        Ajouter Ajo = new Ajouter();
        Ajo.setNumRepas(NumRepas);
        Ajo.setNumCommander(NumCommander);
        Ajo.setQtAjouter(QtAjouter);

        Connection connection = this.con.conn();
        this.sql = ("insert into AJOUTER(NumRepas, NumCommander, QtAjouter) values (" + Ajo.getNumRepas() + "," + Ajo.getNumCommander() + "," + Ajo.getQtAjouter() +")");
        Statement statement = connection.createStatement();
        statement.execute(this.sql);
    }

    public void remove(int id, int num) throws SQLException, ClassNotFoundException {
      Connection connection = this.con.conn();
      this.sql = ("delete from AJOUTER where NumRepas =" + id + " and NumCommander=" + num);
      Statement statement = connection.createStatement();
      statement.execute(this.sql);
    }

    public void mod(int NumRepas, int NumCommander, int QtAjouter) throws SQLException, ClassNotFoundException {
        Ajouter Ajo = new Ajouter();
        Ajo.setNumRepas(NumRepas);
        Ajo.setNumCommander(NumCommander);
        Ajo.setQtAjouter(QtAjouter);
        Connection connection = this.con.conn();
        this.sql = ("update AJOUTER set QtAjouter =" + QtAjouter + " where NumRepas =" + NumRepas + " and NumCommander=" + NumCommander);
        Statement statement = connection.createStatement();
        statement.execute(this.sql);
    }

    public ArrayList find(int i, int j) throws ClassNotFoundException, SQLException {
        Connection connection = this.con.conn();
        ArrayList<Ajouter> ajou = new ArrayList();
        this.sql = ("select * from AJOUTER where NumRepas =" + i + " && NumCommander=" + j );
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          ajou.add(new Ajouter(resultset.getInt("NumRepas"), resultset.getInt("NumCommander"), resultset.getInt("QtAjouter")));
        }
        return ajou;
    }
    public ArrayList find(int i) throws ClassNotFoundException, SQLException {
        Connection connection = this.con.conn();
        ArrayList<Ajouter> Con = new ArrayList();
        this.sql = ("select * from AJOUTER where NumRepas =" + i);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          Con.add(new Ajouter(resultset.getInt("NumRepas"), resultset.getInt("NumCommander"), resultset.getInt("QtAjouter")));
        }
        return Con;
    }

    public ArrayList findNumRepas(int i) throws ClassNotFoundException, SQLException{
        ArrayList<Ajouter> Con = new ArrayList();
        Connection connection = this.con.conn();
        this.sql = ("select * from AJOUTER where NumCommander =" + i);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          Con.add(new Ajouter(resultset.getInt("NumRepas"), resultset.getInt("NumCommander"), resultset.getInt("QtAjouter")));
        }
        return Con;
    }

    public ArrayList findAll() throws SQLException, ClassNotFoundException {
      ArrayList<Ajouter> Con = new ArrayList();
      Connection connection = this.con.conn();
      this.sql = "select * from AJOUTER";
      Statement statement = connection.createStatement();
      ResultSet resultset = statement.executeQuery(this.sql);
      while (resultset.next()) {
        Con.add(new Ajouter(resultset.getInt("NumRepas"), resultset.getInt("NumRepas"), resultset.getInt("QtAjouter")));
      }
      return Con;
    }

    public ArrayList searchAll(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Ajouter> Con = new ArrayList();
        Connection connection = this.con.conn();
        String other = null;
        if (id.matches("[0-9]*")) {
            other = id;
        }
        this.sql = ("select * from AJOUTER where NumRepas= " + other + " || NumRepas=" + other);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          Con.add(new Ajouter(resultset.getInt("NumRepas"), resultset.getInt("NumRepas"), resultset.getInt("QtAjouter")));
        }
        return Con;
    }
}
