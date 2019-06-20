pipeline{
        agent any
        stages{
                stage('---clean---'){
                        steps{
                                sh "mvn clean"
                        }
                }
                stage('--test--'){
                        steps{
                                sh "mvn test"
                        }
                }
				stage('--webapp--'){
						steps{
								sh "sudo cp -r /home/h_a_buck_1995/BAESoloProjectFrontEnd/* /var/lib/jenkins/workspace/SoloProjectPipe/src/main/webapp"
						}
				}
				stage('--package--'){
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
				sh "sudo cp -r /var/lib/jenkins/workspace/SoloProjectPipe/target/BAESoloProject.war /home/h_a_buck_1995/wildfly-10.1.0.Final/standalone/deployments/"
                        }
                }
        }
}