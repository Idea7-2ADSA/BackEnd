FROM openjdk:17
COPY ./IdeaDataBridge/src/artefatos/IdeaDataBridgeCliente.jar ./
WORKDIR ./
EXPOSE 8080
CMD ["java","-jar","IdeaDataBridgeCliente.jar"]
