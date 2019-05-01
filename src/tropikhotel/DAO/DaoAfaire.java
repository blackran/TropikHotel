package tropikhotel.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tropikhotel.Con;
import tropikhotel.GetSet.Afaire;
import tropikhotel.GetSet.Types;

public class DaoAfaire {
  Con con = new Con();
  String sql = "";
  
  public void add(String DescriptionAfaire, String EtatAfaire) throws SQLException, ClassNotFoundException {
    Afaire af = new Afaire();
    

    af.setDescriptionAfaire(DescriptionAfaire);
    af.setEtatAfaire(EtatAfaire);
    
    Connection connection = this.con.conn();
    this.sql = ("insert into AFAIRE(DescriptionAfaire, EtatAfaire) values ('" + af.getDescriptionAfaire() + "','" + af.getEtatAfaire() + "')");
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
  
    public void mod(int NumAfaire, String DescriptionAfaire, String EtatAfaire) throws SQLException, ClassNotFoundException {
        Afaire af = new Afaire();
        af.setNumAfaire(NumAfaire);
        af.setDescriptionAfaire(DescriptionAfaire);
        af.setEtatAfaire(EtatAfaire);
        Connection connection = this.con.conn();
        this.sql = ("UPDATE AFAIRE SET DescriptionAfaire='" + af.getDescriptionAfaire() + "', EtatAfaire='" + af.getEtatAfaire() + "' where NumAfaire=" + af.getNumAfaire());
        Statement statement = connection.createStatement();
        statement.execute(this.sql);
    }
  
    public Afaire find(int i) throws ClassNotFoundException, SQLException {
        Connection connection = this.con.conn();
        Afaire typ = null;
        this.sql = ("select * from AFAIRE where NumAfaire =" + i);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          typ = new Afaire(resultset.getInt("NumAfaire"), resultset.getString("DescriptionAfaire"), resultset.getString("EtatAfaire"));
        }
        return typ;
    }
    
    public void RemoveEtatFinish() throws ClassNotFoundException, SQLException {
        Connection connection = this.con.conn();
        this.sql = ("delete from AFAIRE where EtatAfaire ='1'");
        Statement statement = connection.createStatement();
        statement.execute(this.sql);
    }
    
    public void modDescription(int id) throws SQLException, ClassNotFoundException{
        Connection connection = this.con.conn();
        Afaire e = this.find(id);
        String val = "0".equals(e.getEtatAfaire())?"1":"0";
        this.sql = ("UPDATE AFAIRE SET EtatAfaire='" + val + "' where NumAfaire ="+ id);
        Statement statement = connection.createStatement();
        statement.execute(this.sql);
    }
    
    public ArrayList findAllDesc(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Afaire> af = new ArrayList();
        Connection connection = this.con.conn();
        this.sql = ("select * from AFAIRE where DescriptionAfaire ='"+ id +"'");
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          af.add(new Afaire(resultset.getInt("NumAfaire"), resultset.getString("DescriptionAfaire"), resultset.getString("EtatAfaire")));
        }
        return af;
    }
    public ArrayList findAll() throws SQLException, ClassNotFoundException {
        ArrayList<Afaire> af = new ArrayList();
        Connection connection = this.con.conn();
        this.sql = "select * from AFAIRE";
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          af.add(new Afaire(resultset.getInt("NumAfaire"), resultset.getString("DescriptionAfaire"), resultset.getString("EtatAfaire")));
        }
        return af;
    }
  
    public ArrayList searchAll(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Types> af = new ArrayList();
        Connection connection = this.con.conn();
        String other = null;
        if (id.matches("[0-9]*")) {
          other = id;
        }
        this.sql = ("select * from AFAIRE where NumAfaire= " + other + " || DescriptionAfaire='" + id + "' || EtatAfaire='" + id + "'");
        System.out.println(this.sql);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          af.add(new Types(resultset.getInt("NumAfaire"), resultset.getString("DescriptionAfaire"), resultset.getString("EtatAfaire")));
        }
        return af;
    }
}
