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
            
                sh "mvn -B clean test"
                stash name: "unit_tests", includes: "target/surefire-reports/**"
                jacoco()
            
        }
    }

    /*stage('Integration Tests') {
        steps {
                sh "mvn -B clean verify -Dsurefire.skip=true"
                stash name: 'it_tests', includes: 'target/failsafe-reports/**'
            
        }
    }*/

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
/*stage("Quality Gate") {
  steps {
    timeout(time: 5, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }
  }
}*/
}
}
