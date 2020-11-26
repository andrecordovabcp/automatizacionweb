pipeline {
    agent any
    tools {
        maven 'M3'
        jdk 'jdk8.221'
    }
    stages {
	
        stage ('Build') {
            steps {
                sh ("mvn -X clean verify")
            }
            
        }
        stage('Ejecutar Pruebas'){
                steps{
                    script{
                        try{
                              sh ("mvn verify package -P BCP")
                             echo 'Ejecucion de pruebas sin errores'
                        }
                        catch(ex)
                        {    
                            echo 'Finalizo ejecucion con fallos'
                            error('Failed')
                        }
                    }
                }
                
            }
            
   /*            stage('Reporte'){
            steps{
                script{ 
                    try{
                        publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: "\\var\\jenkins_home\\workspace\\Pruebas_Automatizadas\\target\\site\\serenity${defTimestamp}", reportFiles: 'index.html', reportName: 'Evidencias de Prueba', reportTitles: ''])
                        echo 'Reporte realizado con exito'
                    }
                    catch(ex)
                    {    
                        echo 'Reporte realizado con Fallos'
                        error('Failed')
                    }
                }
            }
        }*/
    }
}