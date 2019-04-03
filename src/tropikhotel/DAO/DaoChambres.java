package tropikhotel.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tropikhotel.Con;
import tropikhotel.GetSet.Chambres;
import tropikhotel.GetSet.ChambresT;

public class DaoChambres
{
  Con con = new Con();
  String sql = "";
  
  public void add(String NomChambre, String TelChambre, String EtageChambre, String ChauffeauChambre, int PrixChambre, int NumCategorie, int NumType) throws SQLException, ClassNotFoundException {
    Chambres Cha = new Chambres();
    Cha.setNomChambre(NomChambre);
    Cha.setTelChambre(TelChambre);
    Cha.setEtageChambre(EtageChambre);
    Cha.setChauffeauChambre(ChauffeauChambre);
    Cha.setPrixChambre(PrixChambre);
    Cha.setNumCategorie(NumCategorie);
    Cha.setNumType(NumType);
    
    Connection connection = this.con.conn();
    this.sql = ("insert into CHAMBRES(NomChambre,  TelChambre,  EtageChambre, ChauffeauChambre, PrixChambre, NumCategorie, NumType) values ('" + Cha.getNomChambre() + "','" + Cha.getTelChambre() + "','" + Cha.getEtageChambre() + "','" + Cha.getChauffeauChambre() + "'," + Cha.getPrixChambre() + "," + Cha.getNumCategorie() + "," + Cha.getNumType() + ")");
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(String id) throws SQLException, ClassNotFoundException {
    Connection connection = this.con.conn();
    this.sql = ("delete from CHAMBRES where NomChambre ='" + id + "'");
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void mod(String NomChambre, String TelChambre, String EtageChambre, String ChauffeauChambre, int PrixChambre, int NumCategorie, int NumType)
    throws SQLException, ClassNotFoundException
  {
    Chambres Cha = find(NomChambre);
    Cha.setNomChambre(NomChambre);
    Cha.setTelChambre(TelChambre);
    Cha.setEtageChambre(EtageChambre);
    Cha.setChauffeauChambre(ChauffeauChambre);
    Cha.setPrixChambre(PrixChambre);
    Cha.setNumCategorie(NumCategorie);
    Cha.setNumType(NumType);
    Connection connection = this.con.conn();
    Statement statement = connection.createStatement();
    this.sql = ("update CHAMBRES set TelChambre='" + Cha.getTelChambre() + "',EtageChambre='" + Cha.getEtageChambre() + "',ChauffeauChambre='" + Cha.getChauffeauChambre() + "',PrixChambre=" + Cha.getPrixChambre() + ",NumCategorie=" + Cha.getNumCategorie() + ",NumType= " + Cha.getNumType() + " where NomChambre='" + Cha.getNomChambre() + "'");
    statement.execute(this.sql);
  }
  
  public Chambres find(String i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    Chambres Cha = null;
    this.sql = ("select * from CHAMBRES where NomChambre ='" + i + "'");
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      Cha = new Chambres(resultset.getString("NomChambre"), resultset.getString("TelChambre"), resultset.getString("EtageChambre"), resultset.getString("ChauffeauChambre"), resultset.getInt("PrixChambre"), resultset.getInt("NumCategorie"), resultset.getInt("NumType"));
    }
    return Cha;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Chambres> Cha = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from CHAMBRES";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      Cha.add(new Chambres(resultset.getString("NomChambre"), resultset.getString("TelChambre"), resultset.getString("EtageChambre"), resultset.getString("ChauffeauChambre"), resultset.getInt("PrixChambre"), resultset.getInt("NumCategorie"), resultset.getInt("NumType")));
    }
    return Cha;
  }
  
    public ArrayList searchAll(String id) throws SQLException, ClassNotFoundException{
        ArrayList<Chambres> Cha = new ArrayList();
        Connection connection = this.con.conn();
        String other = null;
        if (id.matches("[0-9]*")) {
          other = id;
        }
        this.sql = ("select * from CHAMBRES where NomChambre= '" + id + "' || TelChambre='" + id + "' || EtageChambre='" + id + "' || ChauffeauChambre='" + id + "|| PrixChambre=" + other + "' || NumCategorie=" + other + " || NumType=" + other + "");
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          Cha.add(new Chambres(resultset.getString("NomChambre"), resultset.getString("TelChambre"), resultset.getString("EtageChambre"), resultset.getString("ChauffeauChambre"), resultset.getInt("PrixChambre"), resultset.getInt("NumCategorie"), resultset.getInt("NumType")));
        }
        return Cha;
    }
    public ObservableList<ChambresT> searchOneT(String id) throws ClassNotFoundException, SQLException {
        ObservableList<ChambresT> Cha = FXCollections.observableArrayList();
        Connection connection = this.con.conn();
        this.sql = ("select * from CHAMBRES where NomChambre LIKE  '%" + id + "%' || TelChambre LIKE  '%" + id + "%' || EtageChambre LIKE  '%" + id + "%' || ChauffeauChambre LIKE  '%" + id + "%' || PrixChambre LIKE  '%" + id + "%' || NumCategorie LIKE  '%" + id + "%' || NumType LIKE  '%" + id + "%' ");
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          Cha.add(new ChambresT(resultset.getString("NomChambre"), resultset.getString("TelChambre"), resultset.getString("EtageChambre"), resultset.getString("ChauffeauChambre"), String.valueOf(resultset.getInt("PrixChambre")), String.valueOf(resultset.getInt("NumCategorie")), String.valueOf(resultset.getInt("NumType"))));
        }
        return Cha;
    }
}
