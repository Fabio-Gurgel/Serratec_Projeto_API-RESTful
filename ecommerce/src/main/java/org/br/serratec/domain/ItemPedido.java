package org.br.serratec.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "id_item_pedido")
	private Long id;
	
	@NotNull
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@NotNull
	@Column(name = "preco_venda")
	private Double precoVenda;
	
	@NotNull
	@Column(name = "percentual_desconto")
	private Double percentualDesconto;
	
	@NotNull
	@Column(name = "valor_bruto")
	private Double valorBruto;
	
	@NotNull
	@Column(name = "valor_liquido")
	private Double valorliquido;
	
	@NotNull
	@JoinColumn(name = "id_produto", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Produto produto;
	
	@NotNull
	@JoinColumn(name = "id_pedido", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Pedido pedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorliquido() {
		return valorliquido;
	}

	public void setValorliquido(Double valorliquido) {
		this.valorliquido = valorliquido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
}
