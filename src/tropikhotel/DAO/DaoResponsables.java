package tropikhotel.DAO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tropikhotel.Con;
import tropikhotel.GetSet.Responsables;

public class DaoResponsables
{
  Con con = new Con();
  String sql = "";
  
  public void add(int NumResponsable, String NomResponsable, String PseudoResponsable, String PasswordResponsable, String PrenomResponsable, String AddressResponsable, String TelResponsable, String DroitResponsable, String ImageUrlResponsable)
    throws SQLException, ClassNotFoundException
  {
    Responsables resp = new Responsables();
    resp.setNumResponsable(NumResponsable);
    resp.setNomResponsable(NomResponsable);
    resp.setPseudoResponsable(PseudoResponsable);
    resp.setPasswordResponsable(PasswordResponsable);
    resp.setPrenomResponsable(PrenomResponsable);
    resp.setAddressResponsable(AddressResponsable);
    resp.setTelResponsable(TelResponsable);
    resp.setDroitResponsable(DroitResponsable);
    resp.setImageUrlResponsable(ImageUrlResponsable);
    
    Connection connection = this.con.conn();
    this.sql = ("insert into RESPONSABLES(NomResponsable, PseudoResponsable, PasswordResponsable, PrenomResponsable, AddressResponsable, TelResponsable, DroitResponsable, ImageUrlResponsable) values ('" + resp.getNomResponsable() + "','" + resp.getPseudoResponsable() + "','" + resp.getPasswordResponsable() + "','" + resp.getPrenomResponsable() + "','" + resp.getAddressResponsable() + "','" + resp.getTelResponsable() + "','" + resp.getDroitResponsable() + "','" + resp.getImageUrlResponsable() + "')");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.conn();
    this.sql = ("delete from RESPONSABLES where NumResponsable =" + id);
    Statement statement = connection.createStatement();
    System.out.println(this.sql);
    statement.execute(this.sql);
  }
  
  public void mod(int NumResponsable, String NomResponsable, String PseudoResponsable, String PasswordResponsable, String PrenomResponsable, String AddressResponsable, String TelResponsable, String DroitResponsable, String ImageUrlResponsable)
    throws SQLException, ClassNotFoundException
  {
    Responsables resp = find(NumResponsable);
    resp.setNumResponsable(NumResponsable);
    resp.setNomResponsable(NomResponsable);
    resp.setPseudoResponsable(PseudoResponsable);
    resp.setPasswordResponsable(PasswordResponsable);
    resp.setPrenomResponsable(PrenomResponsable);
    resp.setAddressResponsable(AddressResponsable);
    resp.setTelResponsable(TelResponsable);
    resp.setDroitResponsable(DroitResponsable);
    resp.setImageUrlResponsable(ImageUrlResponsable);
    Connection connection = this.con.conn();
    this.sql = ("update RESPONSABLES set NomResponsable='" + resp.getNomResponsable() + "', PseudoResponsable='" + resp.getPseudoResponsable() + "', PasswordResponsable='" + resp.getPasswordResponsable() + "', PrenomResponsable='" + resp.getPrenomResponsable() + "', AddressResponsable='" + resp.getAddressResponsable() + "', TelResponsable='" + resp.getTelResponsable() + "', DroitResponsable='" + resp.getDroitResponsable() + "', ImageUrlResponsable='" + resp.getImageUrlResponsable() + "' where NumResponsable='" + resp.getNumResponsable() + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public Responsables find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    Responsables resp = null;
    this.sql = ("select * from RESPONSABLES where NumResponsable =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      resp = new Responsables(resultset.getInt("NumResponsable"), resultset.getString("NomResponsable"), resultset.getString("PseudoResponsable"), resultset.getString("PasswordResponsable"), resultset.getString("PrenomResponsable"), resultset.getString("AddressResponsable"), resultset.getString("TelResponsable"), resultset.getString("DroitResponsable"), resultset.getString("ImageUrlResponsable"));
    }
    return resp;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Responsables> resp = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from RESPONSABLES";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      resp.add(new Responsables(resultset.getInt("NumResponsable"), resultset.getString("NomResponsable"), resultset.getString("PseudoResponsable"), resultset.getString("PasswordResponsable"), resultset.getString("PrenomResponsable"), resultset.getString("AddressResponsable"), resultset.getString("TelResponsable"), resultset.getString("DroitResponsable"), resultset.getString("ImageUrlResponsable")));
    }
    return resp;
  }
  
  public ArrayList findAllSuper()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Responsables> resp = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from RESPONSABLES where DroitResponsable='SUPERS'";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      resp.add(new Responsables(resultset.getInt("NumResponsable"), resultset.getString("NomResponsable"), resultset.getString("PseudoResponsable"), resultset.getString("PasswordResponsable"), resultset.getString("PrenomResponsable"), resultset.getString("AddressResponsable"), resultset.getString("TelResponsable"), resultset.getString("DroitResponsable"), resultset.getString("ImageUrlResponsable")));
    }
    return resp;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Responsables> resp = new ArrayList();
    Connection connection = this.con.conn();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from RESPONSABLES where NumResponsable= " + other + " || NomResponsable='" + id + "' || PseudoResponsable='" + id + "' || PasswordResponsable='" + id + "' || PrenomResponsable='" + id + "' || AddressResponsable='" + id + "' || TelResponsable='" + id + "' || DroitResponsable='" + id + "' || ImageUrlResponsable='" + id + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      resp.add(new Responsables(resultset.getInt("NumResponsable"), resultset.getString("NomResponsable"), resultset.getString("PseudoResponsable"), resultset.getString("PasswordResponsable"), resultset.getString("PrenomResponsable"), resultset.getString("AddressResponsable"), resultset.getString("TelResponsable"), resultset.getString("DroitResponsable"), resultset.getString("ImageUrlResponsable")));
    }
    return resp;
  }
}
