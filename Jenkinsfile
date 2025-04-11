pipeline {
    agent {
        kubernetes {
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: gradle
    image: gradle:8.5-jdk21-alpine
    command:
    - cat
    tty: true
"""
        }
    }
    environment {
        IMAGE_NAME = 'kimdoyun0806/yygang-api'
        TAG = 'latest'
    }

    stages {
        stage('Build') {
            steps {
                container('gradle') {
                    dir('yyGang') {
                        sh 'chmod +x ./gradlew'
                        sh './gradlew clean build'
                    }
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                container('gradle') {
                    sh '''
                        docker build -t $IMAGE_NAME:$TAG .
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push $IMAGE_NAME:$TAG
                    '''
                }
            }
        }

        stage('K8s Deploy') {
            steps {
                container('gradle') {
                    sh '''
                        kubectl apply -f ./yygang-deploy.yaml
                        kubectl apply -f ./yygang-service.yaml
                    '''
                }
            }
        }
    }
}
