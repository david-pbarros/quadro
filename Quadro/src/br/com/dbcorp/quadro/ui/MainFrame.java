package br.com.dbcorp.quadro.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.dbcorp.quadro.Log;
import br.com.dbcorp.quadro.action.Action;
import br.com.dbcorp.quadro.action.ContatoAction;
import br.com.dbcorp.quadro.action.DiscursoAction;
import br.com.dbcorp.quadro.action.EquipeServicoAction;
import br.com.dbcorp.quadro.action.EscolaAction;
import br.com.dbcorp.quadro.action.EstudoAction;
import br.com.dbcorp.quadro.action.LimpezaAction;
import br.com.dbcorp.quadro.action.MesAction;
import br.com.dbcorp.quadro.action.SentinelaAction;
import br.com.dbcorp.quadro.action.ServicoAction;
import br.com.dbcorp.quadro.action.VisitaAction;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -5174114958612066962L;

	private JDesktopPane desktop;
	
	public MainFrame(int width, int height, Splash splash) {
		this.desktop = new JDesktopPane();
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

		this.setMenu();
		getContentPane().add(desktop);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setTitle("Quadro de An\u00FAncios");
		setIconImage(Params.iconeAplicacao());
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		
		InternalUI inicial = new InicialUI();
		inicial.show();
		inicial.grabFocus();
		((javax.swing.plaf.basic.BasicInternalFrameUI) inicial.getUI()).setNorthPane(null);
		
		
		this.getDesktop().add(inicial);
		try {
			inicial.setMaximum(true);
			inicial.setSelected(true);
		} catch (Exception ex) {
			Log.getInstance().debug(ex);
		}
		
		this.addComponentListener(new ComponentAdapter() {
			MainFrame frame;
			
			private ComponentAdapter init(MainFrame frame){
				this.frame = frame;
		        return this;
		    }
			
			@Override
			public void componentResized(ComponentEvent event) {
				super.componentResized(event);
				Params.setDimensions(this.frame);
			}
			
			
		}.init(this));
		
		splash.setVisible(false);
	}
	
	@Override
	public void setVisible(boolean arg0) {
		super.setVisible(arg0);
		Params.setDimensions(this);
	}
	
	public JDesktopPane getDesktop() {
		return this.desktop;
	}
	
	private void setMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDesignacao = new JMenu("Designações");
		JMenu mnOpc = new JMenu("Opções");
		
		this.setMenuItem(mnOpc, new Action[]{new PessoaAction(this), new ContatoAction(this)});
		this.setMenuItem(mnDesignacao, new Action[]{new MesAction(this), new EquipeServicoAction(this), new EstudoAction(this), new EscolaAction(this), 
				new ServicoAction(this), new SentinelaAction(this), new DiscursoAction(this), new VisitaAction(this), new LimpezaAction(this)});
		
		menuBar.add(mnDesignacao);
		menuBar.add(mnOpc);
	}
	
	private void setMenuItem(JMenu menu, Action[] actions) {
		for (Action action : actions) {
			menu.add(new JMenuItem(action));
		}
	}
}
