package com.bcp.automatizacion.definition;

import static org.junit.Assert.assertTrue;

import com.bcp.automatizacion.step.CreacionArchivoStep;
import com.bcp.automatizacion.util.Constantes;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class CreacionArchivoDefinicion {
	@Steps
	private CreacionArchivoStep creacionArchivoSteps;

	
	@Given("^que el usuario ingresa a la plataforma google drive$")
	public void que_el_usuario_ingresa_a_la_plataforma_google_drive() {
		creacionArchivoSteps.googleDrive();
	}
	
	@When("^ingresa el usuario \"([^\"]*)\" y la contraseña \"([^\"]*)\" de su gmail$")
	public void ingresa_el_usuario_y_la_contraseña_de_su_gmail(String usuario, String contrasenia) {
		creacionArchivoSteps.login(usuario,contrasenia);	
	}
	
	@And("^crea el nuevo documento google$")
	public void crea_el_nuevo_documento_google() {
		creacionArchivoSteps.crearDocumento();
	}

	@And("^guarda el documento creado$")
	public void guarda_el_documento_creado() {
		creacionArchivoSteps.guardarDocumento();
	}

	@Then("^validar que el documento se creo con el codigo, nombre , fecha y hora exacta$")
	public void validar_que_el_documento_se_creo_con_el_codigo_nombre_fecha_y_hora_exacta() {
		creacionArchivoSteps.listaDocumentos();
	}
	
	
}
