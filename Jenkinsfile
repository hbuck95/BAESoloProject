pipeline{
        agent any
        stages{
                stage('---clean---'){
                        steps{
                                sh "mvn clean"
                        }
                }
				stage('--bundle webapp--'){
						steps{
								sh "sudo cp -r /home/h_a_buck_1995/BAESoloProjectFrontEnd/* /var/lib/jenkins/workspace/${JOB_NAME}/src/main/webapp"
						}
				}
				stage('--test and package--'){
                        steps{
                                sh "mvn package"
                        }
                }
				stage('--sonar--'){
                        steps{
                                sh "mvn sonar:sonar"
                        }
                }
				stage('--verify--'){
                        steps{
                                sh "mvn verify"
                        }
                }
				stage('--surefire--'){
                        steps{
                                sh "mvn surefire-report:report"
								sh "mvn site"
                        }
                }
				stage('--deploy--'){
                        steps{
								sh "sudo cp -r /var/lib/jenkins/workspace/${JOB_NAME}/target/*.war /home/h_a_buck_1995/wildfly-10.1.0.Final/standalone/deployments/"
                        }
                }
        }
}