package com.bcp.automatizacion.page;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bcp.automatizacion.paths.AppPaths;
import com.bcp.automatizacion.util.Constantes;
import com.bcp.automatizacion.util.PageObjectUtil;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
@DefaultUrl(Constantes.url)
public class GoogleDrivePage extends PageObject {

	protected PageObjectUtil pageObjectUtil = PageObjectUtil.getInstancia();
	private WebDriverWait wdw = null;
	private long wdwTimeOut = 300L;
	private static Logger logger = LogManager.getLogger(GoogleDrivePage.class);
	Actions actions;
	private String nombreDocumento;
	
	public long getWdwTimeOut() {
		return wdwTimeOut;
	}

	public void inicializar() {
		logger.info(Constantes.mensajeini + "inicializar ");
		getDriver().get(Constantes.url);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
		logger.info(Constantes.mensajefin, " inicializar ");

	}

	protected WebDriverWait getWDW() {
		logger.info(Constantes.mensaje, "getWDW ");
		if (wdw == null) {
			wdw = new WebDriverWait(getDriver(), wdwTimeOut, 1L);
		}

		return wdw;
	}

	public void googleDrive() {
		logger.info(Constantes.mensaje, "googleDrive");
		
		pageObjectUtil.seleniumClick(getDriver(), AppPaths.btnGoogleApps, 0);
		Serenity.takeScreenshot();
		WebElement iFrame= getDriver().findElement(By.tagName("iframe")); 
		getDriver().switchTo().frame(iFrame);
		pageObjectUtil.seleniumClick(getDriver(), AppPaths.btnDrive, 0);
		pageObjectUtil.seleniumWaitPathAppear(getDriver(), AppPaths.btnIrDrive, 1000);
		Serenity.takeScreenshot();
		pageObjectUtil.seleniumClick(getDriver(), AppPaths.btnIrDrive, 0);
		Set <String> handles =getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();
		while (it.hasNext()){
			String newwin = it.next();
			getDriver().switchTo().window(newwin);
		}	
		Serenity.takeScreenshot();
	}
	
	public void login(String usuario, String clave) {
		logger.info(Constantes.mensaje, "login");
		pageObjectUtil.seleniumWaitPathAppear(getDriver(), AppPaths.spanCorreo, 1000);
		pageObjectUtil.seleniumWrite(getDriver(), AppPaths.txtcorreo, 0, usuario, Keys.ENTER);
		pageObjectUtil.seleniumWrite(getDriver(), AppPaths.txtClave, 0, clave, Keys.ENTER);
		Serenity.takeScreenshot();
	}
	
	public void crearDocumento() {
		logger.info(Constantes.mensaje, "crearDocumento");
		pageObjectUtil.seleniumClick(getDriver(), AppPaths.btnNuevo, 0);
		pageObjectUtil.seleniumClick(getDriver(), AppPaths.btnDocumento, 0);	
		Set <String> handles =getDriver().getWindowHandles();
		Iterator<String> it = handles.iterator();
		while (it.hasNext()){
			String newwin = it.next();
			getDriver().switchTo().window(newwin);
		}
		Serenity.takeScreenshot();
	}
	
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	
	public void guardarDocumento() {
		logger.info(Constantes.mensaje, "guardarDocumento");
		LocalDateTime fechaHora= LocalDateTime.now();
		String nombre="E01_Andre_Cordova_Pacheco_"+fechaHora;
		setNombreDocumento(nombre);
		pageObjectUtil.seleniumClick(getDriver(), AppPaths.btnArchivo, 0);
		pageObjectUtil.seleniumClick(getDriver(), AppPaths.btnCambiarNombre, 0);	
		pageObjectUtil.seleniumWrite(getDriver(), AppPaths.inpTituloDocumento, 0, nombre, Keys.ENTER);
		pageObjectUtil.seleniumWaitPathAppear(getDriver(), AppPaths.txtGuardando, 1000);
		Serenity.takeScreenshot();

	}
	
	public List<String> retornarDocumento() {
		List<String> windowHandles = new ArrayList<>(getDriver().getWindowHandles());
		getDriver().switchTo().window(windowHandles.get(1));
		List<String> listnombres=new ArrayList<String>();
		List<WebElement> listdocumentos= getDriver().findElements(By.xpath(AppPaths.tblDocumentos));
		for (WebElement webElement : listdocumentos) {
			listnombres.add(webElement.getText());
		}
		Serenity.takeScreenshot();

		return listnombres;
	}
	
	public void seleccionarDocumento(String nombre) {
		String documento= AppPaths.txtDocumento.replace("documento", nombre);
		pageObjectUtil.seleniumClick(getDriver(), documento, 0);
		Serenity.takeScreenshot();
	}
	
}
