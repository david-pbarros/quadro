package br.com.dbcorp.quadro.ui.visita;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dbcorp.quadro.entidades.SemanaVisita;

public class ReuniaoPublicaUI extends JPanel {
	private static final long serialVersionUID = 7831094812930679771L;
	
	private JTextField txtPresidente;
	private JTextField txtCanticoIni;
	private JTextField txtDiscurso;
	private JTextField txtCanticoMeio;
	private JTextField txtDirigente;
	
	private SemanaVisita visita;

	public ReuniaoPublicaUI(SemanaVisita visita) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(30dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(27dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,}));
		
		this.visita = visita;
		
		TitledBorder title = BorderFactory.createTitledBorder("Reunião Publica");
		this.setBorder(title);
		
		this.txtPresidente = new JTextField();
		this.txtCanticoIni = new JTextField();
		
		JLabel lblDiscursoPblico = new JLabel("Discurso P\u00FAblico");
		lblDiscursoPblico.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.txtDiscurso = new JTextField();
		this.txtCanticoMeio = new JTextField();
		this.txtDirigente = new JTextField();
		
		add(new JLabel("Presidente:"), "2, 2, right, default");
		add(this.txtPresidente, "4, 2, 3, 1, fill, default");
		add(new JLabel("C\u00E2ntico:"), "2, 4, right, default");
		add(this.txtCanticoIni, "4, 4, 3, 1, fill, default");
		add(lblDiscursoPblico, "2, 6, 7, 1");
		add(this.txtDiscurso, "2, 8, 7, 1, fill, default");
		add(new JLabel("C\u00E2ntico:"), "2, 10, right, default");
		add(this.txtCanticoMeio, "4, 10, 3, 1, fill, default");
		add(new JLabel("Dirigente A Sentinela:"), "2, 12, 3, 1, right, default");
		add(this.txtDirigente, "6, 12, 3, 1, fill, default");
		
		this.setCampos();
		
		this.setMinimumSize(new Dimension(931, 180));
		this.setPreferredSize(new Dimension(1200, 180));
	}
	
	public void obterReuniaoPublica() {
		this.visita.setPresidente(this.txtPresidente.getText());
		this.visita.setCanticoIniPublica(this.txtCanticoIni.getText());
		this.visita.setSegundoDiscurso(this.txtDiscurso.getText());
		this.visita.setCanticoMeioPublica(this.txtCanticoMeio.getText());
		this.visita.setDirigenteSentinela(this.txtDirigente.getText());
	}

	private void setCampos() {
		this.txtPresidente.setText(this.visita.getPresidente());
		this.txtCanticoIni.setText(this.visita.getCanticoIniPublica());
		this.txtDiscurso.setText(this.visita.getSegundoDiscurso());
		this.txtCanticoMeio.setText(this.visita.getCanticoMeioPublica());
		this.txtDirigente.setText(this.visita.getDirigenteSentinela());
	}
}
