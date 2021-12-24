import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.text.MaskFormatter;


@Entity
@Table(name = "CGO_NATUREZA_RECEITA")
@Cacheable
public class NaturezaReceita implements Serializable {

	private static final long serialVersionUID = 1013111222239789554L;

	@Id
	@Column(name = "ID_NATUREZA_RECEITA", nullable = false)
	private Integer id;

	@Column(name = "CODG_NATUREZA_RECEITA", nullable = false)
	private Integer codigo;

	@Column(name = "NOME_NATUREZA_RECEITA", nullable = false)
	private String nome;

	// novas colunas para receita V2
	@Column(name = "INDI_RECEITA_DEDUCAO", nullable = true)
	private Integer indicaReceitaDeducao;
	
	@Column(name = "CODG_DETALHEM_GOIAS", nullable = true)
	private Integer codigoDetalheGoias;
	
	@Column(name = "INDI_VERSAO_EMENTARIO_STN", nullable = false)
	private Integer indicaVersaoEmentario = 1;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "ID_ORGAO_RECEITA")
    private OrgaoReceita orgaoReceita;
	
/* 	@ManyToMany(mappedBy = "naturezaReceitas", cascade = CascadeType.ALL)
	private List<Orgao> orgaos; */


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

	public Integer getIndicaReceitaDeducao() {
		return indicaReceitaDeducao;
	}

	public void setIndicaReceitaDeducao(Integer indicaReceitaDeducao) {
		this.indicaReceitaDeducao = indicaReceitaDeducao;
	}

	public Integer getCodigoDetalheGoias() {
		return codigoDetalheGoias;
	}

	public void setCodigoDetalheGoias(Integer codigoDetalheGoias) {
		this.codigoDetalheGoias = codigoDetalheGoias;
	}

	public Integer getIndicaVersaoEmentario() {
		return indicaVersaoEmentario;
	}

	public void setIndicaVersaoEmentario(Integer indicaVersaoEmentario) {
		this.indicaVersaoEmentario = indicaVersaoEmentario;
	}

	public String getCodigoNaturezaReceitaFormatada() {
		Integer cod = getCodigo();
		if (cod == null)
			return "";
		String codigoRec = cod.toString();
		try {
			String mascara = "#.#.#.#.##.##";

			if (codigoRec.length() == 9) {
				mascara = "##.#.#.#.##.##";
			}

			if (indicaVersaoEmentario != null && (indicaVersaoEmentario == 2 || indicaVersaoEmentario == 3)) {
				StringBuilder sb = new StringBuilder();
				sb.append(codigoRec);
				sb.append(indicaReceitaDeducao);
				String formatted = String.format("%03d", codigoDetalheGoias);
				sb.append(formatted);
				codigoRec = sb.toString();
				mascara = "#.#.#.#.##.#.#.####";
			}
			MaskFormatter mf = new MaskFormatter(mascara);
			mf.setValueContainsLiteralCharacters(false);

			StringBuffer sb = new StringBuffer(codigoRec);
			return mf.valueToString(sb);
		} catch (ParseException e) {
			return codigoRec;
		}
	}

}
