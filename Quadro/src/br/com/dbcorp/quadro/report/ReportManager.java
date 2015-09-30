package br.com.dbcorp.quadro.report;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import br.com.dbcorp.quadro.Log;

public class ReportManager {
	
	private Log log = Log.getInstance();
	
	private JasperPrint print;
	
	public void createReport(String fileName, Properties parms, JRDataSource datasource) {
		try {
			parms.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
			
			JasperReport report = this.compile(fileName);
			
			if (datasource != null) {
				this.print = JasperFillManager.fillReport(report, this.processaParametros(parms), datasource);
				
			} else {
				this.print = JasperFillManager.fillReport(report, this.processaParametros(parms), new JREmptyDataSource());
			}
			
		} catch (JRException e) {
			String erro = "Não pode compilar arquivo de relatorio!";
			
			log.error(erro, e);
			throw new RuntimeException(erro);
		}
	}
	
	public JasperReport compile(String fileName) throws JRException {
		InputStream input = ReportManager.class.getClassLoader().getResourceAsStream("report/" +  fileName + ".jrxml");
		
		if (input == null) {
			input = ReportManager.class.getClassLoader().getResourceAsStream("resources/report/" +  fileName + ".jrxml");
		}
		
		if (input == null) {
			input = ReportManager.class.getClassLoader().getResourceAsStream("/report/" +  fileName + ".jrxml");
		}
		
		if (input == null) {
			input = ReportManager.class.getClassLoader().getResourceAsStream("/resources/report/" +  fileName + ".jrxml");
		}
		
		return JasperCompileManager.compileReport(input);
	}
	
	public InputStream toPDF() {
		try {
			return new ByteArrayInputStream(JasperExportManager.exportReportToPdf(this.print));
			
		} catch (Exception e) {
			String erro = "Não gerar PDF do relatório";
			
			log.error(erro, e);
			throw new RuntimeException(erro);
		}
	}
	
	public void toPrint() {
		try {
			JasperPrintManager.printReport(print, true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Map<String, Object> processaParametros(Properties prop) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		for (Object key : prop.keySet()) {
			parametros.put((String)key, prop.get(key));
		}
		
		return parametros;		
	}
}
