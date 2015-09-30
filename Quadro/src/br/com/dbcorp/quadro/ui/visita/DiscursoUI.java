package br.com.dbcorp.quadro.ui.visita;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.dbcorp.quadro.entidades.SemanaVisita;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class DiscursoUI extends JPanel {
	private static final long serialVersionUID = -888691175397519298L;
	
	private JLabel lblDiscurso;
	private JTextField txtTema;
	private JTextField txtCantico;
	private JTextField txtOracao;
	
	private SemanaVisita visita;
	private int tipoDiscurso;
	
	public DiscursoUI(SemanaVisita visita, int tipoDiscurso) {
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(39dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(39dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("70dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		this.visita = visita;
		this.tipoDiscurso = tipoDiscurso;
		
		this.lblDiscurso = new JLabel("");
		this.lblDiscurso.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblDiscurso.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		this.txtTema = new JTextField();
		this.txtCantico = new JTextField();
		this.txtOracao = new JTextField();

		add(this.txtTema, "2, 4, 11, 1, fill, default");
		add(this.lblDiscurso, "2, 2, 11, 1");
		add(new JLabel("C\u00E2ntico:"), "2, 6, right, default");
		add(this.txtCantico, "4, 6, fill, default");
		add(new JLabel("Ora\u00E7\u00E3o Final:"), "6, 6, right, default");
		add(this.txtOracao, "8, 6, 3, 1, fill, default");
		
		this.setCampos();
		
		this.setMinimumSize(new Dimension(931, 85));
		this.setPreferredSize(new Dimension(1200, 85));
	}
	
	public void obterDiscurso() {
		if (tipoDiscurso == 1) {
			this.visita.setPrimeiroDiscurso(this.txtTema.getText());
			this.visita.setCanticoFimServico(this.txtCantico.getText());
			this.visita.setOracaoServico(this.txtOracao.getText());
			
		} else if (tipoDiscurso == 3) {
			this.visita.setTerceiroDiscurso(this.txtTema.getText());
			this.visita.setCanticoFimPublica(this.txtCantico.getText());
			this.visita.setOracaoPublica(this.txtOracao.getText());
		}
	}
	
	private void setCampos() {
		if (tipoDiscurso == 1) {
			this.lblDiscurso.setText("Primeiro discurso");
			
			this.txtTema.setText(this.visita.getPrimeiroDiscurso());
			this.txtCantico.setText(this.visita.getCanticoFimServico());
			this.txtOracao.setText(this.visita.getOracaoServico());
			
		} else if (tipoDiscurso == 3) {
			this.lblDiscurso.setText("Terceiro discurso");
			
			this.txtTema.setText(this.visita.getTerceiroDiscurso());
			this.txtCantico.setText(this.visita.getCanticoFimPublica());
			this.txtOracao.setText(this.visita.getOracaoPublica());
		}
	}
}
