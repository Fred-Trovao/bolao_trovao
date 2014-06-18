package bolao.dao;

import java.util.List;

import bolao.model.Aposta;

public interface ApostaDAO extends DAOBase<Aposta>{
	List<Aposta> buscaApostasPorUsuario(Long id_usuario);
	List<Aposta> buscaApostasUsuarioComPontuacao(Long id_usuario);
	List<Aposta> buscaAPostasPorJogo(Integer id_jogo);
	List<Aposta> buscaApostasUsuarioExibPorNumJogo(Long id_usuario);
}
