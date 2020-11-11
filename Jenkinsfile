pipeline {
    agent any
    //worked yes no no hmm aww worked
    //xattr -r -d com.apple.quarantine geckodriver
    def mvnHome
    stage('Preparation') { // for display purposes
        // Get some code from a GitHub repository
        git 'https://github.com/benhurIvo/java-maven.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'M3'
    }
    stage('Build') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
            }
        }
    }
    stage('Unit Tests') {
        junit '**/target/surefire-reports/TEST-*.xml'
        archiveArtifacts 'target/*.jar'
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
