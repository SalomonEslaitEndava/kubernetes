def ciLastBuild = Jenkins.instance.getItem('rampup-CI').lastSuccessfulBuild.number
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
                sshagent(credentials: ['ssh-key-jenkins']){
                    sh """
                    ssh -o StrictHostKeyChecking=no -l salomon_antonio_eslait 10.0.1.30 'rm -r kubernetes'
                    ssh -o StrictHostKeyChecking=no -l salomon_antonio_eslait 10.0.1.30 'git clone https://github.com/SalomonEslaitEndava/kubernetes.git'
                    ssh -o StrictHostKeyChecking=no -l salomon_antonio_eslait 10.0.1.30 'pwd'
                    ssh -o StrictHostKeyChecking=no -l salomon_antonio_eslait 10.0.1.30 'cd kubernetes'
                    ssh -o StrictHostKeyChecking=no -l salomon_antonio_eslait 10.0.1.30 'sed -i 's/devopsrampup-front:latest/devopsrampup-front:${ciLastBuild}/' deploy-frontend.yaml'
                    ssh -o StrictHostKeyChecking=no -l salomon_antonio_eslait 10.0.1.30 'kubectlapply -f deploy-frontend.yaml'
                    ssh -o StrictHostKeyChecking=no -l salomon_antonio_eslait 10.0.1.30 'rm deploy-frontend.yaml'
                    """
                    // sh 'ssh salomon_antonio_eslait@10.0.1.26 git clone https://github.com/SalomonEslaitEndava/kubernetes.git'
                    // sh 'ssh salomon_antonio_eslait@10.0.1.26 pwd'
                }
            }
        }
    }
}   