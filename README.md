# Trabalho de Banco de Dados AgendaTel

## Contexto

A aplicação *AgendaTel* é uma aplicação Java usando a biblioteca de janelas
gráficas ao usuário (*GUI*) chamada *Swing*. Basicamente a aplicação é um *CRUD*
para entradas em uma agenda telefônica com nome e telefone.

## Preparação

1. Crie uma conta no GitHub (caso ainda não tenha uma)

2. realizar fork do repositório AgendaTel

- [Repositório AgendaTel no GihHub](https://github.com/marciobelo/AgendaTel)

3. clonar repositório localmente

ATENÇÃO: **NÃO** é para clonar o de repositório do professor! É para clonar o
seu próprio repositório (criado a partir do fork do professor). 
Repare o fulano abaixo representa seu nome de usuário no GitHub.

```sh
git clone https://github.com/fulano/AgendaTel.git
```

4. Baixar o NetBeans (quem não tiver)

- [Link NetBeans](https://netbeans.apache.org)

5. Abrir o projeto e rodar

Teste a aplicação usando serialização em disco.

Explore o código da aplicação: a aplicação usa *Swing* para criação da 
interface com o usuário.

## O Trabalho

Após sua análise do código, você perceberá que os registros são salvos num
objeto serializável que é persistido num arquivo em disco (`agenda.ser`).

Mude a aplicação para que ele persista os registros num banco de dados
*MySQL*.

> Dica: em *Windows*, use um software como WampServer (ou VertrigoServ). Ele
cria uma instalação local do *MySQL* de forma simples para começar a usar.
Teste sua conexão antes de tentar codificar a solução.

Sua entrega será composta por duas partes:

1. Submissão da URL do seu repositório GIT.

P.ex.: `https://github.com/fulano/AgendaTel.git`

> Atenção: o seu fork precisa estar público também.

2. Dentro do seu repositório, na pasta `video` coloque um
arquivo mp4 mostrando sua aplicação já utilizando o banco de dados *MySQL*.