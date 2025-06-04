# ☁️ Plataforma como Serviço (PaaS)

## Onde utilizamos PaaS

No nosso projeto, utilizamos **serviços em modelo PaaS** principalmente em duas frentes: **orquestração de microsserviços com o Amazon EKS** e **execução da pipeline CI/CD com o Jenkins no cluster Kubernetes**.

### 1. Amazon EKS (Elastic Kubernetes Service)

O EKS é um exemplo claro de Plataforma como Serviço (PaaS). A AWS gerencia toda a camada de infraestrutura abaixo do Kubernetes — como servidores físicos, virtualização, sistema operacional e orquestração — permitindo que nossa equipe foque no desenvolvimento, deploy e escalabilidade dos microsserviços.

**Como utilizamos o EKS:**
- Criamos um cluster gerenciado usando o AWS EKS.
- Implantamos todos os nossos microsserviços (gateway, account, auth, exchange, product, order) dentro do cluster.
- Gerenciamos o deploy via arquivos `k8s.yaml` com as definições de `Deployment`, `Service`, `Secrets` e `ConfigMap`.
- Aplicamos o Horizontal Pod Autoscaler (HPA) para escalonamento automático baseado em carga.

Isso nos permitiu desenvolver e escalar a aplicação sem nos preocupar com a complexidade de provisionar e manter a infraestrutura subjacente.

---

### 2. Jenkins no Kubernetes

Embora o Jenkins em si seja uma ferramenta de automação (e normalmente rodaria como IaaS), ao rodá-lo **dentro de um cluster Kubernetes gerenciado pelo EKS**, ele também passa a se beneficiar do modelo PaaS — pois toda a orquestração, disponibilidade e escalabilidade do Jenkins está sobre uma plataforma automatizada.

**Como utilizamos o Jenkins:**
- Jenkins foi configurado no cluster EKS via `Jenkinsfile` em cada microsserviço.
- Automatizamos etapas de build, push para DockerHub e deploy no próprio EKS.
- A integração CI/CD ficou desacoplada do hardware e servidores físicos, permitindo flexibilidade e disponibilidade constantes.

---

### 3. Observabilidade e Planejamento futuro (PaaS implícito)

Também planejamos utilizar Prometheus + Grafana e Redis em containers no EKS, o que herda as características de PaaS:

- **Redis (em container)** para caching, com escalabilidade gerenciada automaticamente.
- **Prometheus + Grafana**, rodando em containers desacoplados da infraestrutura, usados para monitoramento e visualização de métricas.

Mesmo sendo componentes tradicionalmente usados em modelos IaaS, o fato de estarem sendo executados no EKS faz com que se encaixem em uma estrutura de PaaS funcional, já que a AWS se responsabiliza pela infraestrutura necessária para executá-los.

---

## Conclusão

Ao optar por executar toda a aplicação em um cluster Kubernetes gerenciado (EKS), nosso grupo aderiu ao modelo PaaS na prática. Isso trouxe agilidade no desenvolvimento, facilidade de manutenção e escalabilidade sem a complexidade de gerenciar infraestrutura física, o que é a essência do modelo Platform as a Service.

