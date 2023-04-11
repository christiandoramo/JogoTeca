<div  align="center">
    
 # Projeto JogoTeca
    
<div  align="left">

### Tópicos

:o: [Contribuidores](#contribuidores)

:o: [Descrição geral](#descrição-geral-pencil)

:o: [Requisitos e funcionalidades](#requisitos-e-funcionalidades)

:o: [Diagrama de Classes](#diagrama-de-classes)

:o: [Diagrama navegacional](#diagrama-navegacional)

:o: [Dicas de acesso](#dicas-de-acesso-arrow_forward)

</div>

## Contribuidores

| [<img src="https://avatars.githubusercontent.com/u/116567019?v=4" width=115><br><sub>Caio Fontes</sub>](https://github.com/caio10012)                                      | [<img src="https://avatars.githubusercontent.com/u/116025325?v=4" width=115><br><sub>Christian Oliveira</sub>](https://github.com/christiandoramo)                               | [<img src="https://avatars.githubusercontent.com/u/118299886?v=4" width=115><br><sub>Giovanni Lima</sub>](https://github.com/Giovanni-LN)                                                                   | [<img src="https://avatars.githubusercontent.com/u/111536765?v=4" width=115><br><sub>Lucas Gabriel</sub>](https://github.com/luganasc)                                     |
| -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [![Gmail Badge](https://img.shields.io/badge/-caiofontes183@gmail.com-c14438?style=social&logo=gmail&link=mailto:caiofontes183@gmail.com)](mailto:caiofontes183@gmail.com) | [![Gmail Badge](https://img.shields.io/badge/-christiandoramo@gmail.com-c14438?style=social&logo=gmail&link=mailto:christiandoramo@gmail.com)](mailto:christiandoramo@gmail.com) | [![Gmail Badge](https://img.shields.io/badge/-giovanni.lima.nascimento@gmail.com-c14438?style=social&logo=gmail&link=mailto:giovanni.lima.nascimento@gmail.com)](mailto:giovanni.lima.nascimento@gmail.com) | [![Gmail Badge](https://img.shields.io/badge/-lucas.gabrieln@ufrpe.br-c14438?style=social&logo=gmail&link=mailto:lucas.gabrieln@ufrpe.br)](mailto:lucas.gabrieln@ufrpe.br) |

## Descrição geral :pencil:

<div  align="left">

O Projeto JogoTeca tem como principal a funcionalidade a venda de jogos, visando o público Gamer e Entusiastas do meio. Para o funcionamento do sistema, são necessárias funções essenciais como a inserção, busca, atualização e remoção de jogos, e consulta das vendas e dos Usuários por parte da admnistração. Enquanto o Usuário, deve poder navegar entre os jogos, ver o seu próprio perfil e o perfil do jogo, ter uma lista de desejos para comprar tudo a hora que quiser, realizar o pedido de Compra, fazer o pagamento, poder ver os jogos adquiridos, o historico de compras e seus comprovantes de compra. Todos essas funcionalidades devem estar presente no estado final no projeto (sprint 5) com interface gráfica funcional, armazenamento e leitura de arquivos.

</div>

## Requisitos e funcionalidades

<div  align="left">

:pushpin: **REQ1** - Inclusão, alteração, busca e remoção de jogos pela admnistração.

:pushpin: **REQ2** - Deve haver diferentes logins e senhas de acesso para o administrador e os usuários.

:pushpin: **REQ3** - Permitir a compra de produtos para os usuários.

:pushpin: **REQ4** - Retornar a quantidade de copias digitais vendidas de cada jogo.

:pushpin: **REQ5** - Consulta dos dados dos usuários pela admnistração.

:pushpin: **REQ6** - Armazenamento dos dados relativos aos usuários, vendas e jogos.

:pushpin: **REQ7** - O usuário pode ter uma forma de abstração de carrinho.

:pushpin: **REQ8** - O Sistema deve permitir que o cliente possa consultar as suas informações.

</div>

<br>

## Diagrama de Classes

<img width="683px" src="https://i.pinimg.com/originals/5a/44/7e/5a447e30d6acb8ad0f31407b6f872df9.png" alt="UML">

## Diagrama navegacional

<img width="683px" src="https://i.pinimg.com/originals/4f/5f/72/4f5f72be3f48245654670b1e56dc74c3.png"  alt="Modelo Navegacional">

## Dicas de Acesso :arrow_forward:

### Versões usadas

<hr>
<div align="left">
<img src ="https://img.shields.io/static/v1?label=Java&message=8&color=blue&style=for-the-badge&logo=oracle"/>
<img src ="https://img.shields.io/static/v1?label=Java&message=17&color=blue&style=for-the-badge&logo=oracle"/>

<br>

<img src ="https://img.shields.io/static/v1?label=JAVAFX&message=8&color=blue&style=for-the-badge&logo=oracle"/>
<img src ="https://img.shields.io/static/v1?label=JavaFX&message=17&color=blue&style=for-the-badge&logo=oracle"/>

<br>

<img src="https://img.shields.io/static/v1?label=Eclipse%20Oxygen%202%20EE&message=2017&color=blueviolet&style=for-the-badge&logo=eclipseide"/>
<img src="https://img.shields.io/static/v1?label=Intellij%20Idea%20CE&message=2023&color=blueviolet&style=for-the-badge&logo=eclipseide"/>

<br>
</div>

### Para acessar o projeto no Eclipse:

<div  align="left">

```
requisitos recomendados:

Eclipse Java Oxygen 2 Enterprise Edition;
java 8.0.2;
javafx 8.0.2;
scenebuilder 8.5.0;
```

</div>
<hr>

```
após clonar o projeto, não abra o projeto/pasta "Jogoteca" no eclipse, quando abrir o projeto vá em abrir por pasta, abra somente a pasta "jogotecaEclipse" e seu único projeto !!!
```

<hr>

```
Instale o software efxclipse pelo caminho: help -> install new software -> work with https://download.eclipse.org/efxclipse/updates-released/3.5.0/site
```

<hr>

```
Após o download será necessário resetar o programa
```

<hr>

```
Vá em config -> java build path, e depois em java compiler, aplicando a ambos o java 8
```

<hr>

```
Caso seja necessário, adicione a library javafx sdk ao projeto
```

<hr>

```
Após fazer um git pull e as mudanças não forem feitas no eclipse, abra o porjeto num novo workspace
```

<br>

## Para acessar o projeto no Intellij:

```
requisitos recomendados:
Intellij Idea Community Edition 2023;
java 17.0.6;
javafx 17.0.6;
scenebuilder 19.0.0;
```

![alt](https://i.pinimg.com/originals/8f/5a/59/8f5a59ee6e148d99cfe18935c45b72b4.jpg)

<hr>

```
Durante a instalação lembrar de permitir que seja definido automaticamente as variables PATH, e quando chegar aqui só importar o projeto JogotecaIntellij
```

<hr>

![alt](https://i.pinimg.com/originals/f9/ab/0d/f9ab0d25c3f594bad8bf6f2f9090ed49.jpg)

<hr>

<hr>

```
clique com o botao direito no projeto va em open in module
settings
```

<hr>

![alt](https://i.pinimg.com/originals/f5/e9/3a/f5e93aac23c6c1003be6cb0bf7748bd3.jpg)

<hr>

```
Adicione no  +  os arquivos da pasta lib (menos src.zip) do javafx 17
```

<hr>

```
Para o funcionamento do mediaplayer vai precisar também adicionar em library esses dlls: glib-lite.dll, gstreamer-lite.dll, jfxmedia.dll.
```

<div  align="left">

### Tópicos

:o: [Contribuidores](#contribuidores)

:o: [Descrição geral](#descrição-geral-pencil)

:o: [Requisitos e funcionalidades](#requisitos-e-funcionalidades)

:o: [Diagrama de Classes](#diagrama-de-classes)

:o: [Diagrama navegacional](#diagrama-navegacional)

:o: [Dicas de acesso](#dicas-de-acesso-arrow_forward)

</div>

</div>
</div>
