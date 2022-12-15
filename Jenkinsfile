pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn clean compile -Dspring.profiles.active=test"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test -Dspring.profiles.active=test"
            }
        }
        stage('Deploy') {
            steps {
                sh "mvn clean heroku:deploy"
            }
        }
    }
}
