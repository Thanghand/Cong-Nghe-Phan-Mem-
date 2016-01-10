- Cong Nghe Phan Mem:  FIFO
	* How to run back end:
		1/ Import sql script : 
		   + Open MySql Workpench:
			- Open Sql Script : fifo.sql and run script.
		2/ Build webservices: 
		   *cd WebServices 
		  (Open command line).	
			+ cd SmartChef_core:
				- mvn install
			+ cd HandlingWebService: 
				- mvn package
				- java -jar target/handlingws-0.0.1-SNAPSHOT.jar server config.yaml
			+ cd UserWebService:
				- mvn package
				- java -jar target/UserWebService-0.0.1-SNAPSHOT.jar server config.yaml.
			+ cd SearchingWebService:
				- mvn package
				-  java -jar target/SearchingWebService-0.0.1-SNAPSHOT.jar server config.yaml
		3/ Build server image using eclipse "Spring Tool Suite"
		  * cd WebServices: 
			+ Open Eclipse and import "image"
			   -> Run on server:
	* How to run Android Applcation: 
		cd  Mobile_user folder: 
		- Open Android Anstudio -> import project -> run it. 
		
			

		
