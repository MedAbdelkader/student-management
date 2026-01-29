pipeline {
    agent any
    
    environment {
        DOCKERHUB_USERNAME = 'abdelkaderbouafoura'
        IMAGE_NAME = 'student-management'
        IMAGE_TAG = "${BUILD_NUMBER}"
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Récupération du code source depuis Git...'
                checkout scm
            }
        }
        

        stage('Unit Tests') {
            steps {
                echo 'Exécution des tests unitaires...'
                bat 'mvn clean test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

         stage('Build with Maven') {
                    steps {
                        echo 'Compilation du projet Spring Boot...'
                        //bat 'mvn clean package -DskipTests'
                        bat 'mvn -DskipTests package'
                    }
                }

        
        stage('Build Docker Image') {
            steps {
                echo 'Construction de l\'image Docker...'
                script {
                    bat """
                        docker build -t ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG} .
                        docker tag ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG} ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:latest
                    """
                }
            }
        }
        
        stage('Push Docker Image') {
            steps {
                echo 'Push de l\'image sur Docker Hub...'
                script {
                    bat """
                        docker login -u ${DOCKERHUB_CREDENTIALS_USR} -p ${DOCKERHUB_CREDENTIALS_PSW}
                        docker push ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}
                        docker push ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:latest
                        docker logout
                    """
                }
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline exécuté avec succès!'
            echo "Image Docker disponible: ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}"
        }
        failure {
            echo 'Le pipeline a échoué.'
        }
        always {
            bat """
                docker rmi ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG} || exit /b 0
                docker rmi ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:latest || exit /b 0
            """
        }
    }
}
