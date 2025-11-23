use menu;



drop table if exists itemCardapio;
create table itemCardapio(
	id bigint primary key auto_increment,
    nome varchar(100) not null,
    descricao varchar(1000),
    categoria enum('ENTRADAS', 'PRATOS_PRINCIPAIS', 'BEBIDAS', 'SOBREMESAS') not null,
    preco decimal(9,2) not null,
    preco_promocional decimal(9,2)
); 

select count(*) from itemCardapio;
select * from itemCardapio;

INSERT INTO itemCardapio (nome, descricao, categoria, preco, preco_promocional)
VALUES
    ('Sanduíche de Presunto do Chaves', 'Sanduíche de presunto simples, mas feito com muito amor.', 'PRATOS_PRINCIPAIS', 3.50, 2.99),
    ('Torta de Frango da Dona Florinda', 'Torta de frango com recheio cremoso e massa crocante.', 'PRATOS_PRINCIPAIS', 12.99, 10.99),
    ('Pipoca do Quico', 'Balde de pipoca preparado com carinho pelo Quico.', 'PRATOS_PRINCIPAIS', 4.99, 3.99),
    ('Água de Jamaica', 'Água aromatizada com hibisco e toque de açúcar', 'BEBIDAS', 2.50, 2.00),
    ('Churros do Chaves', 'Churros recheados com doce de leite, clássicos e irresistíveis.', 'SOBREMESAS', 4.99, 3.99);

        
        