# Projeto de Prática de Programação

Projeto desenvolvido para a disiciplina de Prática de Programação(INF1124) da Universidade Católica de Pernambuco(UNICAP).

**Exercício:** Desenvolver um sistema de cadastro bancário

**Especificação:**

O Banco Dinheiro Certo deseja automatizar o cadastro de contas de seus clientes. No cadastro deve constar as seguintes
informações: número da conta (string) o tipo da conta (1 - conta corrente simples ou 2 - conta corrente especial). saldo
(float) e o cpf (string) do cliente. Faça um programa para criar e manipular o cadastro de conta da banco. Em memória, o
cadastro de contas deve ser implementado utilizando uma árvore AVL. Na árvore, as contas devem estar ordenadas por
número. O programa deve conter os seguintes subprogramas:

(a) Uma função de busca. Esta função irá receber como parâmetro o número da conta procurada e irá retornar a
posição no cadastro onde a conta foi encontrada. Caso o cadastro esteja vazio ou caso não encontre o valor
procurado, a função deve retornar null:

(b) Um procedimento para cadastrar una nova conta. Este procedimento irá receber como parâmetro o número da nova conta. O procedimento
deverá certificar-se que aquele número de conta não consta no cadastro. Para tal, ele irá utilizar a função definida
no item (a). Caso já conste, deve avisar ao usuário que não poderá fazer o cadastramento, pois se trata de um
número de conta repetido. Caso não conste, o procedimento deve prosseguir com o cadastramento, solicitando o
restante dos dados e inserindo a nova conta;

(c) Um procedimento para consultar o saldo de uma conta. Este procedimento irá receber como parâmetro o
número da conta a ser consultada. Fará uma busca utilizando a função definida no item (a). Se a conta existir no
cadastro, o procedimento deverá informar seu saldo; caso contrário, o procedimento deverá informar que a conta
não está cadastrada

(d) Um procedimento para fazer um depósito em uma conta. Este procedimento irá receber como parâmetro o
número da conta onde será efetuado o depósito. Fará uma busca utilizando a função definida no item (a). Se a
conta existir no cadastro, o procedimento deverá solicitar o valor e efetuar o depósito: caso contrário, o
procedimento deverá informar que a conta não está cadastrada.

(e) Um procedimento para fazer um saque em uma conta. Este procedimento irá receber como parámetro o número
da conta onde será efetuado o saque. Fará uma busca utilizando a função definida no item (a). Se a conta não
existir no cadastro, o procedimento deverá informar que a conta não está cadastrada. Se a conta existir, o
procedimento deverá solicitar o valor e, caso haja saldo suficiente, efetuar o saque; caso contrário, o procedimento
deverá verificar se é uma conta especial. Toda conta especial tem, a principio, R\$2.000,00 (dois mil reais) de
crédito. Se o cliente tiver crédito, realizar o saque. Caso contrário, informar que não existe saldo suficiente.

(f) Um procedimento para exibir o número da conta, o nome do titular e o telefone para contato de todos os clientes
do banco. A exibição deverá ocorrer em ordem alfabética crescente dos cpf's dos titulares das contas. (\*\*)

(g) Um procedimento para remover uma conta. Este procedimento irá receber como parimetro o número da conta.
Fará uma busca utilizando a função definida no item (a). Se a conta não existir no cadastro, o procedimento
deverá informar que a conta não está cadastradoa Se a conta existir, o procedimento deverá verificar o saldo dela
Apenas contas com saldo ZERO poderão ser removidas. Se o saldo não for zero, o procedimento deverá informar
que a conta não pode ser removida e informar seu saldo atual.

(h) Um procedimento para, dado um número de CPF, exibir o número das contas existentes que tenham sido
cadastradas com aquele CPF, juntamente com o número de cada conta, exibir também o tipo dela, ou seja, se é
conta corrente simples ou conta corrente especial.

(\*\*) Implemente o cadastro dos clientes. Em memória o cadastro deve ser implementado utilizando uma árvore avl. Sobre
cada cliente devem ser armazenadas as seguintes informações: cpf, nome, e-mail e telefone. A árvore deve ser ordenada
por cpf. Implemente para este cadastro as seguintes funções: cadastrar um cliente, remover um cliente (não remover
cliente que tenha conta cadastrada no cadastro de contas), consultar os dados de um cliente e alterar os dados de um
cliente.

Ao final do programa, os cadastros de contas e de clientes devem ser salvos em arquivos sequenciais. Sempre que
reiniciar o programa. As árvores representando os cadastros devem ser reconstruidas a partir dos respectivos arquivos.
