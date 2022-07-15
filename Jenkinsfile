pipeline {
    agent any
    tools {
        maven 'Maven 3.8.4'
        jdk 'OpenJdk11'
    }
    stages {
        stage ('Test') {
            steps {    
                sh " su -c 'mvn clean test' jenkins"
            }
        }
        stage ('Build') {
            steps {    
                sh " su -c 'mvn clean install -DskipTests' jenkins"
            }
        }
        stage ('Build image') {
            steps {
                sh ' docker login --username AWS -p $(aws ecr get-login-password --region sa-east-1) 844921857298.dkr.ecr.sa-east-1.amazonaws.com'
                sh ' docker build . -t 844921857298.dkr.ecr.sa-east-1.amazonaws.com/ido-business:${BUILD_NUMBER}'
                sh ' docker push 844921857298.dkr.ecr.sa-east-1.amazonaws.com/ido-business:${BUILD_NUMBER}'
            }
        }
        stage ('Deploy') {
            steps {
                script{
                if(env.BRANCH_NAME == 'develop' || env.BRANCH_NAME == 'deploy' ){
                    sh 'helm upgrade -i -f ./etc/helm/dev-values.yaml --set image.tag=${BUILD_NUMBER} ido-business --namespace=dev ./etc/helm/'
                }
                else if(env.BRANCH_NAME == 'release/hlg' ){
                    sh 'helm upgrade -i -f ./etc/helm/hlg-values.yaml --set image.tag=${BUILD_NUMBER} ido-business --namespace=hlg ./etc/helm/'
                }
                //else if(env.BRANCH_NAME == 'master' ){
                //    sh 'helm upgrade --install -f etc/prd-values.yaml --set image.tag=${BUILD_NUMBER} ido-website --namespace=defult etc/'
                //}
                }
            }
        } 
}
}
