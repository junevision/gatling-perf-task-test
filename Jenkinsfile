pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
        jdk 'Java 11.0.14'
    }

    stages {
        stage("Build Maven") {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stage('pullLatestCode') {
            steps {
                git branch: 'master',
                url: 'https://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@github.com/junevision/gatling-perf-task-test.git'
            }
        }

        stage("Run Gatling") {
            steps {
                sh 'mvn gatling:test'
            }
            post {
                always {
                    gatlingArchive()
                }
            }
        }
    }
}