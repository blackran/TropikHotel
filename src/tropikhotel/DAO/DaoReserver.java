package tropikhotel.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import tropikhotel.Con;
import tropikhotel.GetSet.Reserver;

public class DaoReserver
{
  Con con = new Con();
  String sql = "";
  
  public void add(String DateDebutReservation, String DateFinReservation, int NbJourReservation, String EtatReservation, int NumClient, int NumResponsable, int NumReglement)
    throws SQLException, ClassNotFoundException
  {
    Reserver rese = new Reserver();
    
    rese.setDateDebutReservation(DateDebutReservation);
    rese.setDateFinReservation(DateFinReservation);
    rese.setNbJourReservation(NbJourReservation);
    rese.setEtatReservation(EtatReservation);
    rese.setNumClient(NumClient);
    rese.setNumResponsable(NumResponsable);
    rese.setNumReglement(NumReglement);
    LocalDate now = LocalDate.now();
    Connection connection = this.con.conn();
    LocalDate begin = LocalDate.parse(rese.getDateDebutReservation());
    if (ChronoUnit.DAYS.between(now, begin) < 0L)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText("REMARQUE");
      alert.setContentText("date debut error");
      alert.show();
    }
    else
    {
      this.sql = ("insert into RESERVER(DateDebutReservation, DateFinReservation, NbJourReservation, EtatReservation, NumClient, NumResponsable, NumReglement) values ('" + rese.getDateDebutReservation() + "','" + rese.getDateFinReservation() + "'," + rese.getNbJourReservation() + ",'" + rese.getEtatReservation() + "'," + rese.getNumClient() + "," + rese.getNumResponsable() + "," + rese.getNumReglement() + ")");
    }
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.conn();
    this.sql = ("delete from RESERVER where NumReservation =" + id);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void mod(int i, String DateDebutReservation, String DateFinReservation, int NbJourReservation, String EtatReservation, int NumClient, int NumResponsable, int NumReglement)
    throws SQLException, ClassNotFoundException
  {
    Reserver rese = find(i);
    rese.setDateDebutReservation(DateDebutReservation);
    rese.setDateFinReservation(DateFinReservation);
    rese.setNbJourReservation(NbJourReservation);
    rese.setEtatReservation(EtatReservation);
    rese.setNumClient(NumClient);
    rese.setNumResponsable(NumResponsable);
    rese.setNumReglement(NumReglement);
    Connection connection = this.con.conn();
    this.sql = ("UPDATE RESERVER SET DateDebutReservation='" + rese.getDateDebutReservation() + "', DateFinReservation='" + rese.getDateFinReservation() + "', NbJourReservation=" + rese.getNbJourReservation() + ", EtatReservation='" + rese.getEtatReservation() + "', NumClient=" + rese.getNumClient() + ", NumResponsable=" + rese.getNumResponsable() + ", NumReglement=" + rese.getNumReglement() + " where NumReservation=" + rese.getNumReservation());
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public Reserver find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    Reserver rese = null;
    this.sql = ("select * from RESERVER where NumReservation =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    System.out.println(this.sql);
    while (resultset.next()) {
      rese = new Reserver(resultset.getInt("NumReservation"), resultset.getString("DateDebutReservation"), resultset.getString("DateFinReservation"), resultset.getInt("NbJourReservation"), resultset.getString("EtatReservation"), resultset.getInt("NumClient"), resultset.getInt("NumResponsable"), resultset.getInt("NumReglement"));
    }
    return rese;
  }
  
  public ArrayList findReglement(int i)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Reserver> rese = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = ("select * from RESERVER where NumReglement =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      rese.add(new Reserver(resultset.getInt("NumReservation"), resultset.getString("DateDebutReservation"), resultset.getString("DateFinReservation"), resultset.getInt("NbJourReservation"), resultset.getString("EtatReservation"), resultset.getInt("NumClient"), resultset.getInt("NumResponsable"), resultset.getInt("NumReglement")));
    }
    return rese;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Reserver> rese = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from RESERVER";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      rese.add(new Reserver(resultset.getInt("NumReservation"), resultset.getString("DateDebutReservation"), resultset.getString("DateFinReservation"), resultset.getInt("NbJourReservation"), resultset.getString("EtatReservation"), resultset.getInt("NumClient"), resultset.getInt("NumResponsable"), resultset.getInt("NumReglement")));
    }
    return rese;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Reserver> rese = new ArrayList();
    Connection connection = this.con.conn();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from Reserver where NumReservation= " + other + " || DateDebutReservation='" + id + "' || DateFinReservation='" + id + "'  || NbJourReservation=" + other + " || EtatReservation='" + id + "' || NumClient=" + other + " || NumResponsable=" + other + " || NumReglement=" + other + "");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      rese.add(new Reserver(resultset.getInt("NumReservation"), resultset.getString("DateDebutReservation"), resultset.getString("DateFinReservation"), resultset.getInt("NbJourReservation"), resultset.getString("EtatReservation"), resultset.getInt("NumClient"), resultset.getInt("NumResponsable"), resultset.getInt("NumReglement")));
    }
    return rese;
  }
  
  public ArrayList searchOne(int i,String id, String NameTable)throws SQLException, ClassNotFoundException{
    ArrayList<Reserver> rese = new ArrayList();
    Connection connection = this.con.conn();
    String other = null;
    if (i == 0) {
      this.sql = ("select * from RESERVER where " + NameTable + "=" + id);
    } else {
      this.sql = ("select * from RESERVER where " + NameTable + "='" + id + "'");
    }
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      rese.add(new Reserver(resultset.getInt("NumReservation"), resultset.getString("DateDebutReservation"), resultset.getString("DateFinReservation"), resultset.getInt("NbJourReservation"), resultset.getString("EtatReservation"), resultset.getInt("NumClient"), resultset.getInt("NumResponsable"), resultset.getInt("NumReglement")));
    }
    return rese;
  }
}
