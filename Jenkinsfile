pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'javac -d bin src\\*.java'
            }
        }
    }
}
