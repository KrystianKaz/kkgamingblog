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
        stage('Deploy') {
            steps {
                sh "mvn clean heroku:deploy"
            }
        }
    }
}
