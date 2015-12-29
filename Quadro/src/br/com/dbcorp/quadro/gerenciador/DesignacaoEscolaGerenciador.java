package br.com.dbcorp.quadro.gerenciador;

import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.dbcorp.quadro.DataBaseHelper;
import br.com.dbcorp.quadro.Log;
import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.Genero;
import br.com.dbcorp.quadro.entidades.Pessoa;
import br.com.dbcorp.quadro.entidades.VidaMinisterio;
import br.com.dbcorp.quadro.ui.InternalUI;
import br.com.dbcorp.quadro.ui.Params;

@SuppressWarnings("unchecked")
public class DesignacaoEscolaGerenciador extends Gerenciador {
	
	private Log log = Log.getInstance();
	private static int DIA = 0;
	private static int TIPO = 1;
	private static int SALA = 2;
	private static int NUMERO = 3;
	private static int ESTUDANTE = 4;
	private static int GENERO_ESTUDANTE = 5;
	private static int AJUDANTE = 6;
	private static int GENERO_AJUDANTE = 7;
	private static int TEMA = 8;

	public List<DesignacaoEscola> obterDesignacaoesPorSala(DiaReuniao diaReuniao, String sala) {
		Query query = DataBaseHelper.createQuery("FROM DesignacaoEscola d JOIN FETCH d.dia dt WHERE dt = :dia AND d.sala = :sala")
				.setParameter("dia", diaReuniao)
				.setParameter("sala", sala);
		
		return query.getResultList();
	}
	
	public VidaMinisterio obterVidaMinisterio(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("FROM VidaMinisterio d WHERE d.dia = :dia")
				.setParameter("dia", diaReuniao);
		
		VidaMinisterio vidaMinisterio;
		
		try {
			vidaMinisterio = (VidaMinisterio) query.getSingleResult();
			
		} catch (NoResultException ex) {
			vidaMinisterio = new VidaMinisterio();
			vidaMinisterio.setDia(diaReuniao);
		}
		
		return vidaMinisterio;
	}
	
	public List<DesignacaoEscola> obterDesignacaoes(DiaReuniao diaReuniao) {
		Query query = DataBaseHelper.createQuery("FROM DesignacaoEscola d JOIN FETCH d.dia dt WHERE dt = :dia")
				.setParameter("dia", diaReuniao);
		
		return query.getResultList();
	}
	
	public void atualizarDesignacoes(List<DesignacaoEscola> designacoes) {
		for (DesignacaoEscola designacao : designacoes) {
			if (designacao.getId() == 0) {
				DataBaseHelper.persist(designacao);
				
			} else {
				DataBaseHelper.merge(designacao);
			}
			
			Genero genero = null;
			
			switch (designacao.getNumero()) {
			case 0:
			case 1:
				genero = Genero.M;
				break;
			case 2:
				genero = Genero.F;
				break;
			}
			
			if (genero != null) {
				this.salvaPessoa(designacao.getEstudante(), genero);
				this.salvaPessoa(designacao.getAjudante(), genero);
			}
		}
	}
	
	public void atualizarVidaMinisterios(List<VidaMinisterio> vidaMinisterios) {
		for (VidaMinisterio vidaMinisterio : vidaMinisterios) {
			if (vidaMinisterio.getId() == 0) {
				DataBaseHelper.persist(vidaMinisterio);
				
			} else {
				DataBaseHelper.merge(vidaMinisterio);
			}
		}
	}
	
	public void atualizarPessoas(List<Pessoa> pessoas) {
		for (Pessoa pessoa : pessoas){
			Query query = DataBaseHelper.createQuery("FROM Pessoa p WHERE p.nome = :nome")
					.setParameter("nome", pessoa.getNome());
			
			if (query.getResultList().isEmpty()) {
				DataBaseHelper.persist(pessoa);
			}
		}
	}
	
	public DiaReuniao obtemDia(LocalDate data) {
		Query query = DataBaseHelper.createQuery("FROM DiaReuniao d WHERE d.dia = :dia")
				.setParameter("dia", data);
		
		return (DiaReuniao) query.getSingleResult();
	}
	
	public void importar(InternalUI designacaoesUI) {
		JFileChooser fileChooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
		fileChooser.setFileFilter(filter);
		
		if (fileChooser.showOpenDialog(designacaoesUI) == JFileChooser.APPROVE_OPTION) {
			designacaoesUI.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			FileReader fr = null;
			BufferedReader bf = null;
			
			try {
				fr = new FileReader(fileChooser.getSelectedFile());
				bf = new BufferedReader(fr);
			
				String line = bf.readLine();
				String[] campos = line.split(";");
				
				if ( !campos[0].equalsIgnoreCase("dia") || !campos[1].equalsIgnoreCase("tipo") || !campos[2].equalsIgnoreCase("sala") 
						|| !campos[3].equalsIgnoreCase("numero") || !campos[4].equalsIgnoreCase("estudante") || !campos[5].equalsIgnoreCase("genero")
						|| !campos[6].equalsIgnoreCase("ajudante") || !campos[7].equalsIgnoreCase("genero") || !campos[8].equalsIgnoreCase("tema")) {
					
					JOptionPane.showMessageDialog(designacaoesUI, "Arquivo inválido.", "Erro", JOptionPane.WARNING_MESSAGE);
					
				} else {
					LocalDate data = null;
					List<DesignacaoEscola> designacoesSave = new ArrayList<DesignacaoEscola>();
					List<Pessoa> pessoasSave = new ArrayList<>();
					
					DiaReuniao diaReuniao = null;
					
					while ((line = bf.readLine()) != null) {
						campos = line.replace(";", " ;").split(";");
						
						 for (int i = 0; i < campos.length; i++) {
							 campos[i] = campos[i].trim();
						 }
						
						try {
							List<DesignacaoEscola> designacoes = null;
							
							LocalDate temp = LocalDate.parse(campos[DIA], Params.dateFormate());
							
							if (!temp.equals(data)) {
								data = temp;
								
								diaReuniao = this.obtemDia(data);
								
								designacoes  = this.obterDesignacaoes(diaReuniao);
							}
							
							if ("N".equalsIgnoreCase(campos[TIPO]) || "AV".equalsIgnoreCase(campos[TIPO])) {
								int numero = Integer.parseInt(campos[NUMERO]);
								
								DesignacaoEscola designacao = obtemDesignacaoSalaumero(designacoes, campos[SALA], numero);
								designacao = designacao == null ? new DesignacaoEscola() : designacao;
								designacao.setNumero(numero);
								designacao.setDia(diaReuniao);
								designacao.setEstudante(this.capitalize(campos[ESTUDANTE]));
								designacao.setSala(campos[SALA]);
								
								if (!"".equals(campos[AJUDANTE].trim())) {
									designacao.setAjudante(this.capitalize(campos[AJUDANTE]));
									
									Pessoa pessoa = new Pessoa();
									pessoa.setNome(designacao.getAjudante());
									pessoa.setGenero(campos[GENERO_AJUDANTE] == "M" ? Genero.M : Genero.F);
										
									pessoasSave.add(pessoa);
								}
								
								if (campos.length == 9 && !"".equals(campos[TEMA].trim())) {
									designacao.setTema(campos[TEMA]);
								}
								
								designacoesSave.add(designacao);
								
								Pessoa pessoa = new Pessoa();
								pessoa.setNome(designacao.getEstudante());
								pessoa.setGenero(campos[GENERO_ESTUDANTE] == "M" ? Genero.M : Genero.F);
									
								pessoasSave.add(pessoa);
							}
							
							if ( !"N".equalsIgnoreCase(campos[TIPO]) || "AV".equalsIgnoreCase(campos[TIPO])) {
								diaReuniao.setTipo(campos[TIPO]);
								this.atualizaDia(diaReuniao);
							}
						} catch (NoResultException exception) {
							log.error(exception);
						}
					}

					this.atualizarDesignacoes(designacoesSave);
					this.atualizarPessoas(pessoasSave);
									
					JOptionPane.showMessageDialog(designacaoesUI, "Processo de importação finalizado.", "Informação", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception ex) {
				String erro = "Erro no processo de importação.";
				
				log.error(erro, ex);
				JOptionPane.showMessageDialog(designacaoesUI, erro, "Erro", JOptionPane.ERROR_MESSAGE);
				
			} finally {
				try {
					if (bf != null) {
						bf.close();
					}
					
					if (fr != null) {
						fr.close();
					}
				} catch (Exception ex) {
					log.error(ex);
				}
			}
		}
	}
	
	public String tema(DesignacaoEscola designacao) {
		Query query = DataBaseHelper.createQuery("FROM DesignacaoEscola d WHERE d.sala = 'A' AND d.dia = :dia AND d.numero = :numero")
				.setParameter("dia", designacao.getDia())
				.setParameter("numero", designacao.getNumero());
		
		try {
			return ((DesignacaoEscola) query.getSingleResult()).getTema();
			
		} catch (Exception e) {
			return null;
		}
	}
	
	private void atualizaDia(DiaReuniao dia) {
		DataBaseHelper.merge(dia);
	}
	
	private DesignacaoEscola obtemDesignacaoSalaumero(List<DesignacaoEscola> designacoes, String sala, int numero) {
		if (designacoes != null) {
			for (int i = 0; i < designacoes.size(); i++) {
				DesignacaoEscola d = designacoes.get(i);
				
				if ( sala.equals(d.getSala()) && numero == d.getNumero() ) {
					designacoes.remove(i);
					
					return d;
				}
			}
		}
		
		return null;
	}
	
	private String capitalize(String original) {
		StringBuffer novo = new StringBuffer();
		
		if (original != null) {
			String[] palavras = original.toLowerCase().split(" ");
			
			for (String palavra : palavras) {
				if (palavra.length() > 2) {
					novo.append(palavra.substring(0, 1).toUpperCase())
						.append(palavra.substring(1));
					
				} else {
					novo.append(palavra);
				}
				
				novo.append(" ");
			}
		}
		
		return novo.toString().trim();
	}
}
