package com.bcp.automatizacion.paths;

import net.serenitybdd.core.pages.PageObject;

public class AppPaths extends PageObject{
	
	public static final String btnGoogleApps="//a[@class='gb_D gb_vc' and @href='https://www.google.com.pe/intl/es/about/products?tab=wh']";
	public static final String btnDrive="//a[@href='https://drive.google.com/?tab=wo']";
	public static final String btnIrDrive="//a[@data-event-label='Ir a Drive' and @data-event-action='Encabezado']";
	public static final String spanCorreo="//span[text()='Iniciar sesión']";
	public static final String txtcorreo="//input[@id='identifierId' and @name='identifier']";
	public static final String spanClave="//div[text()='Ingresa tu contraseña']";	
	public static final String txtClave="//input[@type='password' and @name='password']";
	public static final String txtDrive="//span[text()='Drive']";
	public static final String btnNuevo="//button[@guidedhelpid='new_menu_button'  and @aria-label='Nuevo' ]";
	public static final String btnDocumento="//div[text()='Documentos de Google']";
	public static final String btnArchivo="//div[@id='docs-file-menu']";
	public static final String btnCambiarNombre="//span[@aria-label='Cambiar nombre r' and @class='goog-menuitem-label']";
	public static final String inpTituloDocumento="//input[@class='docs-title-input' and @value='Documento sin título']";
	public static final String txtGuardando="//div[@aria-label='Estado del documento: Guardado en Drive.' and @class='goog-control']";
	public static final String tblDocumentos="//div[contains(@data-tooltip,'Documentos de Google:')]";
	public static final String txtDocumento="//div[@data-tooltip='Documentos de Google: documento']";
	
	
	/**
     * Paths de registrar cotizacion 
     * * 
     */
	public final String pathLabelCanal="//label[text()='Canal:' and @class='x-form-item-label']";
	public final String pathLabelPlan="//label[text()='Plan Seleccionado:' and @class='x-form-item-label']";
	public final String pathInputFocus="//input[@class='x-form-text x-form-field x-form-focus']";
	public final String pathLoading="//div[@class='ext-el-mask']";
	public final String pathButtonGDParticulares="//button[@type='button' and text()='Generar Datos Particulares']";
	public final String pathSelectPlan="//input[@type='text' and @class='x-form-text x-form-field x-form-empty-field']";
	public final String pathSelectPlan1="//label[text()='Canal:']//following::div//div//input[@type='text' and @size='24' and @autocomplete='off' ]";
	/**
     * END
     */
	
	/**
     * Paths buscar poliza 
     * * 
     */
	public final String nroPoliza = "//input[@type='text' and @name='numero']";
	public final String pathDivLoading = "//div[contains(@class, 'masked')]";
	/**
     * END
     */
	/**
     * Paths validar estado
     * * 
     */
	public final String grilla="//div[@class='x-grid3-viewport']";
	/**
     * END
     */
}
