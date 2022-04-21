pipeline {
    environment {
        registry = "salomoneslaitendava/devopsrampup-front"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }
    agent any
    stages {
        stage('deploy'){
            steps {
                sh 'echo "hola "'
                // sshagent(credentials: ['ssh-key']){
                //     sh 'ssh salomon_antonio_eslait@10.0.1.22 git clone https://github.com/SalomonEslaitEndava/kubernetes.git'
                // }
            }
        }
    }
}   