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
                sh "mvn test"
            }
        }
        stage('Deploy') {
            steps {
//                 sh "mvn clean package heroku:deploy-war -Dheroku.appName=kkgamingblog"
                    sh "java $JAVA_OPTS -jar webapp-runner.jar ${WEBAPP_RUNNER_OPTS} --port $PORT ./target/GamingBlog-0.0.1-SNAPSHOT.war"
            }
        }
    }
}
