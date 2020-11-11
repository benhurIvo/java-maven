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

        stage('Test') {
            steps {
                sh 'mvn clean install'
            }
        }

    stage('SonarCloud') {
  environment {
    SCANNER_HOME = tool 'SonarQubeScanner'
    ORGANIZATION = "benhurIvo"
    PROJECT_NAME = "java-maven"
                }
  steps {
    withSonarQubeEnv(installationName: 'SonarCloudOne', credentialsId: 'SonarCloudOne') {
        sh '''$SCANNER_HOME/bin/sonar-scanner -Dsonar.organization=$ORGANIZATION \
        -Dsonar.java.binaries=build/classes/java/ \
        -Dsonar.projectKey=$PROJECT_NAME \
        -Dsonar.sources=.'''
                                    }
                                    }
                                }
stage("Quality Gate") {
  steps {
    timeout(time: 30, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }
  }
}
}
}
