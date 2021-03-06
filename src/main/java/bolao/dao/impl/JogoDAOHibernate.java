package bolao.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import bolao.dao.JogoDAO;
import bolao.model.Jogo;

@Repository("jogoDAO")
public class JogoDAOHibernate extends GenericHibernate<Jogo> implements JogoDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9184750567168143668L;

	@Override
	protected Class<Jogo> getEntityClass() {
		return Jogo.class;
	}

	@Override
	public List<Date> datasJogos() {
		String hql = "SELECT DISTINCT(CAST(j.dataJogo AS date)) FROM Jogo j "
				+ "ORDER BY CAST(j.dataJogo AS date)";
		
		Query consulta = super.getSession().createQuery(hql);
		
		return consulta.list();
	}

	@Override
	public void merge(Jogo jogo) {
		getSession().merge(jogo);		
	}

	@Override
	public List<Jogo> buscaTodosJogosComPlacar() {
		String hql = "FROM Jogo j "
				+ "WHERE (j.placarCasa is not null) and (j.placarVisitante is not null) "
				+ "ORDER BY j.numeroJogo DESC";
		
		Query consulta = super.getSession().createQuery(hql);
		
		return consulta.list();
	}

	@Override
	public List<Jogo> listaTodosOrderByNumeroJogo() {
		String hql = "FROM Jogo j "
				+ "ORDER BY j.placarCasa DESC, j.numeroJogo";
		
		Query consulta = super.getSession().createQuery(hql);
		
		return consulta.list();
	}
	
	@Override
	public List<Jogo> listaJogosExibiveis() {
		String hql = "FROM Jogo j WHERE "
				+ "extract(epoch from j.dataJogo - current_timestamp) < 3600 "
				+ "ORDER BY j.numeroJogo DESC";
		
		Query consulta = super.getSession().createQuery(hql);
		
		return consulta.list();
	}
}