# UC5 - Especificar Colaborador de Organização

## 1. Engenharia de Requisitos

### Formato Breve

O gestor de organização inicia a especificação de um colaborador da sua organização. O sistema solicita os dados necessários (i.e. nome, função, contacto telefónico, endereço de email). O gestor de organização introduz os dados solicitados. O sistema **valida** e apresenta os dados ao gestor de organização, pedindo que os confirme. O gestor de organização confirma. O sistema **regista os dados do colaborador, gera a sua password, torna-o um utilizador registado** e informa o gestor de organização do sucesso da operação.

### SSD
![UC5_SSD.svg](UC5_SSD.svg)


### Formato Completo

#### Ator principal

* Gestor de Organização

#### Partes interessadas e seus interesses
* **Gestor de Organização:** pretende especificar os colaboradores da sua organização.
* **Organização:** pretende que os seus colaboradores esteja registados para que estes possam atuar em sua representação.
* **T4J:** pretende que as organizações possam registar colaboradores seus.


#### Pré-condições
* n/a

#### Pós-condições
* A informação do novo colaborador de organização é registada no sistema.
* O colaborador também se torna um utilizador registado sistema.

### Cenário de sucesso principal (ou fluxo básico)

1. O gestor de organização inicia a especificação de um colaborador da sua organização.
2. O sistema solicita os dados necessários (i.e. nome, função, contacto telefónico, endereço de email).
3. O gestor de organização introduz os dados solicitados.
4. O sistema valida e apresenta os dados ao gestor de organização, pedindo que os confirme.
5. O gestor de organização confirma os dados.
6. O sistema regista os dados do colaborador, **gera a sua password, torna-o um utilizador registado** e informa o gestor de organização do sucesso da operação.


#### Extensões (ou fluxos alternativos)

*a. O gestor de organização solicita o cancelamento da especificação de um colaborador da sua organização.

> O caso de uso termina.

4a. Dados mínimos obrigatórios em falta.
>	1. O sistema informa quais os dados em falta.
>	2. O sistema permite a introdução dos dados em falta (passo 3)
>
	>	2a. O gestor de organização não altera os dados. O caso de uso termina.

4b. O sistema deteta que os dados (ou algum subconjunto dos dados) introduzidos devem ser únicos e que já existem no sistema.
>	1. O sistema alerta o gestor de organização para o facto.
>	2. O sistema permite a sua alteração (passo 3)
>
	>	2a. O gestor de organização não altera os dados. O caso de uso termina.

4c. O sistema deteta que os dados introduzidos (ou algum subconjunto dos dados) são inválidos.
> 1. O sistema alerta o gestor de organização para o facto.
> 2. O sistema permite a sua alteração (passo 3).
>
	> 2a. O gestor de organização não altera os dados. O caso de uso termina.

#### Requisitos especiais
\-

#### Lista de Variações de Tecnologias e Dados
\-Algoritmo de gerar password

#### Frequência de Ocorrência
\-

#### Questões em aberto

* Existem outros dados que são necessários?
* Qual ou quais os dados que identificam de forma única um colaborador de organização?
* Qual a frequência de ocorrência deste caso de uso?


## 2. Análise OO

### Excerto do Modelo de Domínio Relevante para o UC

![UC5_MD.svg](UC5_MD.svg)


## 3. Design - Realização do Caso de Uso

### Racional

| Fluxo Principal | Questão: Que Classe... | Resposta  | Justificação  |
|:--------------  |:---------------------- |:----------|:---------------------------- |
| 1. O gestor de organização inicia a especificação de um colaborador da sua organização.   		 |	... interage com o utilizador? | EspecificarColaboradorUI    |  Pure Fabrication: não se justifica atribuir esta responsabilidade a nenhuma classe existente no Modelo de Domínio. |
|  		 |	... coordena o UC?	| EspecificarColaboradorController | Controller    |
|  		 |	... cria instância de Colaborador?| Organizacao   | Creator (Regra1): no MD a Organização tem Colaborador.   |
||...conhece o utilizador/gestor a usar o sistema?|SessaoUtilizador|IE: cf. documentação do componente de gestão de utilizadores.|
||...sabe a que organização o utilizador/gestor pertence?|Plataforma|IE: conhece todas as organizações.|
|||Organização|**IE: conhece o registo RegistoColaborador.**|
|||**RegistoColaborador**|**IE: contém a lista de colaboradores.**|
|||Colaborador|IE: conhece os seus dados (e.g. email). |
| 2. O sistema solicita os dados necessários (i.e. nome, função, contacto telefónico, endereço de email).  		 |							 |             |                              |
| 3. O gestor de organização introduz os dados solicitados.  		 |	... guarda os dados introduzidos?  |   Colaborador | Information Expert (IE) - instância criada no passo 1: possui os seus próprios dados.     |
| 4. O sistema valida e apresenta os dados ao gestor de organização, pedindo que os confirme.   		 |	... valida os dados do Colaborador (validação local) | Colaborador |      IE: possui os seus próprios dados.|  	
|	 |	... valida os dados do Colaborador (validação global) |RegistoColaborador  | IE: a RegistoColaborador contém/agrega a lista de Colaborador.  |
| 5. O gestor de organização confirma os dados.   		 |							 |             |                              |
| 6. O sistema regista os dados do colaborador, gera a sua password, torna-o um utilizador registado e informa o gestor de organização do sucesso da operação.  		 |	... guarda o Colaborador criado? | **RegistoColaborador**  |**HC e LC: Regra 2: Quando uma lista de Colaborador é do sistema denomina-se  RegistoColaborador contém/agrega.**|  
||... gera a password?|**classe que implemente a interface ** *AlgoritmoGeradorPasswords* **|Protected Variation:a implementação desta forma fará com que se evite problemas de variação no algoritmo externo que será implementado.|
|	| ... regista/guarda o Utilizador referente ao Colaborador? | AutorizacaoFacade  | IE: a gestão de utilizadores é responsabilidade do componente externo respetivo, cujo ponto de interação é através da classe "AutorizacaoFacade".|


### Sistematização ##

 Do racional resulta que as classes conceptuais promovidas a classes de software são:

 * Organizacao
 * **RegistoColaborador**
 * **EspecificarColaborador**
 * Colaborador
 * Plataforma


Outras classes de software (i.e. Pure Fabrication) identificadas:  

 * EspecificarColaboradorUI  
 * EspecificarColaboradorController

Outras classes de sistemas/componentes externos:

 * SessaoUtilizador
 * AutorizacaoFacade


###	Diagrama de Sequência

**Nota 1:** As mensagens 5, 6 e 7 têm o intuito de demonstrar como é obtida a informação do utilizador do sistema e estão em conformidade com a documentação fornecida inicialmente. Nesse sentido, podiam ter sido omitidas.

**Nota 2:** __Neste UC, a mensagem 8 é de extrema relevância pois é com esta que se determina a qual organização é adicionado/registado o colaborador que está a ser criado.__

#### Alternativa 1 - Correta exclusivamente sob o ponto de vista do UC 5 mas incoerente
![UC5_SD.svg](UC5_SD.svg)

**Nota 3:** Sob o ponto de vista exclusivo do UC 5 o método *novoColaborador(nome, funcao, telf, email)* deve ser um método de instância da classe Organização. __Contudo, esta solução não está coerente com o design adotado no UC 1, onde este método é estático.__

#### Alternativa 2 - Correta e Coerente entre o UC 1 e UC 5
![UC5_SD_a2.svg](UC5_SD_a2.svg)

### SD_ATUALIZADO
![UC5_SD_ATUALIZADO.svg](UC5_SD_ATUALIZADO.svg)

**Registar Colaborador como Utilizador ->REFERÊNCIA:**

![UC5_SD_ATUALIZADO_REGISTAR_COLABORADOR_COMO_UTILIZADOR.svg](UC5_SD_ATUALIZADO_REGISTAR_COLABORADOR_COMO_UTILIZADOR.svg)


###	Diagrama de Classes

#### Alternativa 1 - Correta exclusivamente sob o ponto de vista do UC 5 mas incoerente
![UC5_CD.svg](UC5_CD.svg)

#### Alternativa 2 - Correta e Coerente entre o UC 1 e UC 5
![UC5_CD_a2.svg](UC5_CD_a2.svg)

### CD_ATUALIZADO
![UC5_CD_ATUALIZADO.svg](UC5_CD_ATUALIZADO.svg)

#### Reflexão ####

**Entre o UC 1 e o UC 5 haverá mais alguma coisa que deva ser ajustado/retificado?Se sim, o quê, porquê e como?**

--
**RESPOSTA**
**Pelo padrão HC e LC devemos criar uma classe "RegistoColaborador" para agregar colaborador e assim remover algumas funções a mais na organização que assim passará  a conter o registo de Colaborador**.
