pipeline {
    agent {
        docker {
            image 'maven:3-jdk-12-alpine'
            args '-v /root/.m2:/root/.m2' 
        }
    }

    stages {
        stage('Build') { 
            steps {
                sh './jenkins/scripts/build.sh' 
            }
        }
        stage('Deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh' 
            }
        }
    }
}
