package com.bcp.automatizacion.util;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bcp.automatizacion.paths.AppPaths;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class PageObjectUtil {

	// singleton
	private static PageObjectUtil obj = null;
	
	private PageObjectUtil() {
	}

	public static PageObjectUtil getInstancia() {
		instanciar();
		return obj;
	}

	private synchronized static void instanciar() {
		if (obj == null) {
			obj = new PageObjectUtil();
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	// singleton
	public void seleniumSelect(WebDriver webDriver, final String path, String valor) {
		Select oSelect = new Select(webDriver.findElement(By.xpath(path)));
		List<WebElement> elementCount = oSelect.getOptions();
		int iSize = elementCount.size();
		int i = 0;
		String busca = "buscando";
		while (i <= iSize && busca.equals("buscando")) {
			String sValue = elementCount.get(i).getText().trim();
			if (sValue.equals(valor)) {
				busca = "encontro";
				elementCount.get(i).click();
				sleep(1);
			}
			i++;
		}
	}

	public void seleniumClick(WebDriver webDriver, final String path, int pos) {
		By by = By.xpath(path);
		int count = 60;
		JavascriptExecutor js = (JavascriptExecutor)webDriver;
		WebElement login = webDriver.findElement(By.xpath(path));
		js.executeScript("arguments[0].style.backgroundColor = 'yellow'" , login);
		
		while (count > 0) {
			List<WebElement> lista = webDriver.findElements(by);
			if (!lista.isEmpty()) {
				WebElement we = lista.get(pos);
				if (we.isDisplayed() && isClickable(we,webDriver)) {
					we.click();
					sleep(1.5);
					count = -1;
				}
			}
			count--;
		}
	}

	public  boolean isClickable(WebElement el, WebDriver driver) 
	{
		try{
			WebDriverWait wait = new WebDriverWait(driver, 6);
			wait.until(ExpectedConditions.elementToBeClickable(el));
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	public void seleniumClickJesus(WebDriver webDriver, final String path, int pos, int ejeX, int ejeY) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);
//			this.seleniumWaitPathClickable2(webDriver, we, 1000);

			Actions actions = new Actions(webDriver);
			actions.moveToElement(we, ejeX, ejeY).click().build().perform();
			sleep(1.5);
		}
	}
	
	public void seleniumButtonOpen(WebDriver webDriver, final String path, int pos, int ejeX, int ejeY) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);
			Actions actions = new Actions(webDriver);
			actions.moveToElement(we, ejeX, ejeY).click().build().perform();
			sleep(1.5);
		}
	}
	public void seleniumBDClick(WebDriver webDriver, final String path, int pos) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);
		Actions actions = new Actions(webDriver);
		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);

			if (we.isDisplayed()) {
				actions.doubleClick(we).build().perform();
				sleep(1.5);
			}
		}
	}

	public void seleniumEscribir(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);
			this.seleniumWaitPathClickable2(webDriver, we, 1000);

			if (we.isDisplayed()) {
				we.click();
				sleep(0.25);
				we.clear();
				we.sendKeys(valor);

				if (key != null) {
					sleep(1.50);
					we.sendKeys(key);
				}

				sleep(1);
			}
		}
	}

	public void seleniumWrite(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);
		JavascriptExecutor js = (JavascriptExecutor)webDriver;
		WebElement login = webDriver.findElement(By.xpath(path));
		js.executeScript("arguments[0].style.backgroundColor = 'yellow'" , login);
		
		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);
			this.seleniumWaitPathClickable2(webDriver, we, 1000);
			if (we.isDisplayed()) {
				we.click();
				sleep(1);
				we.clear();
				js.executeScript("arguments[0].value = '';", we);
				we.sendKeys(valor);
				sleep(2);
				if (key != null) {
					we.sendKeys(key);
				}
			}
		}
	}

	public void seleniumClicked(WebDriver webDriver, final String path, int pos) {

		By by = By.xpath(path);
		int count = 60;
		while (count > 0) {
			List<WebElement> lista = webDriver.findElements(by);
			if (!lista.isEmpty()) {
				WebElement we = lista.get(pos);
				if (we.isDisplayed() && isClickable(we,webDriver)) {
					we.click();
					sleep(1.5);
					count = -1;
				}
			}
			count--;
		}
	}

	public void seleniumDobleClicked(WebDriver webDriver, WebElement we) {

		Actions actions = new Actions(webDriver);
		actions.doubleClick(we).build().perform();
		sleep(0.5);
	}

	public void seleniumDobleClicked(WebDriver webDriver, String path) {
		sleep(0.25);
		int count = 6000;
		while (count > 0) {
			List<WebElement> witem = webDriver.findElements(By.xpath(path));
			if (!witem.isEmpty()) {
				Actions actions = new Actions(webDriver);
				actions.doubleClick(witem.get(0)).build().perform();
				sleep(0.5);
				count=-1;
			}
			count--;
		}
	}

	public void seleniumWaitPathAppear(WebDriver webDriver, String path, int time) {
		WebDriverWait wait = new WebDriverWait(webDriver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
		sleep(1);
	}

	public void seleniumWaitPathClickable(WebDriver webDriver, String path, int time) {
		WebDriverWait wait = new WebDriverWait(webDriver, time);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
	}

	public void seleniumWaitPathClickable2(WebDriver webDriver, WebElement path, int time) {
		WebDriverWait wait = new WebDriverWait(webDriver, time);
		wait.until(ExpectedConditions.elementToBeClickable(path));
	}

	public String seleniumGrillaText(WebDriver webDriver, String grilla, int pos, int row, int positionRow) {
		WebElement weGrilla = this.seleniumWebElement(webDriver, grilla, pos);
		List<WebElement> table = weGrilla.findElements(By.tagName("table"));
		List<WebElement> tr = table.get(row).findElements(By.tagName("tr"));
		List<WebElement> td = tr.get(0).findElements(By.tagName("td"));
		List<WebElement> we = td.get(positionRow).findElements(By.tagName("div"));
		we.get(0).click();
		return we.get(0).getText();
	}

	public void seleniumWriteWithOutClic(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);

			if (we.isDisplayed()) {
				sleep(0.25);
				we.clear();
				we.sendKeys(valor);

				if (key != null) {
					sleep(1.50);
					we.sendKeys(key);
				}

				sleep(1);
			}
		}
	}

	public void seleniumEscribirSinClear(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);

			if (we.isDisplayed()) {
				we.click();
				sleep(0.25);
				we.sendKeys(valor);

				if (key != null) {
					sleep(1.50);
					we.sendKeys(key);
				}
			}
		}
	}

	public void seleniumEscribirEspecial(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);

			if (we.isDisplayed()) {
				we.click();
				sleep(0.25);
				we.sendKeys(Keys.HOME);
				for (int i = 0; i < 20; i++) {
					we.sendKeys(Keys.DELETE);
				}

				we.sendKeys(valor);

				if (key != null) {
					sleep(1.50);
					we.sendKeys(key);
				}

				sleep(1);
			}
		}
	}

	public void seleniumEscribirEnCampos(WebDriver webDriver, final String path1, final String path2, int posDiv,
			int pos, String val, Keys key) {
		By by = By.xpath(path1);
		By by2 = By.xpath(path2);
		List<WebElement> listar = webDriver.findElements(by);

		if (!listar.isEmpty()) {
			List<WebElement> we = listar.get(posDiv).findElements(by2);

			if (!we.isEmpty()) {
				WebElement ww = we.get(pos);

				if (ww.isDisplayed()) {
					ww.click();
					sleep(0.25);
					ww.clear();
					ww.sendKeys(val);

					if (key != null) {
						sleep(1.50);
						ww.sendKeys(key);
					}

					sleep(1);
				}
			}
		}
	}

	public void seleniumEscribir2(WebDriver webDriver, final String path, int pos, String valor, Keys key) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);

			if (we.isDisplayed()) {
				we.click();
				sleep(0.25);
				we.clear();
				we.sendKeys(valor);

				if (key != null) {
					sleep(3);
					we.sendKeys(key);
				}

				sleep(1);
			}
		}
	}

	public int cantidadVentanas(WebDriver webDriver) {
		List<String> lista = new ArrayList<String>(webDriver.getWindowHandles());

		return lista.size();
	}

	public void cambiarVentana(WebDriver webDriver, int pos) {
		sleep(3);
		List<String> lista = new ArrayList<String>(webDriver.getWindowHandles());

		if (!lista.isEmpty()) {
			webDriver.switchTo().window(lista.get(pos));
		}
	}

	public void seleccionarCombo(WebDriver webDriver, final String path, String valor) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		for (WebElement we : lista) {
			if (we.getText().trim().equals(valor)) {
				we.click();
				break;
			}
		}
	}

	public boolean buscarDom(WebDriver webDriver, final String path, int pos) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);
		WebElement we = null;

		if (!lista.isEmpty()) {
			we = lista.get(pos);

			if (we.isDisplayed()) {
				return true;
			}
		}

		return false;
	}

	public WebElement seleniumWebElement(WebDriver webDriver, final String path, int pos) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {

			return lista.get(pos);
		}

		return null;
	}

	public WebElement seleniumWebElementLast(WebDriver webDriver, final String path) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);
		int i = lista.size() - 1;

		if (!lista.isEmpty()) {
			this.seleniumWaitPathClickable2(webDriver, lista.get(i), 1000);
			return lista.get(i);
		}

		return null;
	}

	public WebElement seleniumWebElementpos(WebDriver webDriver, final String path, int pos) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			return lista.get(pos);
		}

		return null;
	}

	public void sleep(double seg) {
		long miliseg = (new Double(seg * 1000)).longValue();

		try {
			// System.out.println("sleep:\t\t" + seg + " seg... <--> " + miliseg +
			// "miliseg...");
			Thread.sleep(miliseg);
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	public List<WebElement> seleniumListaByXpath(WebDriver webDriver, final String path) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		return lista;
	}

	public List<WebElement> seleniumListaByTagName(WebDriver webDriver, final String path) {
		By by = By.tagName(path);
		List<WebElement> lista = webDriver.findElements(by);

		return lista;
	}

	public int seleniumPosicionDom(WebDriver webDriver, final String path, String valor) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getText().equals(valor)) {
				return i;
			}
		}

		return 0;
	}

	public void seleniumClear(WebDriver webDriver, final String path, int pos) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);

			if (we.isDisplayed()) {
				we.clear();
				sleep(0.5);
			}
		}
	}

	public void seleniumDobleClick(WebDriver webDriver, WebElement we) {
		Actions actions = new Actions(webDriver);
		actions.doubleClick(we).build().perform();
		sleep(0.25);
	}

	public WebElementFacade serenityWebElement(PageObject page, final String path, int pos) {
		List<WebElementFacade> lista = page.findAll(path);
		sleep(3);

		if (!lista.isEmpty()) {
			return lista.get(pos);
		}

		return null;
	}

	public void seleniumClickJesus(WebDriver webDriver, final String path, int pos) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);
			Actions actions = new Actions(webDriver);
			int clickAtX = 130;
			int clickAtY = 0;

			actions.moveToElement(we, clickAtX, clickAtY).click().build().perform();
			sleep(1.5);
		}
	}

	public void cargando(WebDriver webDriver, WebDriverWait wdw, final String path) {
		System.out.print("cargando:");
		long t1 = System.currentTimeMillis();
		By by = By.xpath(path);
//		this.seleniumWaitPathClickable(webDriver, path,1000); 
//		List<WebElement> lista = webDriver.findElements(by);
//
//		if (!lista.isEmpty()) {
//			for (int i = 0; i < 2; i++) {
//				try {
//					while (!wdw.until(ExpectedConditions.invisibilityOfElementLocated(by))) {
//						System.out.println("cargando...");
//					}
//				} catch (Exception ex) {
//					System.out.println(ex.getMessage());
//					ex.getStackTrace();
//				}
//
//				sleep(1);
//			}
//		}

		webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		while (true) {
			if (wdw.until(ExpectedConditions.invisibilityOfElementLocated(by))) {
				break;
			}
		}
		sleep(2);

		webDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
		long t2 = System.currentTimeMillis();
		System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
	}

	public void loading(WebDriver webDriver, final String path, double time) {
		System.out.print("loading:");
		sleep(2.5);
		long t1 = System.currentTimeMillis();
		WebDriverWait wait = new WebDriverWait(webDriver, 900);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(path)));
		sleep(time);
		long t2 = System.currentTimeMillis();
		System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
	}

	public void cargandoAdvanced(WebDriver webDriver, WebDriverWait wdw, final String path) {
		System.out.print("cargando:");
		long t1 = System.currentTimeMillis();
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			for (int i = 0; i < 2; i++) {
				try {
					while (!wdw.until(ExpectedConditions.invisibilityOfElementLocated(by))) {
						System.out.println("cargando...");
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					ex.getStackTrace();
				}
			}
		}

		long t2 = System.currentTimeMillis();
		System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
	}

	public void cargando2(WebDriver webDriver, final String path, int cantidad, long wdwTimeOut) {
		System.out.print("cargando 2:");
		long t1 = System.currentTimeMillis();
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		/*
		 * while (lista.size() == pos) { lista = webDriver.findElements(by); sleep(1); }
		 */

		for (int a = 0; a < 2; a++) {
			for (int i = 0; i < (int) wdwTimeOut; i++) {
				sleep(1);

				if (lista.size() == cantidad) {
					lista = webDriver.findElements(by);
				} else {
					break;
				}
			}

			sleep(1);
		}

		long t2 = System.currentTimeMillis();
		System.out.println("\t" + ((t2 - t1) / 1000d) + " seg...");
	}

	public void toolkitPegarEnWebElement(WebDriver webDriver, final String path, int pos, String valor, int keyEvent) {
		By by = By.xpath(path);
		List<WebElement> lista = webDriver.findElements(by);

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);

			if (we.isDisplayed()) {
				we.click();
				sleep(0.25);
				we.clear();
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(valor), null);

				try {
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);

					if (keyEvent != 0) {
						sleep(1.50);
						robot.keyPress(keyEvent);
						robot.keyRelease(keyEvent);
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}

				sleep(1);
			}
		}
	}

	public void escribirEnObjetosHidden(WebDriver webDriver, final String pathHidden, final String pathInput, int pos,
			String valor) {
		WebElement elemHidden = webDriver.findElement(By.xpath(pathHidden));
		String js = "arguments[0].style.visibility='visible'";
		((JavascriptExecutor) webDriver).executeScript(js, elemHidden);
		List<WebElement> lista = webDriver.findElements(By.xpath(pathInput));

		if (!lista.isEmpty()) {
			WebElement we = lista.get(pos);
			we.sendKeys(Keys.HOME, Keys.DELETE, Keys.DELETE, Keys.DELETE);
			we.sendKeys(valor, Keys.ENTER);
			sleep(3);
		}
	}

	public WebElement seleniumBuscarDom(List<WebElement> lista, String valor) {
		for (WebElement we : lista) {
			if (we.getText().trim().equals(valor)) {
				return we;
			}
		}

		return null;
	}

	

}