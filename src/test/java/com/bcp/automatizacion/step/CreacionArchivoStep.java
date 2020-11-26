package com.bcp.automatizacion.step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.bcp.automatizacion.page.GoogleDrivePage;
import com.bcp.automatizacion.util.Constantes;
import com.bcp.automatizacion.util.PageObjectUtil;

import net.thucydides.core.annotations.Step;

public class CreacionArchivoStep {

	private GoogleDrivePage appSasPage;
	private static Logger logger = LogManager.getLogger(GoogleDrivePage.class);

	
	@Step
	public void googleDrive() {
		logger.info(Constantes.strIniciando, "googleDrive");
		appSasPage.inicializar();
		appSasPage.googleDrive();
	}
	
	@Step
	public void login(String usuario, String clave) {
		logger.info(Constantes.strIniciando, "login");
		appSasPage.login(usuario, clave);
	}

	@Step
	public void crearDocumento() {
		logger.info(Constantes.strIniciando, "crearDocumento");
		appSasPage.crearDocumento();
	}
	
	@Step
	public void guardarDocumento() {
		logger.info(Constantes.strIniciando, "guardarDocumento");
		appSasPage.guardarDocumento();
	}
	
	@Step
	public void listaDocumentos() {
		logger.info(Constantes.strIniciando, "ListaDocumentos");
		List<String> listDocumentos=appSasPage.retornarDocumento();
		boolean blnEncontrado=false;
		for (String item: listDocumentos) {
			if(item.equals(appSasPage.getNombreDocumento()))blnEncontrado=true;
		}
		assertTrue(blnEncontrado);
		appSasPage.seleccionarDocumento(appSasPage.getNombreDocumento());
	}
}
