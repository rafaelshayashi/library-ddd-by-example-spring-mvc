# Library (ddd by example) | Desafio de código

Esse projeto visa implementar um software para gerênciamento de livros em uma bibliotéca, é um projeto de estudo 
e o seu objetivo principal é implementar utilizando uma aqrtuitetura de microsservicos com funcionalidaes 
muitos proximas do mundo real.

Para ser possível fazer isso foi utilizado todo a descrição de um projeto do ddd by example library, esse projeto 
é bem abrangente e define todos os requisitos de um sistema real utilizando-se do DDD para alcançar esse objetivo,
por causa dessa similaridade com o mundo real é a base ideal.

## Domain description

Uma bibliotéca possibilita que usuários peguem livros emprestados em diferentes unidades da bibliotéca. Livros podem ser emprestados a apenas um usuário por vez em qualquer momento. Livros podem ser normais(circulating) ou restritos e podem haver a cobranca pelo seu emprestimos ou utilização. Um livro restrito poderá só poderá ser emprestado ou utilizado por um usuário que seja um pesquisados. Um usuário comum pode ter somente cinco emprestimos de livros simultaneamente, enquanto um usuário pesquisador não há limite. O emprestimo(open-ended) está em aberto até que o usuário faça a devolução do livro.

Os livros não poderão ser cadastrados com um titulo e um preço em branco. No momento da adição do livro será definido se o 
livro é comum ou restrito

## Definições

Usuário
Qualquer pessoa que pode solicitar o emprestimo do livro

Available book
Um livro que está disponível para emprestimo

Circulating Book
Um livro que está disponivel para emprestimo para qualquer usuário

Restricted Book
Um livro que está disponível somente para usuários qualificados

Book on Hold
Um livro que está emprestado para algum usuário

Collected Book

Catologue
O catalogo dos livros e suas unidades

Hold Duration
O tempo de duração do emprestimo, pode ser ilimitado em alguns casos

Checkout
Pegar o livro em alguma unidade da biblioteca

Overdue Checkout
Um libvro que nao foi devolvido em até 60 dias

Daily Sheet with Expired Holds
Uma lista com todos os emprestimos que expirados, essa lista é atualizada diariamente


# Dominios

Catálogo -> Livros, instancias de livros.
Patrons -> Regular and research
Library Branch -> Identification, address
Service worker -> Verificacao diária


## Gradle

this projects uses gradle as a tool for building the java applications and also uses a feature that generated a gradle
lock file using the following commnad

```bash
./gradlew resolveAndLockAll --write-locks
```

