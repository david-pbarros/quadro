package br.com.dbcorp.quadro.report;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;

import br.com.dbcorp.quadro.Log;
import br.com.dbcorp.quadro.entidades.DesignacaoEscola;
import br.com.dbcorp.quadro.entidades.DesignacaoServico;
import br.com.dbcorp.quadro.entidades.DiaReuniao;
import br.com.dbcorp.quadro.entidades.DiaReuniao.TipoDia;
import br.com.dbcorp.quadro.entidades.Discurso;
import br.com.dbcorp.quadro.entidades.EquipeServico;
import br.com.dbcorp.quadro.entidades.Estudo;
import br.com.dbcorp.quadro.entidades.Limpeza;
import br.com.dbcorp.quadro.entidades.Mes;
import br.com.dbcorp.quadro.entidades.SemanaVisita;
import br.com.dbcorp.quadro.entidades.Sentinela;
import br.com.dbcorp.quadro.entidades.Servico;
import br.com.dbcorp.quadro.entidades.VidaMinisterio;
import br.com.dbcorp.quadro.gerenciador.Gerenciador;
import br.com.dbcorp.quadro.report.dto.DesignacaoEscolaDTO;
import br.com.dbcorp.quadro.report.dto.DiscursoDTO;
import br.com.dbcorp.quadro.report.dto.IndicadoresDTO;
import br.com.dbcorp.quadro.report.dto.SentinelaLivroDTO;
import br.com.dbcorp.quadro.report.dto.ServicoDTO;
import br.com.dbcorp.quadro.report.dto.VisitaDTO;
import br.com.dbcorp.quadro.report.dto.VisitaServicoDTO;
import br.com.dbcorp.quadro.ui.Params;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class ReportCommon {
	
	private Log log = Log.getInstance();
	
	private static ReportCommon reportCommon;
	
	private Map<String, String> abreviacoes;
	
	private Properties prop;
	
	private Gerenciador gerenciador;
	
	private ReportManager reportManager;
	
	private ReportCommon() {
		super();
		
		this.abreviacoes = new HashMap<String, String>();		
		this.gerenciador = new Gerenciador();
		this.reportManager = new ReportManager();
	}
	
	public static ReportCommon getInstance() {
		if (reportCommon == null) {
			reportCommon = new ReportCommon();
			
			reportCommon.prop = new Properties();
			
			reportCommon.prop.put("caixaTitulo", Params.caixaTitulo());
			reportCommon.prop.put("caixaTituloMenor", Params.caixaTituloMenor());
			reportCommon.prop.put("caixaMes", Params.caixaMes());
		}
		
		return reportCommon;
	}
	
	public void gerarSentinela(List<Sentinela> sentinelaMes, Mes mes) {
		this.prop.put("mes", mes.getMes().name());
		
		List<SentinelaLivroDTO> sentinelas = new ArrayList<SentinelaLivroDTO>();
		
		for (Sentinela sentinela : sentinelaMes) {
			SentinelaLivroDTO dto = new SentinelaLivroDTO();
			
			dto.setLeitor(this.nomeAbreviado(sentinela.getLeitor()));
			dto.setPresidente(this.nomeAbreviado(sentinela.getPresidente()));
			dto.setDataReuniao(sentinela.getDiaReuniao().getDia());
			
			if (TipoDia.ASSEMBLEIA == sentinela.getDiaReuniao().getTipoDia()) {
				dto.setTpAssembleia(especialText(sentinela.getDiaReuniao()));
			
			} else if (TipoDia.VISITA == sentinela.getDiaReuniao().getTipoDia()) {
				dto.setVisita(true);
			}
			
			sentinelas.add(dto);
		}
		
		this.gerarRelatorio("LeitorPresidente", new JRBeanCollectionDataSource(sentinelas));
	}
	
	public void gerarEstudoBiblico(List<Estudo> estudoMes, Mes mes) {
		this.prop.put("mes", mes.getMes().name());
		
		List<SentinelaLivroDTO> sentinelas = new ArrayList<SentinelaLivroDTO>();
		
		for (Estudo estudo : estudoMes) {
			SentinelaLivroDTO dto = new SentinelaLivroDTO();
			
			dto.setLeitor(this.nomeAbreviado(estudo.getLeitor()));
			dto.setPresidente(this.nomeAbreviado(estudo.getDirigente()));
			dto.setDataReuniao(estudo.getDiaReuniao().getDia());
			
			if (TipoDia.ASSEMBLEIA == estudo.getDiaReuniao().getTipoDia()) {
				dto.setTpAssembleia(especialText(estudo.getDiaReuniao()));
			
			} else if (TipoDia.VISITA == estudo.getDiaReuniao().getTipoDia()) {
				dto.setVisita(true);
			}
			
			sentinelas.add(dto);
		}
		
		this.gerarRelatorio("Livro", new JRBeanCollectionDataSource(sentinelas));
	}
	
	public void gerarLimpeza(List<Limpeza> limpezas) {
		this.gerarRelatorio("Limpeza", new JRBeanCollectionDataSource(limpezas));
	}
	
	public void gerarIndicadores(List<EquipeServico> equipesServico, Mes mes) {
		this.prop.put("mes", mes.getMes().name());
		
		List<IndicadoresDTO> indicadores = new ArrayList<IndicadoresDTO>();
		
		String textoEspecial = null;
		
		for (EquipeServico equipeServico : equipesServico) {
			IndicadoresDTO dto = new IndicadoresDTO();
			
			dto.setData(equipeServico.getDiaReuniao().getDia());
			dto.setIndicadores(this.nomeAbreviado(equipeServico.getIndcador1()), this.nomeAbreviado(equipeServico.getIndcador2()), this.nomeAbreviado(equipeServico.getIndcador3()));
			dto.setVolantes(this.nomeAbreviado(equipeServico.getVolante1()), this.nomeAbreviado(equipeServico.getVolante2()));
			
			if (TipoDia.ASSEMBLEIA == equipeServico.getDiaReuniao().getTipoDia()) {
				if (textoEspecial == null) {
					textoEspecial = especialText(equipeServico.getDiaReuniao());
				}
				
				dto.setTpAssembleia(textoEspecial);
			}
			
			indicadores.add(dto);
		}
		
		this.gerarRelatorio("Indicadores", new JRBeanCollectionDataSource(indicadores));
	}
	
	public void gerarDiscursos(List<Discurso> discursos, Mes mes) {
		this.prop.put("mes", mes.getMes().name());
		
		String tpEspecial = null;
		
		List<DiscursoDTO> discursosDTO = new ArrayList<DiscursoDTO>();
		
		for (Discurso discurso : discursos) {
			DiscursoDTO dto = new DiscursoDTO();
			dto.setCidade(discurso.getCidade());
			dto.setCongregacao(discurso.getCongregacao());
			dto.setOrador(this.nomeAbreviado(discurso.getOrador()));
			dto.setTema(discurso.getTema());
			dto.setTipo(discurso.getTipo());
			dto.setData(discurso.getDiaReuniao().getDia());
			
			if (TipoDia.ASSEMBLEIA == discurso.getDiaReuniao().getTipoDia()) {
				if (tpEspecial == null) {
					tpEspecial = especialText(discurso.getDiaReuniao());
				}
				
				dto.setTpAssembleia(tpEspecial);
				
			} else if (TipoDia.VISITA == discurso.getDiaReuniao().getTipoDia()) {
				dto.setVisita(true);
			}
			
			discursosDTO.add(dto);
		}
		
		this.gerarRelatorio("Orador", new JRBeanCollectionDataSource(discursosDTO));
	}
	
	public void gerarEscola(List<DesignacaoEscola> designacaoesEscola, Mes mes, String sala, List<VidaMinisterio> vidaMinisterios) {
		this.prop.put("mes", mes.getMes().name());
		
		List<DesignacaoEscolaDTO> designacaoes = new ArrayList<DesignacaoEscolaDTO>();
		
		DesignacaoEscolaDTO dto = null;
		
		String tpEspecial = null;
		
		Collections.sort(mes.getDias());
		
		for (DiaReuniao dia : mes.getDias()) {
			if ("S".equalsIgnoreCase(dia.getQuando()) && (TipoDia.ASSEMBLEIA == dia.getTipoDia() || TipoDia.RECAPITULACAO == dia.getTipoDia() || TipoDia.VISITA == dia.getTipoDia() || TipoDia.VIDEOS == dia.getTipoDia())) {
				dto = this.criaDesignacaoEscola(dia, sala, vidaMinisterios);
				
				if (TipoDia.ASSEMBLEIA == dia.getTipoDia()) {
					if (tpEspecial == null) {
						tpEspecial = especialText(dia);
					}
					
					dto.setTpAssembleia(tpEspecial);
				
				} else if (TipoDia.RECAPITULACAO == dia.getTipoDia()  && "B".equals(sala)) {
					dto.setRecapitulacao(true);
				
				} else if (TipoDia.VISITA == dia.getTipoDia()) {
					dto.setVisita(true);
				
				} else if (TipoDia.VIDEOS == dia.getTipoDia() && "B".equals(sala)) {
					dto.setVideos(true);
				}
				
				designacaoes.add(dto);
			}
			
			for (DesignacaoEscola designacaoEscola : designacaoesEscola) {
				if (designacaoEscola.getDia().equals(dia)) {
					if (dto == null || !dto.getDataReuniao().equals(designacaoEscola.getDia().getDia())) {
						dto = this.criaDesignacaoEscola(dia, sala, vidaMinisterios);
					}
					
					if (designacaoEscola.getNumero() == 1) {
						dto.setLeitura(designacaoEscola.getTema());
						dto.setLeitor(this.nomeAbreviado(designacaoEscola.getEstudante()));
						dto.setVideos(TipoDia.VIDEOS == dia.getTipoDia());
					
					} else if (designacaoEscola.getNumero() == 2) {
						dto.setEstVisita(this.nomeAbreviado(designacaoEscola.getEstudante()));
						dto.setAjuVisita(this.nomeAbreviado(designacaoEscola.getAjudante()));
					
					} else if (designacaoEscola.getNumero() == 3) {
						dto.setEstRevisita(this.nomeAbreviado(designacaoEscola.getEstudante()));
						dto.setAjuRevisita(this.nomeAbreviado(designacaoEscola.getAjudante()));
					
					} else if (designacaoEscola.getNumero() == 4) {
						dto.setEstEstudo(this.nomeAbreviado(designacaoEscola.getEstudante()));
						dto.setAjuEstudo(this.nomeAbreviado(designacaoEscola.getAjudante()));
					}
					
					if (!designacaoes.contains(dto)) {
						designacaoes.add(dto);
					}
				}
			}
		}
		
		if ("A".equals(sala)) {
			this.gerarRelatorio("EscolaA", new JRBeanCollectionDataSource(designacaoes));
		
		} else {
			this.gerarRelatorio("EscolaB", new JRBeanCollectionDataSource(designacaoes));
		}
	}
	
	public void gerarServico(List<Servico> servicos, Mes mes) {
		this.prop.put("mes", mes.getMes().name());
		
		List<ServicoDTO> servicosDTO = new ArrayList<ServicoDTO>();
		
		for (Servico servico : servicos) {
			if (servico == null) {
				continue;
			}
			
			ServicoDTO dto = null;

			if (TipoDia.ASSEMBLEIA  == servico.getDiaReuniao().getTipoDia()) {
				dto = new ServicoDTO();
				dto.setDataReuniao(servico.getDiaReuniao().getDia());
				dto.setTpAssembleia(especialText(servico.getDiaReuniao()));
				
				servicosDTO.add(dto);
			
			} else if (TipoDia.VISITA == servico.getDiaReuniao().getTipoDia()) { 
				dto = new ServicoDTO();
				dto.setDataReuniao(servico.getDiaReuniao().getDia());
				dto.setVisita(true);
				
				servicosDTO.add(dto);
				
			} else {
				Collections.sort(servico.getDesignacoes());
				
				for (DesignacaoServico designacao : servico.getDesignacoes()) {
					dto = new ServicoDTO();
					
					dto.setDataReuniao(servico.getDiaReuniao().getDia());
					dto.setOrador(this.nomeAbreviado(designacao.getOrador()));
					dto.setTema(designacao.getTema());
					dto.setTempo(designacao.getMinutos());
					dto.setOracao(this.nomeAbreviado(servico.getOracao()));
					
					//fixos por dia
					dto.setOracaoIni(servico.getOracInicial());
					dto.setPresidente(servico.getPresidente());
					dto.setTemaDiscurso(servico.getTemaDisc());
					dto.setOradorDiscruso(servico.getOrador());
					dto.setOradorJoias(servico.getJoias());
	
					servicosDTO.add(dto);
				}
			}
		}
		
		this.gerarRelatorio("Servico", new JRBeanCollectionDataSource(servicosDTO));
	}
	
	public void gerarTesteToPrint(SemanaVisita visita) {
		List<SemanaVisita> visitas = new ArrayList<SemanaVisita>();
		visitas.add(visita);
		
		this.reportManager.createReport("cartaoPublicador", this.prop, new JRBeanCollectionDataSource(visitas));
		this.reportManager.toPrint();
	}
	
	public void gerarVisita(SemanaVisita visita) {
		if (visita != null) {
			VisitaDTO dto = new VisitaDTO();
			
			for (DesignacaoEscola designacaoEscola : visita.getDesignacoesEscola()) {
				if (designacaoEscola.getNumero() == 0) {
					dto.setLeitura(designacaoEscola.getTema());
					dto.setLeitor(this.nomeAbreviado(designacaoEscola.getEstudante()));
				
				} else if (designacaoEscola.getNumero() == 1) {
					dto.setTema1(designacaoEscola.getTema());
					dto.setEstudante1(this.nomeAbreviado(designacaoEscola.getEstudante()));
				
				} else if (designacaoEscola.getNumero() == 2) {
					dto.setTema2(designacaoEscola.getTema());
					dto.setEstudante2(this.nomeAbreviado(designacaoEscola.getEstudante()));
					dto.setAjudante1(this.nomeAbreviado(designacaoEscola.getAjudante()));
				
				} else if (designacaoEscola.getNumero() == 3) {
					dto.setTema3(designacaoEscola.getTema());
					dto.setEstudante3(this.nomeAbreviado(designacaoEscola.getEstudante()));
					dto.setAjudante2(this.nomeAbreviado(designacaoEscola.getAjudante()));
				}
			}
			
			List<VisitaServicoDTO> servicos = new ArrayList<VisitaServicoDTO>();
			dto.setServico(servicos);
			
			Servico servico = visita.getServico();
			Collections.sort(servico.getDesignacoes());
			
			for (DesignacaoServico designacao : servico.getDesignacoes()) {
				VisitaServicoDTO dtoServ = new VisitaServicoDTO();
				dtoServ.setOrador(this.nomeAbreviado(designacao.getOrador()));
				dtoServ.setTema(designacao.getTema());
				dtoServ.setTempo(designacao.getMinutos());
				dtoServ.setCantico(servico.getCantico());

				servicos.add(dtoServ);
			}
			
			try {
				this.prop.put("SUBREPORT", this.reportManager.compile("SubVisitaServico"));
				
			} catch (JRException ex) {
				String erro = "Não foi possivel gerar o relatório.";
				
				log.error(erro, ex);
				JOptionPane.showMessageDialog(null, erro, "Erro", JOptionPane.ERROR_MESSAGE);
			}

			dto.setDiaSemana(visita.getDiaSemana().getDia());
			dto.setDiaFimSemana(visita.getDiaFimSemana().getDia());
			dto.setPrimeiroDiscurso(visita.getPrimeiroDiscurso());
			dto.setSegundoDiscurso(visita.getSegundoDiscurso());
			dto.setTerceiroDiscurso(visita.getTerceiroDiscurso());
			dto.setOracaoServico(visita.getOracaoServico());
			dto.setOracaoPublica(visita.getOracaoPublica());
			dto.setCanticoFimServico(visita.getCanticoFimServico());
			dto.setCanticoIniPublica(visita.getCanticoIniPublica());
			dto.setCanticoMeioPublica(visita.getCanticoMeioPublica());
			dto.setCanticoFimPublica(visita.getCanticoFimPublica());
			dto.setPresidente(visita.getPresidente());
			dto.setDirigenteSentinela(visita.getDirigenteSentinela());
			
			VisitaDTO[] visitas = {dto};
			
			this.gerarRelatorio("Visita", new JRBeanArrayDataSource(visitas));
		}
	}
	
	private void gerarRelatorio(String reportName, JRDataSource beanDataSource) {
		OutputStream out = null;
		
		try {
			this.reportManager.createReport(reportName, this.prop, beanDataSource);
			
			InputStream in = this.reportManager.toPDF();
			
			File tempFile = File.createTempFile(reportName, ".pdf");
		    tempFile.deleteOnExit();
			
			out = new FileOutputStream(tempFile);
			
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			Desktop.getDesktop().open(tempFile);
			
		} catch (Exception ex) {
			String erro = "Não foi possivel gerar o relatório.";
			
			log.error(erro, ex);
			JOptionPane.showMessageDialog(null, erro, "Erro", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}
	
	private String especialText(DiaReuniao diaReuniao) {
		return diaReuniao.getDescricao();
	}
	
	private String nomeAbreviado(String nome) {
		String abreviacao = this.abreviacoes.get(nome);
		
		if (abreviacao == null && nome != null && nome.length() > 0) {
			abreviacao = this.gerenciador.abreviacaoPessoa(nome);
			this.abreviacoes.put(nome, abreviacao);
		}
		
		return abreviacao;
	}
	
	private DesignacaoEscolaDTO criaDesignacaoEscola(DiaReuniao diaReuniao, String sala, List<VidaMinisterio> vidaMinisterios) {
		DesignacaoEscolaDTO dto = new DesignacaoEscolaDTO();
		dto.setDataReuniao(diaReuniao.getDia());
		
		if ("A".equalsIgnoreCase(sala)) {
			VidaMinisterio vidaMinisterio = vidaMinisterios.stream().filter(v->v.getDia().equals(diaReuniao)).findFirst().orElse(null);
			
			dto.setDesigVideos(vidaMinisterio.getDesgApresentacao());
		}
		
		return dto;
	}
}