pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test -Dspring.profiles.active=test-jenkins"
            }
        }
        stage('Deploy') {
            steps {
                sh "mvn clean heroku:deploy -Dspring.profiles.active=test-jenkins"
            }
        }
    }
}
