pipeline {
  agent any

  tools {nodejs "nodeJS"}

  stages { 
    stage('Clean old tar') {
      steps {
        bat 'del *.tar.gz'
      }
    }   
    stage('Cloning Git') {
      steps {
        git branch: 'main', url: 'https://github.com/dattatrey29/first.git'
      }
    }        
    stage('Install dependencies') {
      steps {
        bat 'npm install'
      }
    } 
    stage('Package') {
        steps {
            bat 'tar -czvf first.%BUILD_ID%.tar.gz *'
        }
    }
    stage('Archive') {
        steps {
            archiveArtifacts artifacts: '*.tar.gz', followSymlinks: false
        }
    }
  }
}