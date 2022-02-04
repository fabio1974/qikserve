The repository contains two folders: the backend is a Maven SpringBoot project, and the front-end is a React application. I stored all the data in a remote Postgres database on Heroku. This application is not secure, and all database settings are already set in the application.properties file (not recommended, of course, but it is just a demo application) 

To put them running, you need:

1. Clone the repository: 

  ##### `git clone git@github.com:fabio1974/qikserve.git`

2. Go to the /backend folder in the terminal and run the following three commands:

  ##### `mvn test`
  ##### `mvn package`
  ##### `java -jar target/qikserve-0.0.1-SNAPSHOT.jar` 

3. To run the React frontend, open another terminal window, go to /frontend folder, then run:

  ##### `npm install`
  ##### `npm start`

If you run the frontend before the backend, don't forget to refresh the page after launching the backend to see the list of products from the database.

4. Go to http://localhost:3000/
