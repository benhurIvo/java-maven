pipeline {
    agent any
    tools {
        maven 'M3'
    }

    stages {
        stage('Prepare') {
            steps {
                checkout scm
            }
        }


        stage('Build') {
        // Run the maven build
        steps {
                sh 'mvn -Dmaven.test.failure.ignore clean package'
            } 
        }
    
    stage('Unit Tests') {   
        steps {
            
                sh "mvn clean test"
            
        }
    }

    //Code quality
    stage('SonarCloud') {
        environment {
    SCANNER_HOME = tool 'SonarQubeScanner'
    ORGANIZATION = "benhurivo"
    PROJECT_NAME = "java-maven"
                }
    steps {
    withSonarQubeEnv(installationName: 'SonarCloudOne', credentialsId: 'SonarCloudOne') {
        sh '''$SCANNER_HOME/bin/sonar-scanner -Dsonar.organization=$ORGANIZATION \
        -Dsonar.java.binaries=. \
        -Dsonar.projectKey=$PROJECT_NAME \
        -Dsonar.sources=.'''
                                    }
                                    }
                                }
                                
    /*stage('Deploy') {
        steps {
                sh "mvn clean install -Ddocker"
                sh "docker run -p 8090:8090 my_code"
            
        }
    }*/

    /*stage('Deploy Testing') {
        steps {
                sh "mvn test 'seleniumTest'"
            
        }
    }*/
}
}
