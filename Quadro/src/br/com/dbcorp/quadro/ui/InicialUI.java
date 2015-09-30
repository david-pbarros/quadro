package br.com.dbcorp.quadro.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import br.com.dbcorp.quadro.gerenciador.Gerenciador;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

public class InicialUI extends InternalUI {
	public InicialUI() {
		
		this.setPreferredSize(new Dimension(Params.INTERNAL_WIDTH - 200, Params.INTERNAL_HEIGHT));
		
		try {
			new Gerenciador().obterMeses();
			
			getContentPane();
			getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("default:grow"),
					FormFactory.DEFAULT_COLSPEC,
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					RowSpec.decode("default:grow"),
					RowSpec.decode("max(49dlu;default)"),
					RowSpec.decode("default:grow"),}));
			
			Image img = Params.splashImage();
			
			int width = img.getWidth(null);
			int height = img.getHeight(null);
			
			JPanelWithBackground panel = new JPanelWithBackground(img);
			panel.setPreferredSize(new Dimension(width, height));
			
			getContentPane().add(panel, "2, 2");
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
		setVisible(true);
	}
	private static final long serialVersionUID = 7068067565699504965L;

	@Override
	public void reset() {
		
	}

}
