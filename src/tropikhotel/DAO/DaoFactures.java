package tropikhotel.DAO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tropikhotel.Con;
import tropikhotel.GetSet.Factures;

public class DaoFactures
{
  Con con = new Con();
  String sql = "";
  
  public void add(int NumFacture, String DateFacture, int MontantFacture, int NumReglement, int NumClient)
    throws SQLException, ClassNotFoundException
  {
    Factures fact = new Factures();
    
    fact.setNumFacture(NumFacture);
    fact.setDateFacture(DateFacture);
    fact.setMontantFacture(MontantFacture);
    fact.setNumReglement(NumReglement);
    fact.setNumClient(NumClient);
    
    Connection connection = this.con.conn();
    this.sql = ("insert into Factures(NumFacture, DateFacture, MontantFacture, NumReglement, NumClient) values (" + fact.getNumFacture() + ",'" + fact.getDateFacture() + "'," + fact.getMontantFacture() + "," + fact.getNumReglement() + "," + fact.getNumClient() + ")");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.conn();
    this.sql = ("delete from FACTURE where NumFacture =" + id);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void mod(int i, int NumFacture, String DateFacture, int MontantFacture, int NumReglement, int NumClient)
    throws SQLException, ClassNotFoundException
  {
    Factures fact = find(i);
    fact.setNumFacture(NumFacture);
    fact.setDateFacture(DateFacture);
    fact.setMontantFacture(MontantFacture);
    fact.setNumReglement(NumReglement);
    fact.setNumClient(NumClient);
  }
  
  public Factures find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    Factures fact = null;
    this.sql = ("select * from FACTURE where NumFacture =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      fact = new Factures(resultset.getInt("NumFacture"), resultset.getString("DateFacture"), resultset.getInt("MontantFacture"), resultset.getInt("NumReglement"), resultset.getInt("NumClient"));
    }
    return fact;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Factures> fact = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from FACTURE";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      fact.add(new Factures(resultset.getInt("NumFacture"), resultset.getString("DateFacture"), resultset.getInt("MontantFacture"), resultset.getInt("NumReglement"), resultset.getInt("NumClient")));
    }
    return fact;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Factures> fact = new ArrayList();
    Connection connection = this.con.conn();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from FACTURE where NumFacture= " + other + " || DateFacture='" + id + "' || MontantFacture=" + other + " || NumReglement=" + other + " || NumClient=" + other + "");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      fact.add(new Factures(resultset.getInt("NumFacture"), resultset.getString("DateFacture"), resultset.getInt("MontantFacture"), resultset.getInt("NumReglement"), resultset.getInt("NumClient")));
    }
    return fact;
  }
}
