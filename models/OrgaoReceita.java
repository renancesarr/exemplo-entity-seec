

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRP_ORGAO_RECEITA")
@Cacheable
public class OrgaoReceita implements Serializable {

	private static final long serialVersionUID = 4722038720103065297L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_ORGAO_RECEITA", nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="ID_ORGAO", nullable = false)
	private Orgao orgao;
	
	@ManyToOne
	@JoinColumn(name="ID_NATUREZA_RECEITA", nullable = false)
	private NaturezaReceita naturezaReceita;
	

	public OrgaoReceita() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Orgao getOrgao() {
		return orgao;
	}


	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}


	public NaturezaReceita getNaturezaReceita() {
		return naturezaReceita;
	}


	public void setNaturezaReceita(NaturezaReceita naturezaReceita) {
		this.naturezaReceita = naturezaReceita;
	}
	
}
