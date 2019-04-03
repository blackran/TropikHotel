package tropikhotel.DAO;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tropikhotel.Con;
import tropikhotel.GetSet.Reglements;
import tropikhotel.GetSet.ReglementsT;
import tropikhotel.GetSet.ReservationsT;

public class DaoReglements
{
  Con con = new Con();
  String sql = "";
  
  public void add() throws SQLException, ClassNotFoundException {
    Reglements regl = new Reglements();
    
    Connection connection = this.con.conn();
    this.sql = ("insert into REGLEMENTS(EtatReglement, MontantReglement, AnneeReglement) values ('non regler', 0,'" + LocalDate.now().getYear() + "')");
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id) throws SQLException, ClassNotFoundException {
    Connection connection = this.con.conn();
    this.sql = ("delete from REGLEMENTS where NumReglement =" + id);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void mod(int i, String EtatReglement, int MontantReglement, String AnneReglement) throws SQLException, ClassNotFoundException {
    Reglements regl = find(i);
    regl.setEtatReglement(EtatReglement);
    regl.setMontantReglement(MontantReglement);
    regl.setAnneReglement(AnneReglement);
    Connection connection = this.con.conn();
    Statement statement = connection.createStatement();
    this.sql = ("update REGLEMENTS set EtatReglement='" + regl.getEtatReglement() + "', MontantReglement=" + regl.getMontantReglement() + ", AnneeReglement='" + regl.getAnneReglement() + "' where NumReglement =" + i);
    statement.execute(this.sql);
  }
  
  public void modEtats(int i, String EtatReglement) throws SQLException, ClassNotFoundException {
    Reglements regl = find(i);
    regl.setEtatReglement(EtatReglement);
    Connection connection = this.con.conn();
    Statement statement = connection.createStatement();
    this.sql = ("update REGLEMENTS set EtatReglement='" + regl.getEtatReglement() + "' where NumReglement =" + i);
    statement.execute(this.sql);
  }
  
  public Reglements find(int i)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    Reglements regl = null;
    this.sql = ("select * from REGLEMENTS where NumReglement =" + i);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      regl = new Reglements(resultset.getInt("NumReglement"), resultset.getString("EtatReglement"), resultset.getInt("MontantReglement"), resultset.getString("AnneeReglement"));
    }
    return regl;
  }
  
  public int findSomme(String annee)
    throws ClassNotFoundException, SQLException
  {
    Connection connection = this.con.conn();
    int regl = 0;
    this.sql = ("select * from REGLEMENTS where AnneeReglement ='" + annee + "'");
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      regl += resultset.getInt("MontantReglement");
    }
    return regl;
  }
  
  public ArrayList findAll()
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Reglements> regl = new ArrayList();
    Connection connection = this.con.conn();
    this.sql = "select * from REGLEMENTS";
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      regl.add(new Reglements(resultset.getInt("NumReglement"), resultset.getString("EtatReglement"), resultset.getInt("MontantReglement"), resultset.getString("AnneeReglement")));
    }
    return regl;
  }
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Reglements> regl = new ArrayList();
    Connection connection = this.con.conn();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from REGLEMENTS where NumReglement= " + other + " || EtatReglement='" + id + "' || MontantReglement=" + other + "");
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      regl.add(new Reglements(resultset.getInt("NumReglement"), resultset.getString("EtatReglement"), resultset.getInt("MontantReglement"), resultset.getString("AnneeReglement")));
    }
    return regl;
  }

    public ObservableList<ReglementsT> searchOneT(String id) throws ClassNotFoundException, SQLException {
        ObservableList<ReglementsT> regl = FXCollections.observableArrayList();
        Connection connection = this.con.conn();
        this.sql = ("select * from REGLEMENTS where NumReglement LIKE  '%" + id + "%' || EtatReglement LIKE  '%" + id + "%' || MontantReglement LIKE  '%" + id + "%'");
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          regl.add(new ReglementsT(String.valueOf(resultset.getInt("NumReglement")), resultset.getString("EtatReglement"), String.valueOf(resultset.getInt("MontantReglement"))));
        }
        return regl;
    }
}
