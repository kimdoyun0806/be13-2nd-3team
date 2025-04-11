pipeline {
    agent any

    environment {
        IMAGE_NAME = 'kimdoyun0806/yygang-api'
        TAG = '2.0'
        BUILD_DIR = 'yyGang'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                dir("${BUILD_DIR}") {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean build'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                sh """
                    docker build -t ${IMAGE_NAME}:${TAG} .
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push ${IMAGE_NAME}:${TAG}
                """
            }
        }

        stage('K8s Deploy to k3s') {
            steps {
                sh """
                    kubectl apply -f ./yygang-deploy.yaml
                    kubectl apply -f ./yygang-service.yaml
                """
            }
        }
    }
}
