package com.bcp.automatizacion.runner;

import org.junit.runner.RunWith;

import com.bcp.automatizacion.util.RunBefore;
import com.bcp.automatizacion.util.RunPersonalizar;
import cucumber.api.CucumberOptions;

@RunWith(RunPersonalizar.class)
@CucumberOptions(features = { "src/test/resources/features/CreacionArchivo.feature" }, tags = { "@CreacionArchivoWord" }, glue = { "com.bcp.automatizacion" })

public class Runner  {
    @RunBefore
    public static void previo() {
        //ExcelUtil.getInstancia().escribirFeatures();
    }
}