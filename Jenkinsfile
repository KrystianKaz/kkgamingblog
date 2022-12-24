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
                sh "mvn test package"
            }
        }
        stage('Deploy') {
            steps {
                sh "heroku war:deploy --war target/GamingBlog-0.0.1-SNAPSHOT.war -Dheroku.appName=kkgamingblog"
            }
        }
    }
}
