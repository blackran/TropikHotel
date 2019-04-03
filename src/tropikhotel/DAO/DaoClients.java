package tropikhotel.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tropikhotel.Con;
import tropikhotel.GetSet.Clients;
import tropikhotel.GetSet.ClientsT;

public class DaoClients
{
  Con con = new Con();
  String sql = "";
  
  public void add(String NomClient, String AddressClient, String CpClient, String PaysClient, String TelClient, String EmailClient, String AnneeCreClient)
    throws SQLException, ClassNotFoundException
  {
    Clients cli = new Clients();
    cli.setNomClient(NomClient);
    cli.setAddressClient(AddressClient);
    cli.setCpClient(CpClient);
    cli.setPaysClient(PaysClient);
    cli.setTelClient(TelClient);
    cli.setEmailClient(EmailClient);
    cli.setAnneeCreClient(AnneeCreClient);
    
    Connection connection = this.con.conn();
    this.sql = ("insert into CLIENTS(NomClient, AddressClient, CpClient, PaysClient, TelClient, EmailClient, AnneeCreClient) values ('" + cli.getNomClient() + "','" + cli.getAddressClient() + "','" + cli.getCpClient() + "','" + cli.getPaysClient() + "','" + cli.getTelClient() + "','" + cli.getEmailClient() + "','" + cli.getAnneeCreClient() + "')");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.conn();
    this.sql = ("delete from CLIENTS where NumClient =" + id);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void mod(int i, String NomClient, String AddressClient, String CpClient, String PaysClient, String TelClient, String EmailClient, String AnneeCreClient)
    throws SQLException, ClassNotFoundException
  {
    Clients cli = find(i);
    cli.setNomClient(NomClient);
    cli.setAddressClient(AddressClient);
    cli.setCpClient(CpClient);
    cli.setPaysClient(PaysClient);
    cli.setTelClient(TelClient);
    cli.setEmailClient(EmailClient);
    cli.setAnneeCreClient(AnneeCreClient);
    Connection connection = this.con.conn();
    Statement statement = connection.createStatement();
    this.sql = ("update CLIENTS set NomClient='" + cli.getNomClient() + "', AddressClient='" + cli.getAddressClient() + "',CpClient='" + cli.getCpClient() + "',PaysClient='" + cli.getPaysClient() + "',TelClient='" + cli.getTelClient() + "',EmailClient='" + cli.getEmailClient() + "', AnneeCreClient='" + cli.getAnneeCreClient() + "' where NumClient =" + i);
    System.out.println(this.sql);
    statement.execute(this.sql);
  }
  
  public Clients find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    Clients cli = null;
    this.sql = ("select * from CLIENTS where NumClient =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      cli = new Clients(resultset.getInt("NumClient"), resultset.getString("NomClient"), resultset.getString("AddressClient"), resultset.getString("CpClient"), resultset.getString("PaysClient"), resultset.getString("TelClient"), resultset.getString("EmailClient"), resultset.getString("AnneeCreClient"));
    }
    return cli;
  }
  
  public int findAnnee(String annee) throws ClassNotFoundException, SQLException {
    int cli = 0;
    Connection connection = this.con.conn();
    this.sql = ("select count(*) as COUNT from CLIENTS where AnneeCreClient =" + annee);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      cli = resultset.getInt("COUNT");
    }
    return cli;
  }
  
  public ArrayList findAll() throws SQLException, ClassNotFoundException {
    ArrayList<Clients> cli = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from CLIENTS";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      cli.add(new Clients(resultset.getInt("NumClient"), resultset.getString("NomClient"), resultset.getString("AddressClient"), resultset.getString("CpClient"), resultset.getString("PaysClient"), resultset.getString("TelClient"), resultset.getString("EmailClient"), resultset.getString("AnneeCreClient")));
    }
    return cli;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Clients> cli = new ArrayList();
    Connection connection = this.con.conn();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from CLIENTS where NumClient= " + other + " || NomClient='" + id + "' || AddressClient='" + id + "' || CpClient='" + id + "' || PaysClient='" + id + "' || TelClient='" + id + "' || EmailClient='" + id + "' || AnneeCreClient='" + id + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      cli.add(new Clients(resultset.getInt("NumClient"), resultset.getString("NomClient"), resultset.getString("AddressClient"), resultset.getString("CpClient"), resultset.getString("PaysClient"), resultset.getString("TelClient"), resultset.getString("EmailClient"), resultset.getString("AnneeCreClient")));
    }
    return cli;
  }
    public ObservableList<ClientsT> searchAllT(String id) throws SQLException, ClassNotFoundException {
        ObservableList<ClientsT> cli = FXCollections.observableArrayList();
        Connection connection = this.con.conn();
        this.sql = ("select * from CLIENTS where NumClient LIKE  '%" + id + "%' || NomClient LIKE '%" + id + "%' || AddressClient LIKE '%" + id + "%' || CpClient LIKE '%" + id + "%' || PaysClient LIKE '%" + id + "%' || TelClient LIKE '%" + id + "%' || EmailClient LIKE '%" + id + "%'");
        System.out.println(this.sql);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          cli.add(new ClientsT(String.valueOf(resultset.getInt("NumClient")), resultset.getString("NomClient"), resultset.getString("TelClient")));
        }
        return cli;
    }
}
