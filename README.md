# App Login

App Login é um aplicativo Android desenvolvido para realiazar autenticação com usuários vindos de uma API.

## Destaques

- **Arquitetura MVVM**: O aplicativo adota a arquitetura Model-View-ViewModel (MVVM) para separar as preocupações de interface do usuário e lógica de negócios. Isso facilita a manutenção e escalabilidade do código.
- **Model-View**:A aplicação segue o padrão Model-View, no qual o Model representa os dados e a lógica, a View é responsável pela apresentação e a ViewModel atua como um intermediário entre os dois.
- **LiveData**: Utilizado para a criação de objetos LiveData que mantêm os dados atualizados e notificam as partes interessadas quando ocorrem alterações.
- **Consumo de API com Retrofit**: O aplicativo se comunica com uma API externa utilizando a biblioteca Retrofit, permitindo a recuperação de dados em tempo real.
- **Autenticação**: O app implementa um sistema de autenticação, permitindo que os usuários façam login para acessar funcionalidades específicas.
- **Testes Unitários**: Além disso, foram desenvolvidos testes unitários para garantir o bom funcionamento do aplicativo.

## Pré-requisitos

- Dispositivo Android com versão 7 ou posterior.
  
## Instalação

1. Clone este repositório: `git clone https://github.com/wallissonmart/app-kotlin-mvvm-login.git`.
2. Abra o projeto no Android Studio.
3. Aguarde a sincronização das dependências do Gradle.
4. Execute o aplicativo em um emulador ou dispositivo físico.

## Uso

Realizar Login:
Preencha os campos com os dados usuário.
Toque no botão "Entrar" para realizar autenticação e ir para a tela principal.

## Contribuição

Se deseja contribuir para o projeto, siga estas etapas:

1. Fork o projeto.
2. Crie sua ramificação de recurso (`git checkout -b feature/SeuRecurso`).
3. Commit suas alterações (`git commit -m 'Adicionar novo recurso'`).
4. Faça push para a ramificação (`git push origin feature/SeuRecurso`).
5. Abra uma solicitação pull.

## Autor

[Wallisson Martins] - [wallissonmartins37@gmail.com]
