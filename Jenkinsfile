pipeline {
	environment {
	    registry = "nimo1975/selenium-docker"
	    registryCredential = 'dockerhub'
	    dockerImage = ''
	  }
    agent none
    stages {
        stage('Build Jar') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                script {
                	dockerImage = docker.build registry
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
			        docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
			        	dockerImage.push("${BUILD_NUMBER}")
			            dockerImage.push("latest")
			        }
                }
            }
        }
    }
}