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
                //sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Analyse du code avec SonarQube...'
                withSonarQubeEnv('Sonar') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Build with Maven') {
            steps {
                echo 'Compilation du projet Spring Boot...'
                //sh 'mvn -DskipTests package'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Construction de l\'image Docker...'
                script {
                    // Utilisation de doubles guillemets pour l'interpolation des variables
                    //sh "docker build -t ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG} ."
                    //sh "docker tag ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG} ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:latest"
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                echo 'Push de l\'image sur Docker Hub...'
                script {
                    // Les variables _PSW et _USR sont injectées par Jenkins via credentials()
                    //sh "echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
                    //sh "docker push ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}"
                    //sh "docker push ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:latest"
                    //sh "docker logout"
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline exécuté avec succès!'
            //echo "Image Docker disponible: ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG}"
        }
        failure {
            echo 'Le pipeline a échoué.'
        }
        always {
            script {
                // Nettoyage avec doubles guillemets
                //sh "docker rmi ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:${IMAGE_TAG} || true"
                //sh "docker rmi ${DOCKERHUB_USERNAME}/${IMAGE_NAME}:latest || true"
            }
        }
    }
}