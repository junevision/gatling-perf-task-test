pipeline {
    agent any

    stages {
        stage("Build Maven") {
            steps {
                sh '~/Documents/apache-maven-3.8.1/bin/mvn -B clean package'
            }
        }

        stage('Pull Latest Code') {
            steps {
                git branch: 'master',
                url: 'https://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@github.com/junevision/gatling-perf-task-test.git'
            }
        }

        stage("Run Gatling") {
            steps {
                sh '~/Documents/apache-maven-3.8.1/bin/mvn gatling:test'
            }
            post {
                always {
                    publishers {
                            archiveGatling()
                        }
                }
            }
        }
    }
}