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
    
    //stage('Results') {
    //    junit '**/target/surefire-reports/TEST-*.xml'
    //    archiveArtifacts 'target/*.jar'
    //}

    stage('SonarCloud') {
        environment {
    SCANNER_HOME = tool 'SonarQubeScanner'
    ORGANIZATION = "benhurivo"
    PROJECT_NAME = "java-maven"
                }
    steps {
    withSonarQubeEnv(installationName: 'SonarCloudOne', credentialsId: 'SonarCloudOne') {
        sh 'ls'
        sh '''$SCANNER_HOME/bin/sonar-scanner -Dsonar.organization=$ORGANIZATION \
        -Dsonar.java.binaries=build/classes/java/ \
        -Dsonar.projectKey=$PROJECT_NAME \
        -Dsonar.sources=.'''
                                    }
                                    }
                                }
stage("Quality Gate") {
  steps {
    timeout(time: 1, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }
  }
}
}
}
