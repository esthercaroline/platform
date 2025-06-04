# 💸 Análise de Custos da Aplicação na AWS

## Visão Geral

A aplicação foi implantada em um ambiente de nuvem gerenciado pela AWS, utilizando o serviço **Amazon EKS** (Elastic Kubernetes Service) para orquestração dos microsserviços. A seguir, detalha-se uma análise robusta de custos baseada em:

- Dados reais da aba de faturamento da AWS
- Uso atual do cluster EKS e serviços relacionados
- Projeções de serviços futuros (Redis, Jenkins, Prometheus e Grafana)

---

## Estimativa de Custos Mensais por Serviço

| Serviço                                | Estimativa Mensal (USD) | Observações                                                                                      |
|----------------------------------------|--------------------------|--------------------------------------------------------------------------------------------------|
| **EKS (Elastic Kubernetes Service)**   | $8.00                    | Custo base pelo uso do plano de controle gerenciado.                                             |
| **EC2 - Compute (Instâncias)**         | $6.45                    | Refere-se ao uso de 2 nós `t3.medium`, responsáveis pela execução dos pods Kubernetes.           |
| **EC2 - Other (Outros custos)**        | $7.45                    | Inclui EBS (discos), transferência de dados e outros componentes de infraestrutura.             |
| **VPC (Virtual Private Cloud)**        | $0.78                    | Custo de roteamento, sub-redes e infraestrutura de rede.                                         |
| **S3 (Simple Storage Service)**        | $0.00                    | Ainda não utilizado no projeto.                                                                  |
| **Taxas (Tax)**                        | $3.11                    | Impostos e encargos aplicáveis pela AWS.                                                         |
| **Redis (ElastiCache - previsto)**     | $15.00                   | Simulação para uma instância `cache.t3.micro`, considerando uso em produção leve.               |
| **Jenkins (container no EKS)**         | $0.00                    | Reaproveita o cluster EKS, sem custo adicional relevante.                                        |
| **Prometheus + Grafana (previstos)**   | $0.00                    | Devem rodar no mesmo cluster com volume efêmero; custo incluso nos nós EC2.                     |
| **Total Estimado (com Redis)**         | **≈ $40.79**             | Valor mensal projetado com base no uso atual e nos serviços adicionais planejados.              |

---

## Gráfico de Distribuição de Custos

![Gráfico de Pizza dos Custos Estimados](imagens/graph.png)

> Este gráfico mostra visualmente que os maiores custos estão concentrados em EC2 e EKS, responsáveis pela infraestrutura base dos microsserviços.

---

## Análise Crítica

- **EKS e EC2** somam mais de 60% do custo total, sendo os principais alvos de otimização.
- **Redis** deve ser o único serviço externo a ser adicionado com custo significativo.
- Serviços como **Jenkins**, **Prometheus** e **Grafana** foram planejados para rodar no mesmo cluster, evitando gastos extras.
- **VPC** tem custo marginal, mas constante.
- **RDS** foi descartado: o banco de dados PostgreSQL está rodando localmente, o que reduz significativamente o custo da arquitetura.

---

## Recomendações de Otimização

| Estratégia                              | Benefício Esperado                                         |
|-----------------------------------------|-------------------------------------------------------------|
| Reduzir instâncias para `t3.small`      | Economia de até 30% no EC2 Compute                          |
| Ativar o HPA corretamente               | Balanceamento automático de carga reduz picos desnecessários|
| Monitorar custos com AWS Budgets        | Alertas automáticos ao ultrapassar limite definido          |
| Consolidar serviços no mesmo cluster    | Redução do overhead por multi-cluster                       |

---

## Projeções Futuras

Com a aplicação amadurecendo, a projeção de custo total com Redis, Jenkins e observabilidade adicionados gira em torno de **USD $40 a $45 por mês**, mantendo uma estrutura robusta e escalável.

Se o projeto for movido para produção real com mais tráfego e dados, recomenda-se:

- Migrar Redis para um plano com réplica (`cache.t3.small` ou maior)
- Persistir métricas do Prometheus em EBS
- Monitorar tráfego com AWS CloudWatch para otimizar balanceadores e transferências

---

## Referências

- [AWS Pricing Calculator](https://calculator.aws.amazon.com/)
- [AWS Cost Explorer](https://console.aws.amazon.com/cost-reports/home)
- [Documentação do EKS](https://docs.aws.amazon.com/eks/)
- [Guia de preços do ElastiCache](https://aws.amazon.com/elasticache/pricing/)
- [Comparativo de instâncias EC2](https://instances.vantage.sh/)

---

