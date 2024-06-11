# Use uma imagem base com JDK 17
FROM openjdk:17-slim

# Instalar netcat e outras dependências necessárias
RUN apt-get update && \
    apt-get install -y netcat-openbsd procps && \
    rm -rf /var/lib/apt/lists/*

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR e o script de espera para o diretório de trabalho no contêiner
COPY  IdeaDataBridge/src/artefatos/IdeaDataBridgeCliente.jar /app/IdeaDataBridgeCliente.jar
COPY IdeaDataBridge/src/artefatos/wait-for-it.sh /app/wait-for-it.sh

# Torne o script executável
RUN chmod +x /app/wait-for-it.sh

# Carregar as variáveis de ambiente e especificar o comando para rodar a aplicação Java
CMD ./wait-for-it.sh db 3306 -- java -jar /app/IdeaDataBridgeCliente.jar $CODIGOTOTEM