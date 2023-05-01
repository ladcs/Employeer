# Teste Técnico para PROJEDATA

Esse projeto foi feiro com a linguagem JAVA, com o teste em JUnit 5.

Para iniciar o projeto, no terminar usar o comando:

````sh
git clone https://github.com/ladcs/Employeer

cd Employeer

mvn install

docker-compose up
````
Para isso é necessário o git, maven e docker installado na máquina.

Com isso poderá fazer o Run na class App.java no caminho src/main/java/employeer.

O docker-commpose é responsável por colocar o banco de dados em um conteinar, esse que é o MySQL.

No arquivo pom.xml temos as dependências:

spring -> o que permite usar o servlet;

servlet -> para poder fazer uma API Rest no programa;

persistence -> para conectar o ORM com o banco de dados;

junit -> fazer os testes;

hibernate -> ORM para trabalhar com o banco de dados;

MySQL -> conexão com o banco de dados;

logback -> tirar os logs das dependêcias.

# Packages:

<details><summary><strong>employeer</strong></summary> <br/>
Aqui é possível encontrar a classe App e a classe Seed, além dos outros pacotes.

<details><summary>classe App</summary> <br/>
Propriedade crud, instancia de RestAPi.

métodos: main, delay, jumpTenLines, next, requestProjedata, printOptions, optionsValue, addNew, delete, incRemuneration, mapJob, oldest, sort, allRemuneration e minRemuneration.

Já o método delay imprime: "pressione enter para continuar" e faz o programa esperar essa ação.


Enquanto o método jumpTenLines imprime 10 linhas em branco para haver uma pequena separação entre os requisitos.

Já o método next serve apenas para chamar os dois últimos métodos para serem usados na main.

requestProjedata faz o que foi solicitado.

printOptions mostra as opções que pode ser escolhido.

optionsValue redireciona para fazer o que foi pedido e usa um método next.

addNew adiciona um novo Funcionario.

delete deleta um Funcionario.

incRemuneration aumenta/diminui o salário de todos os Funcionario do banco de dados.

mapJob mostra o map com as chaves e os nomes dos Funcionario.

oldest mostra o Funcionario mais velho.

sort mostra os nomes dos Funcionario ordenado.

allRemuneration mostra a soma de todos os salários.

minRemuneration mostra o nome e quantos salários mínimos cada um recebe.

</details>

<details><summary>classe Seed</summary> <br />
Possui apenas dois métodos, um para derrubar a tabela, caso ela já exista, deleteIfExistTable.

Outro para criar a tabela e popular a mesma com os dados fornecidos pela empresa, insertSeed.

seu constructor apenas chama o método deleteIfExistTable, esse que ao terminar chama insertSeed.
</details>
</details>

<details><summary><strong>model</strong></summary> <br/>
<details><summary>classe Pessoas</summary><br/>
É uma classe pai, onde possui as colunas name e birthDate para uma pessoa, ainda possui seus getters e setters. como é uma classe pai de uma entidade do hibernate usa-se @MappedSuperclass, para poder ter essas colunas na tabela.
</details>

<details><summary>classe Funcionario</summary><br/>
Possui as colunas id, remuneration e job, além de estender a classe Pessoas, herdando assim as colunas da mesma. possui ainda os seus getters e setters. A coluna id ela possui o auto incremente.
</details>
</details>

<details><summary><strong>restAPI</strong></summary> <br/>
<details><summary>classe RestApi</summary><br/>
Há métodos do CRUD com caminhos para tal.

Para criar uma entidade -> addOneEmployeer, path /api/employeer;

Para pegar todas as entidades -> getAllFunc, path /api/employeer;

Para pegar uma entidade pelo atributo name -> getByName, path /api/employeer/name;

Para pegar uma entidade pelo atributo id -> getById, path /api/employeer/id;

Para editar a remuneração pelo id -> updateRemunaration, path /api/employeer/id;

Para deletar pelo id -> delete, path /api/employeer/id;
</details>
</details>

<details><summary><strong>service</strong></summary> <br/>
<details><summary>classe EmployeerService</summary><br/>
Comunicação com o Hibernate.

Para criar uma entidade usa-se o método insertOne recebe um Funcionario e retorna o nome da entidade inserido no banco de dados;

Para encontrar uma entidade por Id usa-se o método findById, retorna o Funcionário com o id enviado;

Para encontrar uma entidade por name usa-se o método findByName, retorna o primeiro Funcionário na tabela com o name enviado;

Para deletar pelo nome usa-se deletePerson enviando o nome, retorna vazio;

O método findAll retorna todos os Funcionarios no banco de dados;

Por fim o método updateRemuneration recebe a Entidade e o valor novo valor de remunaretion e retorna a entidade com o update.
</details>
</details>

<details><summary><strong>util</strong></summary> <br/>
<details><summary>classe MapAndIncRemunaration</summary><br/>
Há duas propriedades, mapJobAndPeople e crud.

mapJobAndPeople é um map que a chave é uma string e que possui uma List de string, ele possui seu getter, mas não seu setter.

crud é uma instância da classe RestApi.

Os métodos são populateMap, incRemuneration.

Em populateMap usa-se a propriedade crud com o getAllFunc para então colocar no map a chave em job, caso ainda não tenha encontrado algum Funcionario com esse job, e adiciona o nome do mesmo na List. Caso haja a chave com o valor do job, simplesmente adiciona o nome a list.

Por fim o incRemuneration recebe o valor em porcentagem, com essa porcentagem, aumenta se positivo e diminui se negativo, remuneration de todos os Funcionarios no banco.

Constructor chama populateMap.
</details>

<details><summary>classe MapAndIncRemunaration</summary><br/>
Há duas propriedades, mapJobAndPeople e crud.

mapJobAndPeople é um map que a chave é uma string e que possui uma List de string, ele possui seu getter, mas não seu setter.

crud é uma instância da classe RestApi.

Os métodos são populateMap, incRemuneration.

Em populateMap usa-se a propriedade crud com o getAllFunc para então colocar no map a chave em job, caso ainda não tenha encontrado algum Funcionario com esse job, e adiciona o nome do mesmo na List. Caso haja a chave com o valor do job, simplesmente adiciona o nome a list.

Por fim o incRemuneration recebe o valor em porcentagem, com essa porcentagem, aumenta se positivo e diminui se negativo, remuneration de todos os Funcionarios no banco.

Constructor chama populateMap.
</details>

<details><summary>classe ToPrint</summary><br/>
Propriedades personInMonth, nameMonth, crud.

PersonInMonth é um map de String como chave e List de String, é a única propriedade com getter sem setter.

nameMonth é um array com os nomes de cada mês, de janeiro como posição zero e dezembro como posição 11.

crud é uma instancia de RestApi.

Os métodos são remunerationToString, birthdateDayMonthYear, setEmployeerInMonth, findOlder, sort, allRemuneration e numberRemuneration.

remunerationToString recebe um BigDecimal e retorna uma string no formato com "." no milhar e "," na parte decimal, com o valor do BigDecimal.

birthdateDayMonthYear recebe um LocalDate e retorna uma string com o valor recebido no formato dd/MM/yyyy.

setEmployeerInMonth recebe o mês como int, valor de 1 até 12, que é o mês, caso o valor esteja fora desse intervalo será lançado um erro. Assim em personInMonth vai receber como chave o nome do mês, esse que esta como valor no array nameMonth da posição do mês menos 1, e adiciona o nome de todos os nascidos nesse mês na chave.

findOlder busca a entidade do Funcionario mais velho e retorna uma string com o "nome possui idade anos.".

sort retorna um array com os nomes das entidades Funcionario ordenado em alfabeto.

allRemuneration retorna um BigDecimal que será o somatório de todas as remuneration das entidades Funcionario.

numberRemuneration recebe uma entidade Funcionario e um double min, returna um String com o "nome recebe 'quantos Salário' salários." ou "nome recebe 'quantos Salário' salário.".
</details>
</details>

<details><summary><strong>projedataRequest</strong></summary><br/>
<details><summary>class Table</summary><br/>
metodos: tableRows, titleTable, divTitleAndItens.

tableRows retorna uma List de string que usa uma instancia de ToPrint e outra de RestApi para pegar todas as entidades de Funcionario da tabela e formatar então retorna cada entidade como um item da lista.

titleTable retorna a string formatada da seguinte maneira "%10s %20s %10s %20s \n", "Nome", "Data Nascimento", "Salário", "Função". Acompanhando assim os itens de tableRows.

divTitleAndItens retorna "------------------------------------------------------------------\n".
</details>

<details><summary>class Prints</summary><br/>
métodos: table, deleteJoao, jobAndNames, monthAndNames, oldestPerson, sortPeople, total, remunerations.

table com uma instancia da classe table imprime divTitleAndItens, titleTable, divTitleAndItens por fim cada item retornado da tableRows.

deleteJoao deleta o João e usa o método table.

jobAndNames imprime o map com as pessoas nascidas nos meses 10 e 12.

oldestPerson imprime "nome possui idade anos." da pessoa mais velha.

sortPeople imprime o nome ordenado das pessoas com uma separação por linha.

total imprime o total a pagar com o formato "." em milhar e "," em decimal.

remunerations imprime quantos salarios cara funcionario recebe onde o salário mínimo seja de 1212.00 no formato "nome recebe 1 salário." ou "nome recebe x salários.".
</details>

<details><summary>class Prints</summary>

propriedades crud, instancia do RestApi, mapAndInc, instancia da MapAndIncRemunaration, e utilPrint, instancia de ToPrint.

constructor inicia as instancia das propriedades.

métodos: deleteJoao, incRemuneration, jobAndPeople, monthAndPeople, oldest, sort, total e numberOfRemunerations.

deleteJoao deleta do banco a entidate com o nome João.

incRemuneration aumenta o salário de todos em 10%, usando mapAndInc.

jobAndPeople retorna o map da instancia mapAndInc.

monthAndPeople retorna o map com os meses de outubro e dezembro da instancia utilPrint.

oldest retorna o findOlder do UtilPrint.

sort retorn ordenado pelo utilPrint.sort().

total retorna o valor da instancia utilPrint.remunerationToString(utilPrint.allRemuneration()).

numberOfRemunerations retorna uma List de String onde cada item recebe utilPrint.numberRemuneration(Feuncionario, 1212).

</details>
</details>
