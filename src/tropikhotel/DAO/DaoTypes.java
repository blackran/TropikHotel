package tropikhotel.DAO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tropikhotel.Con;
import tropikhotel.GetSet.Types;

public class DaoTypes
{
  Con con = new Con();
  String sql = "";
  
  public void add(int NumType, String NomType, String DescriptionType)
    throws SQLException, ClassNotFoundException
  {
    Types typ = new Types();
    
    typ.setNumType(NumType);
    typ.setNomType(NomType);
    typ.setDescriptionType(DescriptionType);
    
    Connection connection = this.con.conn();
    this.sql = ("insert into TYPES(NumType, NomType, DescriptionType) values (" + typ.getNumType() + ",'" + typ.getNomType() + "','" + typ.getDescriptionType() + "')");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.conn();
    this.sql = ("delete from TYPES where NumType =" + id);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void mod(int NumType, String NomType, String DescriptionType)
    throws SQLException, ClassNotFoundException
  {
    Types typ = find(NumType);
    typ.setNumType(NumType);
    typ.setNomType(NomType);
    typ.setDescriptionType(DescriptionType);
    Connection connection = this.con.conn();
    this.sql = ("UPDATE TYPES SET NomType='" + typ.getNomType() + "',DescriptionType='" + typ.getDescriptionType() + "' where NumType=" + typ.getNumType());
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public Types find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    Types typ = null;
    this.sql = ("select * from TYPES where NumType =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      typ = new Types(resultset.getInt("NumType"), resultset.getString("NomType"), resultset.getString("DescriptionType"));
    }
    return typ;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Types> typ = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from TYPES";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      typ.add(new Types(resultset.getInt("NumType"), resultset.getString("NomType"), resultset.getString("DescriptionType")));
    }
    return typ;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Types> typ = new ArrayList();
    Connection connection = this.con.conn();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from TYPES where NumType= " + other + " || NomType='" + id + "' || DescriptionType='" + id + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      typ.add(new Types(resultset.getInt("NumType"), resultset.getString("NomType"), resultset.getString("DescriptionType")));
    }
    return typ;
  }
}
