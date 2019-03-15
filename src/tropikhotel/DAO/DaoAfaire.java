package tropikhotel.DAO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tropikhotel.Con;
import tropikhotel.GetSet.Afaire;
import tropikhotel.GetSet.Types;

public class DaoAfaire
{
  Con con = new Con();
  String sql = "";
  
  public void add(int NumAfaire, String DescriptionAfaire, String HeureAfaire)
    throws SQLException, ClassNotFoundException
  {
    Afaire af = new Afaire();
    
    af.setNumAfaire(NumAfaire);
    af.setDescriptionAfaire(DescriptionAfaire);
    af.setHeureAfaire(HeureAfaire);
    
    Connection connection = this.con.conn();
    this.sql = ("insert into AFAIRE(NumAfaire, DescriptionAfaire, HeureAfaire) values (" + af.getNumAfaire() + ",'" + af.getDescriptionAfaire() + "','" + af.getHeureAfaire() + "')");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.conn();
    this.sql = ("delete from AFAIRE where NumAfaire =" + id);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void mod(int NumAfaire, String DescriptionAfaire, String HeureAfaire)
    throws SQLException, ClassNotFoundException
  {
    Afaire af = new Afaire();
    af.setNumAfaire(NumAfaire);
    af.setDescriptionAfaire(DescriptionAfaire);
    af.setHeureAfaire(HeureAfaire);
    Connection connection = this.con.conn();
    this.sql = ("UPDATE AFAIRE SET DescriptionAfaire='" + af.getDescriptionAfaire() + "', HeureAfaire='" + af.getHeureAfaire() + "' where NumAfaire=" + af.getNumAfaire());
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public Types find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    Types typ = null;
    this.sql = ("select * from AFAIRE where NumAfaire =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      typ = new Types(resultset.getInt("NumAfaire"), resultset.getString("DescriptionAfaire"), resultset.getString("HeureAfaire"));
    }
    return typ;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Types> af = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from AFAIRE";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      af.add(new Types(resultset.getInt("NumAfaire"), resultset.getString("DescriptionAfaire"), resultset.getString("HeureAfaire")));
    }
    return af;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Types> af = new ArrayList();
    Connection connection = this.con.conn();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from AFAIRE where NumAfaire= " + other + " || DescriptionAfaire='" + id + "' || HeureAfaire='" + id + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      af.add(new Types(resultset.getInt("NumAfaire"), resultset.getString("DescriptionAfaire"), resultset.getString("HeureAfaire")));
    }
    return af;
  }
}
