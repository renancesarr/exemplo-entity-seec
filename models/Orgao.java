import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ORGAOS")
@Cacheable
public class Orgao implements Serializable {

	private static final long serialVersionUID = -452753919474508768L;
	
	@Id
	@Column(name="ORGA_IDEN", nullable = false)
	private Integer id;
	
	@Column(name="ORGA_CODG", nullable = false)
	private Integer codigo;
	
	@Column(name="ORGA_NOME", nullable = false)
	private String nome;
	
	@Column(name="ORGA_SIGL", nullable = false)
	private String sigla;
	
	@Column(name="ORGA_DATA_INICIO_VIGENCIA", nullable = false)
	@JsonIgnore
	private LocalDate dataInicioVigencia;
	
	@Column(name="ORGA_DATA_FIM_VIGENCIA")
	@JsonIgnore
	private LocalDate dataFimVigencia;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "ID_ORGAO_RECEITA")
    private OrgaoReceita orgaoReceita;
	
/* 	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "PRP_PRP_ORGAO_RECEITA",
			joinColumns = @JoinColumn(name = "ID_ORGAO"),
			inverseJoinColumns = @JoinColumn(name = "ID_NATUREZA_RECEITA"))
	List<NaturezaReceita> naturezaReceitas; */

	public Orgao() {
		super();
	}

	public Orgao(Integer id, Integer codigo, String nome, String sigla, LocalDate dataInicioVigencia, LocalDate dataFimVigencia) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.sigla = sigla;
		this.dataInicioVigencia = dataInicioVigencia;
		this.dataFimVigencia = dataFimVigencia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public LocalDate getDataInicioVigencia() {
		return dataInicioVigencia;
	}

	public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}

	public LocalDate getDataFimVigencia() {
		return dataFimVigencia;
	}

	public void setDataFimVigencia(LocalDate dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, dataFimVigencia, dataInicioVigencia, id, nome, sigla);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orgao other = (Orgao) obj;
		return Objects.equals(codigo, other.codigo) 
				&& Objects.equals(dataFimVigencia, other.dataFimVigencia)
				&& Objects.equals(dataInicioVigencia, other.dataInicioVigencia) 
				&& Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) 
				&& Objects.equals(sigla, other.sigla);
	}

	
}