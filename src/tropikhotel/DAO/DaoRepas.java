package tropikhotel.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tropikhotel.Con;
import tropikhotel.GetSet.Repas;
import tropikhotel.GetSet.RepasT;

public class DaoRepas
{
  Con con = new Con();
  String sql = "";
  
  public void add(String NomRepas, int PrixRepas, String Heure)
    throws SQLException, ClassNotFoundException
  {
    Repas rep = new Repas();
    
    rep.setNomRepas(NomRepas);
    rep.setPrixRepas(PrixRepas);
    rep.setHeureRepas(Heure);
    
    Connection connection = this.con.conn();
    this.sql = ("insert into Repas(NomRepas, PrixRepas, Heure) values ('" + rep.getNomRepas() + "'," + rep.getPrixRepas() + ",'" + rep.getHeureRepas() + "')");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.conn();
    this.sql = ("delete from REPAS where NumRepas =" + id);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void mod(int i, String NomRepas, int PrixRepas, String HeureRepas)
    throws SQLException, ClassNotFoundException
  {
    Repas rep = find(i);
    rep.setNomRepas(NomRepas);
    rep.setPrixRepas(PrixRepas);
    rep.setHeureRepas(HeureRepas);
  }
  
  public Repas find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    Repas rep = null;
    this.sql = ("select * from REPAS where NumRepas =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      rep = new Repas(resultset.getInt("NumRepas"), resultset.getString("NomRepas"), resultset.getInt("PrixRepas"), resultset.getString("Heure"));
    }
    return rep;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Repas> rep = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from REPAS";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      rep.add(new Repas(resultset.getInt("NumRepas"), resultset.getString("NomRepas"), resultset.getInt("PrixRepas"), resultset.getString("Heure")));
    }
    return rep;
  }
  
    public ArrayList searchAll(String id) throws SQLException, ClassNotFoundException{
        ArrayList<Repas> rep = new ArrayList();
        Connection connection = this.con.conn();
        String other = null;
        if (id.matches("[0-9]*")) {
          other = id;
        }
        this.sql = ("select * from Repas where NumRepas= " + other + " || NomRepas='" + id + "' || PrixRepas=" + other + "|| Heure='" + id + "'");
        System.out.println(this.sql);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          rep.add(new Repas(resultset.getInt("NumRepas"), resultset.getString("NomRepas"), resultset.getInt("PrixRepas"), resultset.getString("Heure")));
        }
        return rep;
    }
    public ObservableList<RepasT> searchOneT(String id) throws ClassNotFoundException, SQLException {
        ObservableList<RepasT> rep = FXCollections.observableArrayList();
        Connection connection = this.con.conn();
        this.sql = ("select * from Repas where NumRepas LIKE  '%" + id + "%' || NomRepas LIKE  '%" + id + "%' || PrixRepas LIKE  '%" + id + "%' || Heure LIKE  '%" + id + "%' ");

        System.out.println(this.sql);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          rep.add(new RepasT(String.valueOf(resultset.getInt("NumRepas")), resultset.getString("NomRepas"), String.valueOf(resultset.getInt("PrixRepas")), resultset.getString("Heure")));
        }
        return rep;
    }
}
