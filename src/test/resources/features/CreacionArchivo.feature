#Author: andrepacordl@gmail.com
@tag
Feature: Creacion de archivos word en la plataforma de google drive

  @CreacionArchivoWord
  Scenario Outline: Como usuario bcp quiero crear un archivo word en la plataforma google drive para la comprobacion de archivos creados por nombre y fecha 
    Given que el usuario ingresa a la plataforma google drive 
    When ingresa el usuario "<usuario>" y la contraseña "<contrasenia>" de su gmail
    And crea el nuevo documento google
    And guarda el documento creado
    Then validar que el documento se creo con el codigo, nombre , fecha y hora exacta 
  Examples: 
      | usuario | contrasenia|
      |andrecordovabcp|bcp2020.|
  