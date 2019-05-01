/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop;

import java.sql.SQLException;
import tropikhotel.DAO.DaoCategories;
import tropikhotel.DAO.DaoTypes;

/**
 *
 * @author blackran
 */
public class ConverTypeCateg {
	DaoTypes daotypes = new DaoTypes();
	DaoCategories daocategories = new DaoCategories();
	
	public String NumcatToNomCat(int NumCat) throws ClassNotFoundException, SQLException{
		return daocategories.find(NumCat).getDescriptionCategorie();
	}
	
	public int NomCatToNumcat(String NomCat) throws ClassNotFoundException, SQLException{
		return daocategories.findName(NomCat).getNumCategorie();
	}
	
	public String NumTypeToNomType(int NumType) throws ClassNotFoundException, SQLException{
		return daotypes.find(NumType).getNomType();
	}
	
	public int NomTypeToNumType(String NomType) throws ClassNotFoundException, SQLException{
		return daotypes.findName(NomType).getNumType();
	}
}
