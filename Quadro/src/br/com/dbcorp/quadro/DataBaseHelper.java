package br.com.dbcorp.quadro;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.dbcorp.quadro.ui.Params;

public class DataBaseHelper {

	private static EntityManagerFactory fac;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	private DataBaseHelper() {
	}
	
	private static Properties getInitialConf() {
		Properties props = new Properties();
		
		try {
			props.load(Params.iniLoad());
			
			for(Object key : props.keySet()) {
				System.out.println("chave: " + key + " valor: " + props.get(key));
			}

		} catch (Exception e) {
			Log.getInstance().error(e);
		} 
		
		return props;
	}
	
	private static void inicialize(boolean withTx) {
		if (DataBaseHelper.fac == null) {
			DataBaseHelper.fac = Persistence.createEntityManagerFactory("quadro", getInitialConf());
		}
		
		if (DataBaseHelper.em == null) {
			DataBaseHelper.em = DataBaseHelper.fac.createEntityManager();
		}
		
		if (withTx) {
			DataBaseHelper.tx = em.getTransaction();
		}
	}
	
	public static <T> void persist(T entity) {
		DataBaseHelper.inicialize(true);
		
		DataBaseHelper.tx.begin();
		DataBaseHelper.em.persist(entity);
		DataBaseHelper.tx.commit();
	}
	
	public static <T> T merge(T entity) {
		DataBaseHelper.inicialize(true);
		
		DataBaseHelper.tx.begin();
		entity = DataBaseHelper.em.merge(entity);
		DataBaseHelper.tx.commit();
		
		return entity;
	}
	
	public static <T> void remove(T entity) {
		DataBaseHelper.inicialize(true);
		
		DataBaseHelper.tx.begin();
		DataBaseHelper.em.remove(DataBaseHelper.em.merge(entity));
		DataBaseHelper.tx.commit();
	}
	
	public static <T> void removeWithOutTX(T entity) {
		DataBaseHelper.em.remove(DataBaseHelper.em.merge(entity));
	}
	
	public static <T> void refresh(T entity) {
		DataBaseHelper.inicialize(true);
		
		DataBaseHelper.tx.begin();
		DataBaseHelper.em.refresh(entity);
		DataBaseHelper.tx.commit();
	}
	
	public static <T> T find(Class<T> entityClass, Object id) {
		DataBaseHelper.inicialize(false);
		
		return DataBaseHelper.em.find(entityClass, id);
	}
	
	public static Query createQuery(String sql) {
		DataBaseHelper.inicialize(false);
		
		return DataBaseHelper.em.createQuery(sql);
	}
	
	public static EntityTransaction obterTransacao() {
		DataBaseHelper.inicialize(true);
		
		return DataBaseHelper.tx;
	}
}
