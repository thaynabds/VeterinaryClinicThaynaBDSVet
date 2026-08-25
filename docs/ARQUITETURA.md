# Arquitetura - ThaynaBDSVet

## Camadas

```
App
  chama Controller
Controller
  le Scanner, trata NumberFormatException, chama Service
Service
  valida regra de negocio, chama Repository
Repository
  usa List estatica
Model
  atributos, construtores, getters e setters
```

## Por que tres construtores no Model

1. Vazio: cria objeto sem dados.
2. Cadastro: gera `id` com `contador` estatico (`++contador`), como um AUTO_INCREMENT.
3. Completo (com id): usado no update, quando o registro ja existe.

## Exemplo de fluxo: cadastrar animal na ficha

1. Usuario escolhe a opcao 2
2. `TutorController` pede o ID do tutor
3. `TutorService.buscarPorId` consulta o `TutorRepository`
4. Controller le os dados do animal
5. `AnimalService.cadastrar` valida nome, documento e especie
6. `AnimalRepository.create` guarda o animal na lista
7. `TutorService.adicionarAnimal` coloca o animal na lista do tutor
8. Opcionalmente o mesmo animal e ligado a outro tutor

## O que cada camada NAO faz

- Model nao imprime menu e nao valida regra de negocio
- Repository nao valida valor negativo
- Service nao le Scanner
- Controller nao acessa a lista estatica do Repository
