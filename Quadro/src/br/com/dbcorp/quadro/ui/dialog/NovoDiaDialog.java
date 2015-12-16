package br.com.dbcorp.quadro.ui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.gerenciador.MesGerenciador;
import br.com.dbcorp.quadro.ui.Params;
import br.com.dbcorp.quadro.ui.mes.MesesUI;

public class NovoDiaDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 7789542379134478478L;
	
	private JButton btnOK;
	private JButton btnCancela;
	private JTextField txDia;
	
	private Mes mes;
	private MesGerenciador gerenciador;
	private MesesUI parent;
	
	public NovoDiaDialog(Mes mes, MesGerenciador gerenciador, MesesUI parent) {
		setTitle("Novo Dia");
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.mes = mes;
		this.gerenciador = gerenciador;
		this.parent = parent;
		
		this.btnOK = new JButton("Continuar");
		this.btnCancela = new JButton("Cancelar");
		
		this.btnOK.addActionListener(this);
		this.btnCancela.addActionListener(this);
		
		this.txDia = new JTextField();
		this.txDia.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel semanaPanel = new JPanel();
		semanaPanel.add(new JLabel("Dia:"));
		semanaPanel.add(this.txDia);
		panel.add(semanaPanel, BorderLayout.NORTH);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.btnOK);
		buttonPanel.add(this.btnCancela);
		
		getContentPane().add(panel, BorderLayout.CENTER); 
		getContentPane().add(buttonPanel, BorderLayout.SOUTH); 
		
		pack();
		
		this.centerScreen();
	}
	
	private void centerScreen() {
		int w = Params.WIDTH - this.getPreferredSize().width;
		int h = Params.HEIGHT - this.getPreferredSize().height;
		setLocation(w/2, h/2);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(this.btnOK)) {
			this.verificarData();
		} 

		dispose();
	}
	
	private void verificarData() {
		String textoDia = this.txDia.getText();
		
		if (textoDia != null && !"".equals(textoDia)) {
			try {
				int dia = Integer.parseInt(textoDia);
				
				if (dia < 1 || dia > 31) {
					JOptionPane.showMessageDialog(this, "Não é um dia válido.", "Erro", JOptionPane.ERROR_MESSAGE);
				
				} else {
					LocalDate data = LocalDate.of(this.mes.getAno(), this.mes.getMes().getNumero(), 1);
					
					if (data.lengthOfMonth() >= dia) {
						data = data.withDayOfMonth(dia);
						
						if (this.gerenciador.existeDia(data)) {
							JOptionPane.showMessageDialog(this, "Este dia já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
							
						} else {
							DiaReuniao diaReuniao = new DiaReuniao();
							diaReuniao.setDia(data);
							
							DayOfWeek diaSemana = data.getDayOfWeek();
							
							if (diaSemana == DayOfWeek.SUNDAY || diaSemana == DayOfWeek.SATURDAY) {
								diaReuniao.setQuando("F");
								
							} else {
								diaReuniao.setQuando("S");
							}
							
							this.mes.getDias().add(diaReuniao);
							
							this.gerenciador.atualizar(this.mes);
							
							this.parent.setDias();
						}
					} else {
						JOptionPane.showMessageDialog(this, "Este mes não tem 31 dias.", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Não é um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}