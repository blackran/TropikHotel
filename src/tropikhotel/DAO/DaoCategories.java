package tropikhotel.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tropikhotel.Con;
import tropikhotel.GetSet.CategorieT;
import tropikhotel.GetSet.Categories;

public class DaoCategories
{
  Con con = new Con();
  String sql = "";
  
  public void add(int NumCategorie, String DescriptionCategorie)
    throws SQLException, ClassNotFoundException
  {
    Categories Cat = new Categories();
    Cat.setNumCategorie(NumCategorie);
    Cat.setDescriptionCategorie(DescriptionCategorie);
    
    Connection connection = this.con.conn();
    this.sql = ("insert into CATEGORIES(DescriptionCategorie) values ('" + Cat.getDescriptionCategorie() + "')");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void remove(int id)
    throws SQLException, ClassNotFoundException
  {
    Connection connection = this.con.conn();
    this.sql = ("delete from CATEGORIES where NumCategorie =" + id);
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
  public void mod(int NumCategorie, String DescriptionCategorie)
    throws ClassNotFoundException, SQLException
  {
    Categories Cat = find(NumCategorie);
    Cat.setNumCategorie(NumCategorie);
    Cat.setDescriptionCategorie(DescriptionCategorie);
    Connection connection = this.con.conn();
    this.sql = ("update CATEGORIES set DescriptionCategorie='" + Cat.getDescriptionCategorie() + "' where NumCategorie=" + Cat.getNumCategorie());
    Statement statement = connection.createStatement();
    statement.execute(this.sql);
  }
  
	public Categories find(int i) throws ClassNotFoundException, SQLException {
		Connection connection = this.con.conn();
		Categories Cat = null;
		this.sql = ("select * from CATEGORIES where NumCategorie =" + i);
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(this.sql);
		System.out.println(this.sql);
		while (resultset.next()) {
		  Cat = new Categories(resultset.getInt("NumCategorie"), resultset.getString("DescriptionCategorie"));
		}
		return Cat;
	}
	
	public Categories findName(String i) throws ClassNotFoundException, SQLException {
		Connection connection = this.con.conn();
		Categories Cat = null;
		this.sql = ("select * from CATEGORIES where DescriptionCategorie ='" + i +"'");
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(this.sql);
		System.out.println(this.sql);
		while (resultset.next()) {
		  Cat = new Categories(resultset.getInt("NumCategorie"), resultset.getString("DescriptionCategorie"));
		}
		return Cat;
	}

	public ArrayList findAll() throws SQLException, ClassNotFoundException {
		ArrayList<Categories> Cat = new ArrayList();
		Connection connection = this.con.conn();
		this.sql = "select * from CATEGORIES";
		Statement statement = connection.createStatement();
		ResultSet resultset = statement.executeQuery(this.sql);
		while (resultset.next()) {
		  Cat.add(new Categories(resultset.getInt("NumCategorie"), resultset.getString("DescriptionCategorie")));
		}
		return Cat;
	}
  
  public ArrayList searchAll(String id)
    throws SQLException, ClassNotFoundException
  {
    ArrayList<Categories> Cat = new ArrayList();
    Connection connection = this.con.conn();
    String other = null;
    if (id.matches("[0-9]*")) {
      other = id;
    }
    this.sql = ("select * from CATEGORIES where NumCategorie= " + other + " || DescriptionCategorie='" + id + "'");
    System.out.println(this.sql);
    Statement statement = connection.createStatement();
    ResultSet resultset = statement.executeQuery(this.sql);
    while (resultset.next()) {
      Cat.add(new Categories(resultset.getInt("NumCategorie"), resultset.getString("DescriptionCategorie")));
    }
    return Cat;
  }
    public ObservableList<CategorieT> searchOneT(String id) throws ClassNotFoundException, SQLException {
        ObservableList<CategorieT> Cat = FXCollections.observableArrayList();
        Connection connection = this.con.conn();
        this.sql = ("select * from CATEGORIES where NumCategorie LIKE  '%" + id + "%' || DescriptionCategorie LIKE  '%" + id + "%' ");

        System.out.println(this.sql);
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(this.sql);
        while (resultset.next()) {
            Cat.add(new CategorieT(String.valueOf(resultset.getInt("NumCategorie")), resultset.getString("DescriptionCategorie")));
        }
        return Cat;
    }
}
