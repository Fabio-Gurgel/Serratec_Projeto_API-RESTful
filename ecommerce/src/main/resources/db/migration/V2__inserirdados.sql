INSERT INTO public.categoria (nome,descricao) VALUES
	 ('Alimentos','Comidas e bebidas'),
	 ('Limpeza','Produtos de limpeza em geral');

INSERT INTO public.produto (nome,descricao,qtd_estoque,data_cadastro,valor_unitario,id_categoria) VALUES
	 ('Arroz','Arroz Integral 5kg',100,'2022-10-20',20.99,2),
	 ('Detergente','Detergente Ypê',25,'2022-01-01',5.4,1);


INSERT INTO public.endereco (cep,logradouro,bairro,localidade,numero,complemento,uf) VALUES
	 ('11111111','Rua A','Bairro B','Cidade C','1',NULL,'RJ'),
	 ('22222222','Rua B','Bairro C','Cidade D','2',NULL,'SP');

	
INSERT INTO public.cliente (nome_completo,email,cpf,telefone,data_nascimento,id_endereco) VALUES
	 ('Joao Silva','joao@test.com','11111111111','21999999999','2000-01-01',1),
	 ('Maria Silva','maria@test.com','22222222222','21000000000','1900-01-01',2);

INSERT INTO public.pedido (data_pedido,data_entrega,data_envio,status,valor_total,id_cliente) VALUES
	 ('2022-10-20','2022-10-20','2022-10-20','0',150.0,1),
	 ('2022-10-01','2022-10-05','2022-10-05','1',50.99,2);

	
INSERT INTO public.item_pedido (quantidade,preco_venda,percentual_desconto,valor_bruto,valor_liquido,id_produto,id_pedido) VALUES
	 (2,20.99,0.0,42.2,42.0,1,1),
	 (1,5.4,10.0,5.1,5.0,2,2);

	