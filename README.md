# Projeto JogoTeca

## Integrantes do grupo

- Caio Fontes de Melo - caiofontes183@gmail.com
- Christian Oliveira do Ramo - christiandoramo@gmail.com
- Giovanni Lima do Nascimento - giovanni.lima.nascimento@gmail.com
- Lucas Gabriel Albuquerque do Nascimento - lucas.gabrieln@ufrpe.br

## Descrição geral do projeto

O Projeto JogoTeca tem como principal a funcionalidade a comercialização de jogos, visando o publico Gamer e Entusiastas do meio, fornecendo funções essenciais como: Venda, Compra e Aluguel de jogos, além de possibilitar outras funções ao usuário, como lista espera, desejos, favoritos e entre outras.

## Requisitos do projeto

- - **REQ1** - O sistema deve permitir a inclusão,alteração e remoção das diversas categorias de compradores.
- - **REQ2** - O sistema deve possuir senha de acesso e identificação para diferentes tipos de usuários:administrador e funcionários
- - **REQ3**- O sistema deve permitir o registro de vendas de produtos para os clientes previamente cadastrados.
- - **REQ4**- O sistema deve gerenciar a quantidade de jogos em estoque, notificando o administrado quando a quantidade em estoque estiver baixa.
- - **REQ5**- O sistema deve permitir a inclusão, alteração e remoção de funcionários da jogoTeca, com os seguintes atributos: nome, endereço,email, telefone,senha e data de nascimento.
- - **REQ6**- O sistema deve permitir o empréstimo de um jogo por um comprador que deseje aluga-lo.
- - **REQ7**- O sistema deve permitir a emissão de um relatório para que se faça a consulta dos jogos que estão disponiveis ou não.
- - **REQ8**- O sistema deve permitir que se faça uma análise dos juros,caso o jogo seja entregue com atraso.
- - **REQ9**- O sistema deve fornecer facilidades para a realização de backups dos arquivos do sistema.
- - **REQ10**- O sistema deve permitir a troca de jogos por moedas que podem ser acumulados e negociadas por outros jogos.
- - **REQ11**- O sistema deve iniciar a impressão de relatórios solicitados dentro de no máximo 20 segundos após sua requisição.
- - **REQ12**- O sistema deve permitir ao usuário a função de lista de espera para jogos com estoque esgotado.
- - **REQ13**- O sistema deve permitir ao usuário a função de lista de desejos para jogos com pretensão de compra.
- - **REQ14**- O sistema deve permitir a impressão de um histórico de vendas.
- - **REQ15**- O sistema deve permitir ao usuário a opção de favoritar jogos de seu interesse.
- - **REQ16**- O sistema deve disponibilizar para que o usuário escolha entre cliente, funcionario e administrador.
- - **REQ17**- O Sistema deve permirtir que o cliente possa consultar as suas informações cadatradas.Caso alguma alteração seja necessária, o cliente deverá contatar algum funcionário.

<br><hr><hr>

<div align="center">

# Dicas de Acesso aos colaboradores

### Versões usadas

javaSE 8 ( jdk 8u211 - jre 1.8)
<br>
Eclipse IDE Oxygen 2 Enterprise Edition
javafx - já integrado em jdk 1.8
## (Usando a JRE 1.8 Não precisa seguir o tutorial abaixo sobre como habilitar o javafx, pois já vem incluso no jdk 1.8) 
<br>

obter recursos aqui: [Google Drive - Jogoteca](https://drive.google.com/drive/folders/17cEQQ40nq47XDg6v99tujSVtjEZ549l5?usp=share_link)

Exemplos/Templates para estudo: <br> [Sistemas Bancários - Leandro Marques](https://github.com/lmarques7/sistema_bancario) <br> [JavaFX CRUD MVC - Rafael Mesquita](https://www.youtube.com/playlist?list=PL-mvLy2ws8ILNrs8jtEAwaZMxDZvlMj48)

</div>

## Habilitando JavaFX com eclipse IDE 🚀

tutorial oficial do javafx com eclipse: [link](https://openjfx.io/openjfx-docs/#install-javafx)

## _Atenção!_

Use uma versão do eclipse anterior as de 2022 (como 2021-03), pois as mais recentes podem bugar na criação de arquivos fxml necessarios para o javafx </mark> <br>

Clickar na aba HELP -> install new software. Na janela já aberta coloque https://download.eclipse.org/efxclipse/updates-released/3.5.0/site/.<br><br>
Agora baixe o arquivo do javafx no site Gluon em uma pasta que voce se lembre.

No eclipse, clique no seu projeto e abra as propriedadess. Na aba library e depois selecione modulepath, crie uma biblioteca adicionando "external Jars", adicionando todos os jars que estão na pasta lib da pasta do javafx baixado. Agora basta selecionar essa livraria criada e dar o apply and close.

Caso ainda não funcione a execução de uma GUI do javafx no eclipse e apareça o erro

> "Error: Could not find or load main class application.Main
> Caused by: java.lang.NoClassDefFoundError: javafx/application/Application"

é necessario ir em run configurations do main do seu projeto (também na setinha ao lado do icone de run/play), e aplicar na aba arguments em VM arguments o seguinte: --module-path "O seu caminho absoluto até a pasta lib do javafx" --add-modules=javafx.fxml,javafx.controls

### Extra:

### para vizualizar as provas passadas do github do prof de IP2 Leandro<br>

<p>Para passar do erro com janela de alerta relativo a "...UTF16..." (<kbd>ou 'boolean com.ibm.icu.text.UTF16.isSurrogate(char)' não lembro no momento</kbd>)basta ir no eclipse em janela/window > prefecias/preferences > java > Editor > mark occurrences e desabilitar: mark occurrences<p>
