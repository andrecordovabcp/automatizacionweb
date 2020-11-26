# Pruebas Automatizadas
Este proyecto nos ayuda ah validar la creacion de los documentos word en la plataforma Google Drive

## Comenzando 🚀

Para poder ejecutar nuestro proyecto necesitamos un editor de codigo JAVA-MAVEN
Estos son los pasos para poder ejecutar el proyecto:
1.Importar el proyecto MAVEN
2.Instalar todas las dependencias haciendo click derecho en el proyecto, escoges las opcion MAVEN, haces click en Update Project
3.Comprobamos la version de nuestro chrome driver de nuestro dispositivo
4.Si tiene una version diferente de Versión 87.0.4280.66 descarga la version en "https://chromedriver.chromium.org/"
5.Para ejecutar todo el proyecto te diriges a la capa runner hacer click en la clase Runner
6.Hacer click derecho en Run As y hacer click en JUnit Test
7.Despues de ejecutar el proyecto podemos visualizar el reporte mediante el comando "mvn serenity:aggregate"


##Despliegue
El proyecto esta configurado para poder desplegar el proyecto en jenkins