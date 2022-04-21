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
                sshagent(credentials: ['ssh-key']){
                    sh '''
                    ssh -o StrictHostKeyChecking=no -l salomon_antonio_eslait 10.0.1.22 'touch sobeloenlaotramaquina.txt'
                    
                    '''
                    // sh 'ssh salomon_antonio_eslait@10.0.1.26 git clone https://github.com/SalomonEslaitEndava/kubernetes.git'
                    // sh 'ssh salomon_antonio_eslait@10.0.1.26 pwd'
                }
            }
        }
    }
}   