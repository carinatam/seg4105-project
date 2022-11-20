pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                bat 'gradle assemble'
            }
        }
         stage('Test') {
            steps {
                bat 'gradle test'
            }
        }
        stage('Build Docker Image') {
            steps {
                bat 'gradle docker'
            }
        }
        stage('Run Docker Image') {
            steps {
                bat 'gradle dockerRun'
            }
        }
    }
}