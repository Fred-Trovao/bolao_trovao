package bolao.apresentacao.mb;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bolao.model.Aposta;
import bolao.services.IApostaService;
import bolao.util.MessagesProperty;

@ManagedBean(name="apostaMB")
@ViewScoped
public class ApostaMB extends MB implements Serializable{
	
	private static final long serialVersionUID = -3719644829272321357L;
	@ManagedProperty(value="#{apostaService}")
	private IApostaService apostaService;
	private List<Aposta> apostas;
	
	@PostConstruct
	public void init(){
		try{
		this.apostas = this.apostaService
				.buscaApostasPorUsuario(getUsuarioLogado());
		}catch(Exception e){
			e.printStackTrace();
			MessagesProperty.errorMsg("MN0007");
		}
		
		for(Aposta a : this.apostas)
			System.out.println("<img class=\"escudos " + a.getJogo().getClubeCasa().getEscudo() + " \"\\>");
	}

	public IApostaService getApostaService() {
		return apostaService;
	}

	public void setApostaService(IApostaService apostaService) {
		this.apostaService = apostaService;
	}

	public List<Aposta> getApostas() {
		return apostas;
	}

	public void setApostas(List<Aposta> apostas) {
		this.apostas = apostas;
	}
}