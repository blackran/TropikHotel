package tropikhotel.DAO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tropikhotel.Con;
import tropikhotel.GetSet.Concerner;

public class DaoConcerner
{
  Con con = new Con();
  String sql = "";
  
  public void add(int NumReservation, String NomChambre)
    throws SQLException, ClassNotFoundException
  {
    Concerner Con = new Concerner();
    Con.setNumReservation(NumReservation);
    Con.setNomChambre(NomChambre);
    
    Connection connection = this.con.conn();
    this.sql = ("insert into CONCERNER(NumReservation, NomChambre) values (" + Con.getNumReservation() + ",'" + Con.getNomChambre() + "')");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id, String nom)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.conn();
    this.sql = ("delete from CONCERNER where NumReservation =" + id + " and NomChambre='" + nom + "'");
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void mod(int i, String j, int NumReservation, String NomChambre)
    throws SQLException, ClassNotFoundException
  {
    Concerner Con = (Concerner)find(i, j).get(0);
    Con.setNumReservation(NumReservation);
    Con.setNomChambre(NomChambre);
  }
  
  public ArrayList find(int i, String j)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    ArrayList<Concerner> Con = new ArrayList();
    this.sql = ("select * from CONCERNER where NumReservation =" + i + " && NomChambre='" + j + "'");
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      Con.add(new Concerner(resultset.getInt("NumReservation"), resultset.getString("NomChambre")));
    }
    return Con;
  }
  public ArrayList find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    ArrayList<Concerner> Con = new ArrayList();
    this.sql = ("select * from CONCERNER where NumReservation =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      Con.add(new Concerner(resultset.getInt("NumReservation"), resultset.getString("NomChambre")));
    }
    return Con;
  }
  
  public ArrayList findNomChambre(int i) throws ClassNotFoundException, SQLException{
    ArrayList<Concerner> Con = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = ("select * from CONCERNER where NumReservation =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      Con.add(new Concerner(resultset.getInt("NumReservation"), resultset.getString("NomChambre")));
    }
    return Con;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Concerner> Con = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from CONCERNER";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      Con.add(new Concerner(resultset.getInt("NumReservation"), resultset.getString("NomChambre")));
    }
    return Con;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Concerner> Con = new ArrayList();
    Connection connection = this.con.conn();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from Concerner where NumReservation= " + other + " || NomChambre='" + id + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      Con.add(new Concerner(resultset.getInt("NumReservation"), resultset.getString("NomChambre")));
    }
    return Con;
  }
}
