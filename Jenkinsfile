pipeline {
    agent any

    stages{
        stage('Build demo') {
            when{
                branch 'demo'
            }
            agent {
                docker {
                    image 'maven17-build'
                    args '-v $HOME/.m2:/root/.m2'

                }
            }
          steps {
              sh '''
                 mvn  clean install  -DskipTests -B
                 apt-get install -y unzip
                 mkdir -p target/dependency && (cd target/dependency; unzip ../*.jar)
                 docker build -t shal/dev .
              '''
          }
        }

        stage('run app demo'){
          when{
             branch 'demo'
          }
          steps{
              sh '''
                  rm -rf shal-dev
                  mkdir shal-dev && cp  deploy/dev-release/docker-compose.yml shal-dev/docker-compose.yml
                  cd shal-dev
                  cat docker-compose.yml

                  docker-compose down  --remove-orphans
                  docker-compose up -d
              '''
          }
        }
่   }
}