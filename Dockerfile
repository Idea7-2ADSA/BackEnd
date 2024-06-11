FROM openjdk:17
WORKDIR /app
COPY /IdeaDataBridge/src/artefatos/IdeaDataBridgeCliente.jar /app
CMD java -jar /app/IdeaDataBridgeCliente.jar $CODIGOTOTEM