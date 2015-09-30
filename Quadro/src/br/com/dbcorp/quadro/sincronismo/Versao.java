package br.com.dbcorp.quadro.sincronismo;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import br.com.dbcorp.quadro.sincronismo.PHPConnection.HTTP_METHOD;
import br.com.dbcorp.quadro.ui.Params;


public class Versao {
	public void verifica() {
		String retorno = "";
		
		try {
			PHPConnection con = new PHPConnection("/service.php/versao", HTTP_METHOD.GET);
			con.connect();
			
			if (con.getResponseCode() != 200) {
				throw new RuntimeException(con.getErrorDetails());
			}
			
			JSONObject obj = con.getResponse();
			
			if (obj != null) {
				int versao = (int) Params.propriedades().get("verionNumber");
				
				if (versao < obj.getInt("versao")) {
					retorno = "\nExiste nova versão do sistema para download.";
					
					String msg = obj.getString("msg");
					
					if (msg.length() > 0) {
						retorno += "\n" + msg;
					}
				}
			}
		} catch (Exception ex) {
			retorno =  "";
		}
		
		if (!"".equals(retorno)) {
			JOptionPane.showMessageDialog(null, retorno, "Nova Versão", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
