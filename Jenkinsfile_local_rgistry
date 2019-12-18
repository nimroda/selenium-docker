pipeline {
	environment {
	    registry = "IL02VLAPP5004.cfrm.dev.local:5000/selenium-docker:latest"
	  //  registryCredential = 'dockerhub'
	    dockerImage = 'selenium-docker'
	  }
    agent { label 'DOCKER_SLAVE' }
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
        stage('Build & Push trunk platform image'){
		steps {
		  script {
    		dockerImage.push()
			}
		  }	
        }
//        stage('Push Image') {
//            steps {
//                script {
//			        docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
//			        	dockerImage.push("${BUILD_NUMBER}")
//			            dockerImage.push("latest")
//			        }
//               }
//            }
//        }
    }
}