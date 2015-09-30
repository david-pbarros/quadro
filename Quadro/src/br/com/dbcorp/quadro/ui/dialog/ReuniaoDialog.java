package br.com.dbcorp.quadro.ui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.dbcorp.quadro.ui.Params;

@SuppressWarnings("rawtypes")
public class ReuniaoDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 7789542379134478478L;
	
	private JComboBox diasSemana;
	private JComboBox diasFind;
	private JButton btnOK;
	private JButton btnCancela;
	private int diaDaSemana;
	private int diaFind;
	
	@SuppressWarnings("unchecked")
	public ReuniaoDialog() {
		setTitle("Escolha o Dia");
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.btnOK = new JButton("Continuar");
		this.btnCancela = new JButton("Cancelar");
		this.diasSemana = new JComboBox(new String[]{"Segunda", "Terca", "Quarta", "Quinta", "Sexta"});
		this.diasFind = new JComboBox(new String[]{"Sabado", "Domingo"});
		
		this.btnOK.addActionListener(this);
		this.btnCancela.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel semanaPanel = new JPanel();
		semanaPanel.add(new JLabel("Reunião Semanal:"));
		semanaPanel.add(this.diasSemana);
		
		JPanel findPanel = new JPanel();
		findPanel.add(new JLabel("Reunião Fim de Semana:"));
		findPanel.add(this.diasFind);
		
		panel.add(semanaPanel, BorderLayout.NORTH);
		panel.add(findPanel, BorderLayout.SOUTH);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.btnOK);
		buttonPanel.add(this.btnCancela);
		
		getContentPane().add(panel, BorderLayout.CENTER); 
		getContentPane().add(buttonPanel, BorderLayout.SOUTH); 
		
		pack();
		
		this.centerScreen();
	}
	
	public int[] exibir() {
		this.setVisible(true);
		
		return new int[]{this.diaDaSemana, this.diaFind};
	}
	
	private void centerScreen() {
		int w = Params.WIDTH - this.getPreferredSize().width;
		int h = Params.HEIGHT - this.getPreferredSize().height;
		setLocation(w/2, h/2);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnOK)) {
			this.marcar();
		} 

		dispose();
	}
	
	private void marcar() {
		switch (this.diasSemana.getSelectedIndex()) {
		case 0:
			this.diaDaSemana = Calendar.MONDAY;
			break;
		case 1:
			this.diaDaSemana = Calendar.TUESDAY;
			break;
		case 2:
			this.diaDaSemana = Calendar.WEDNESDAY;
			break;
		case 3:
			this.diaDaSemana = Calendar.THURSDAY;
			break;
		case 4:
			this.diaDaSemana = Calendar.FRIDAY;
			break;
		}
		
		switch (this.diasFind.getSelectedIndex()) {
		case 0:
			this.diaFind = Calendar.SATURDAY;
			break;
		case 1:
			this.diaFind = Calendar.SUNDAY;
			break;
		}
	}
}