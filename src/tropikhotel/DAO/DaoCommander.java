package tropikhotel.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tropikhotel.Con;
import tropikhotel.GetSet.Commander;
import tropikhotel.GetSet.CommanderT;

public class DaoCommander{
    Con con = new Con();
    String sql = "";

    public void add(int NumCommander, int TarifCommander, String DateCommander, int NumClient, int NumReglement) throws SQLException, ClassNotFoundException{
        Commander Comm = new Commander();
        Comm.setNumCommander(NumCommander);
        Comm.setTarifCommander(TarifCommander);
        Comm.setDateCommander(DateCommander);
        Comm.setNumClient(NumClient);
        Comm.setNumReglement(NumReglement);
        Connection connection = this.con.conn();
        this.sql = ("insert into COMMANDER(TarifCommander, DateCommander, NumClient, NumReglement) values ('" + Comm.getTarifCommander() + "','" + Comm.getDateCommander() + "'," + Comm.getNumClient() + "," + Comm.getNumReglement() + ")");
        System.out.println(this.sql);
        Statement statement = connection.createStatement();
        statement.execute(this.sql);
    }
  
    public void remove(int id) throws SQLException, ClassNotFoundException {
        Connection connection = this.con.conn();
        this.sql = ("delete from COMMANDER where NumCommander =" + id);
        Statement statement = connection.createStatement();
        statement.execute(this.sql);
    }
  
    public void mod(int NumCommander, int TarifCommander, String DateCommander, int NumClient, int NumReglement) throws SQLException, ClassNotFoundException {
        Commander Comm = find(NumCommander);
        Comm.setNumCommander(NumCommander);
        Comm.setTarifCommander(TarifCommander);
        Comm.setDateCommander(DateCommander);
        Comm.setNumClient(NumClient);
        Comm.setNumReglement(NumReglement);
        Connection connection = this.con.conn();
        this.sql = ("update COMMANDER set TarifCommander="+Comm.getTarifCommander()+" , DateCommander='"+Comm.getDateCommander()+"' ,NumClient="+Comm.getNumClient()+", NumReglement="+Comm.getNumReglement()+" where NumCommander =" + Comm.getNumCommander());
        Statement statement = connection.createStatement();
        statement.execute(this.sql);
    }
//    public void modMotant(int i, int TarifCommander) throws SQLException, ClassNotFoundException {
//        Commander Comm = find(i);
//        Comm.setNumCommander(i);
//        Comm.setTarifCommander(TarifCommander);
//        
//        Connection connection = this.con.conn();
//        this.sql = ("update COMMANDER set TarifCommander="+Comm.getTarifCommander()+" where NumCommander =" + Comm.getNumCommander());
//        Statement statement = connection.createStatement();
//        statement.execute(this.sql);
//    }
  
    public Commander find(int i) throws ClassNotFoundException, SQLException{
        Connection connection = this.con.conn();
        Commander Comm = null;
        this.sql = ("select * from COMMANDER where NumCommander =" + i);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        System.out.println(this.sql);
        while (resultset.next()) {
          Comm = new Commander(resultset.getInt("NumCommander"), resultset.getInt("TarifCommander"), resultset.getString("DateCommander"), resultset.getInt("NumClient"), resultset.getInt("NumReglement"));
        }
        return Comm;
    }
  
    public ArrayList findAll() throws SQLException, ClassNotFoundException {
        ArrayList<Commander> Comm = new ArrayList();
        Connection connection = this.con.conn();
        this.sql = "select * from COMMANDER";
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
            Comm.add(new Commander(resultset.getInt("NumCommander"), resultset.getInt("TarifCommander"), resultset.getString("DateCommander"), resultset.getInt("NumClient"), resultset.getInt("NumReglement")));
        }
        return Comm;
    }
    
    public ArrayList findAllByReglement(int i) throws SQLException, ClassNotFoundException {
        ArrayList<Commander> Comm = new ArrayList();
        Connection connection = this.con.conn();
        this.sql = "select * from COMMANDER where NumReglement="+i;
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
            Comm.add(new Commander(resultset.getInt("NumCommander"), resultset.getInt("TarifCommander"), resultset.getString("DateCommander"), resultset.getInt("NumClient"), resultset.getInt("NumReglement")));
        }
        return Comm;
    }
  
    public ArrayList searchAll(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Commander> Comm = new ArrayList();
        Connection connection = this.con.conn();
        String other = null;
        if (id.matches("[0-9]*")) {
          other = id;
        }
        this.sql = ("select * from Commander where NumCommander= " + other + " || TarifCommander=" + other + " || DateCommander='" + id + "' || NumClient=" + other + " || NumReglement=" + other + "");
        System.out.println(this.sql);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          Comm.add(new Commander(resultset.getInt("NumCommander"), resultset.getInt("TarifCommander"), resultset.getString("DateCommander"), resultset.getInt("NumClient"), resultset.getInt("NumReglement")));
        }
        return Comm;
    }
    public ObservableList<CommanderT> searchOneT(String id) throws ClassNotFoundException, SQLException {
        ObservableList<CommanderT> Comm = FXCollections.observableArrayList();
        Connection connection = this.con.conn();
        this.sql = ("select * from CATEGORIES where NumCategorie LIKE  '%" + id + "%' || DescriptionCategorie LIKE  '%" + id + "%' ");

        System.out.println(this.sql);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
          Comm.add(new CommanderT(String.valueOf(resultset.getInt("NumCommander")), String.valueOf(resultset.getInt("TarifCommander")), String.valueOf(resultset.getInt("NumClient")), String.valueOf(resultset.getInt("NumReglement"))));
        }
        return Comm;
    }
}
