# Sigest ![Build Status](https://travis-ci.org/tacianosilva/sigest.svg?branch=sigest-1.0)

Repositório para o Projeto do Sistema de Gerenciamento de Estágios para a disciplina de Tópicos Especiais em Engenharia de Software do curso de Bacharelado em Sistemas de Informação da UFRN - Campus Caicó/RN.

A idéia é permitir que professores, alunos e coordenação acompanhem de forma simples o andamento dos estágios facilitando assim a sua avaliação.

## Execução

### Criar Banco de Dados (dev e test)

    CREATE DATABASE sigest_db;
    CREATE DATABASE sigest_db_test;

    CREATE USER 'sigest_user'@'localhost' IDENTIFIED BY 'sigest_user';

    GRANT ALL ON sigest_db.* TO 'sigest_user'@'localhost';
    GRANT ALL ON sigest_db_test.* TO 'sigest_user'@'localhost';
