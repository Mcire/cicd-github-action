# CICD GitHub Action

Ce projet est une démonstration d'une application Java Spring Boot avec des actions GitHub pour le CI/CD.

## Description

Ce projet utilise Maven pour la gestion des dépendances et des packages, ainsi que Spring Boot pour simplifier le développement d'applications Java.

## Installation

1. Clonez ce dépôt sur votre machine locale.
2. Assurez-vous d'avoir Java JDK 17 et Apache Maven installés sur votre système.

## Configuration

Avant d'exécuter les workflows GitHub Actions, vous devez effectuer les configurations suivantes :

## build.yml
```yml
name: build-and-push-image

on:
  push:
    branches: [ main, release ]
    paths-ignore:
      - '**/README.md'
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 17
        uses: actions/setup-java@v2
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: maven
      - name: Build with Maven
        run: mvn -B package
      - name: Publish to Docker
        run: |
            mvn -B spring-boot:build-image -Dspring-boot.build-image.publish=true \
               -Ddocker.user=${{ secrets.DOCKER_USER }} -Ddocker.token=${{ secrets.DOCKER_TOKEN }} \
               -DskipTests
```
ce fichier de configuration définit un processus automatisé pour construire une application Java avec Maven, puis publier l'image Docker résultante dans un registre Docker, en utilisant GitHub Actions.

### Docker Hub

1. Créez un compte sur Docker Hub si vous n'en avez pas déjà un.
2. Créez un repository pour votre application sur Docker Hub.

### GitHub

1. Assurez-vous d'avoir les autorisations nécessaires pour configurer les secrets dans le repository GitHub.
2. Configurez les secrets suivants dans les paramètres de votre repository :

   - `DOCKER_USER`: Votre nom d'utilisateur Docker Hub.
   - `DOCKER_TOKEN`: Votre token d'accès Docker Hub.

## Utilisation

Une fois les configurations effectuées, vous pouvez exécuter les workflows GitHub Actions.

1. Lorsque vous poussez sur les branches `main` ou `release` (à l'exception des modifications apportées aux fichiers `README.md`), le workflow "Build and Push Image" sera déclenché.
2. Ce workflow construit l'image Docker de l'application et la publie sur Docker Hub.

## Result

