# DSW1-AA1
Atividade AA-1 da disciplina de Desenvolvimento de Software para Web 1
- Sistema para oferta de vagas de estágios/empregos <br/>

# Professor responsável
- Delano Medeiros Beder <br/>

# Integrantes do Grupo
- 759375 Luís Felipe Corrêa Ortolan
- 758597 Roseval Donisete Malaquias Junior

# Roteiro de Execução
1- Instalar, inicializar e popular o banco de dados Mysql
- Os integrantes deste grupo se basearem neste tutorial para realizar as configurações necessarias em um ambiente linux:
https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04-pt
- O Mysql server é inicializado e populado com o seguintes comandos, segundo as configurações utilizadas no arquivo GenericDAO.java do sistema:
<pre><code>$ cd AA1
$ mysql -u root -p
$ root
mysql> source ./db/MySql/create.sql
</pre></code>
- ** OBS **: Usuário e senha a serem utilizados pela aplicação podem ser facilmente alterados. No arquivo GenericDAO.java do projeto é necessário alterar a linha 17, colocando o usuário e senha escolhidos pela configuração Mysql do sistema, caso desejado.

2- Inicilizar o Tomcat
- Neste projeto foi utilizado o apache-tomcat-9.0.45. Na pasta do tomcat executar os seguinte comandos:
<pre><code>$ cd bin
$ ./catalina.sh run
</pre></code>

3- Deploy do projeto
- O deploy foi feito utilizando maven com o seguinte comando:
<pre><code>$ mvn tomcat7:deploy
</pre></code>

4- Acessando a aplicação pelo navegador
localhost:8080/AA1/

5- Observações do banco de dados populado
- O bando de dados criado seguindo esses passos já foi populado para que todas as funcionalidades implementadas neste sistema possam ser testadas.
- Para acessar as funcionalidades que necessitam do papel de administrador, entrar no sistema com LOGIN=admin, SENHA=admin.
- Para acessar as funcionliadade que necessitam do papel de usuário Profissional, pode-se entrar no sistema com LOGIN=rdmaljr@hotmail.com SENHA=123password ou LOGIN=marcela@hotmail.com SENHA=1234password ou LOGIN=jose@estudante.ufscar.br SENHA=1235password.
- Para acessar as funcionalidade que necessitam do papel de usuário Empresa, pode-se entrar no sistema com LOGIN=microsoft@hotmail.com SENHA=1236password ou LOGIN=roseval@estudante.ufscar.br SENHA=1237password.

# Checklist de Requisitos Concluídos
- Todos os requisitos especificados pelo professor foram implementados e integrados na aplicação.






