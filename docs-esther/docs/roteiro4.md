# Roteiro 4 — CI/CD com Jenkins

## Desenvolvedora

- Esther Cunha  

---

## Por que utilizar o Jenkins?

O Jenkins é uma ferramenta de automação **open source** amplamente utilizada em processos de **Integração Contínua (CI)** e **Entrega Contínua (CD)**.  
Com ele, é possível automatizar tarefas como:

- Build de aplicações
- Execução de testes
- Geração e push de imagens Docker
- Deploy em ambientes de produção ou homologação

Além disso, ele se integra facilmente com serviços como **DockerHub**, **GitHub**, **AWS**, e **Kubernetes**, permitindo uma entrega rápida, confiável e reproduzível.

---

## Descrição da Atividade

Foi implementada uma pipeline completa de **CI/CD** para o microserviços, utilizando o Jenkins como orquestrador de build e deploy.

Essa pipeline automatiza todas as etapas críticas da entrega, desde a compilação até o deploy da imagem Docker no repositório remoto.

### Requisitos atendidos:

- Pipeline versionada com `Jenkinsfile` dentro do repositório dos microserviços.
- Build da aplicação Java com Maven (`clean package`).
- Geração de imagem Docker multiplataforma com `buildx`.
- Publicação automática da imagem no DockerHub (`latest` e tag com `BUILD_ID`).
- Uso de credenciais seguras armazenadas no Jenkins (DockerHub credentials).
- Execução local do Jenkins com controle total do processo.

---

## Exemplo de Jenkinsfile Utilizado

```groovy
pipeline {
    agent any
    environment {
        SERVICE = 'product'
        NAME = "esthercaroline/${env.SERVICE}"
    }
    stages {
        stage('Dependecies') {
            steps {
                build job: 'product', wait: true
            }
        }
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }      
        stage('Build & Push Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credential', usernameVariable: 'USERNAME', passwordVariable: 'TOKEN')]) {
                    sh "docker login -u $USERNAME -p $TOKEN"
                    sh "docker buildx create --use --platform=linux/arm64,linux/amd64 --node multi-platform-builder-${env.SERVICE} --name multi-platform-builder-${env.SERVICE}"
                    sh "docker buildx build --platform=linux/arm64,linux/amd64 --push --tag ${env.NAME}:latest --tag ${env.NAME}:${env.BUILD_ID} -f Dockerfile ."
                    sh "docker buildx rm --force multi-platform-builder-${env.SERVICE}"
                }
            }
        }
    }
}
```
---

## Etapas da Pipeline

| Etapa              | Descrição                                                                 |
|--------------------|---------------------------------------------------------------------------|
| `Dependecies`      | Executa o job `product` para garantir que o build do microserviço ocorra. |
| `Build`            | Compila a aplicação usando Maven, sem rodar os testes.                    |
| `Build & Push`     | Gera imagem multiplataforma com `buildx` e publica no DockerHub.          |

---


## Jenkins rodando

**Exemplo:**  
![](imagens/kubernetes.png)

---
