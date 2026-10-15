# ThaynaBDSVet

Cadastro de pet shop em Java. O programa guarda tutor, animal, setor e produto em memoria, com menu no console.

A ficha do animal so abre se o documento informado ja pertence a um tutor. O mesmo documento nao pode ser usado duas vezes. Produto so entra em setor que ja existe. Tutor com animal na ficha nao e apagado. Setor com produto tambem nao.

## Camadas

```
App
  -> controllers
      -> services
          -> repositories
              -> models
```

O controller le o teclado e mostra a mensagem. A regra fica no service. A lista fica no repository.

## Executar

Requisito: Java 17.

```bash
./mvnw -q test
./mvnw -q -DskipTests compile
java -cp target/classes App
```

Os setores da loja ja entram no inicio. Ao fechar o programa, o cadastro some.

## Testes

`./mvnw test` cobre documento repetido, animal sem tutor, tutor com animal, produto sem setor, valor negativo e faixa de preco invertida. O GitHub Actions roda o mesmo comando.

## Autora

Thayna Batista da Silva
thaynabdstec@gmail.com
https://github.com/thaynabds
