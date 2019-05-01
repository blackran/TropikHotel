/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desktop;

import javafx.scene.input.KeyEvent;

/**
 *
 * @author blackran
 */
public class ControleChamp {
	public String numberOnly(String Text){
		if(!Text.equals("")){
			String lastText = Text.substring(Text.length()-1, Text.length());
			if (!Text.matches("[0-9]*")) {
				Text = Text.substring(0,Text.length()-1);
			}
		}
		return Text;
	}
	public String textOnly(String Text){
		if(!Text.equals("")){
			String lastText = Text.substring(Text.length()-1, Text.length());
			if (!Text.matches("[a-zA-Z]*")) {
				Text = Text.substring(0,Text.length()-1);
			}
		}
		return Text;
	}
	public String lengthOnly(String Text, int len){
		if(!Text.equals("")){
			if (Text.length()>len) {
				Text = Text.substring(0,Text.length()-1);
			}
		}
		return Text;
	}
}
