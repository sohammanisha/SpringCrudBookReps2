# Getting Started Backend.
1) Set up in any IDE (Eclipse, Spring Tool Suite) with Spring project initializr (https://start.spring.io/).
2) Set up maven (Download Maven from https://maven.apache.org/download.cgi). Set maven bin path in Enviornment Variables assuming JAVA_HOME for JAVA 17  is set.
3) Take or Clone source code from Git repository https://github.com/sohammanisha/SpringCrudBookReps2.git (atlernatively https://github.com/sohammanisha/SpringCrudBookReps2)
4) Unzip the code from https://github.com/sohammanisha/SpringCrudBookReps2.git in a directory, say demo. 
5) cd demo
5) run the command in for building and testing: mvn clean install .
6) run the comman to start the server mvn spring-boot:run
7) NOTE: as for git push creates a security issue with OPEN AI API KEY, before runnning the server, add spring.ai.openai.api-key=<Your OPEN AI KEY>
ai.api.key=<Your OPEN AI KEY>
ai.api.url=https://api.openai.com/v1/chat/completions in src/main/resources/application.properties file to intialize bean AIService.

# Getting Started Front end.
1) npx create-next-app@latest book-crud-frontend
2) cd book-crud-frontend
3) npm install axios tailwindcss
4) npx tailwindcss init -p
   a) if npx tailwindcss init -p give error then create the tailwind.config.js manually and add manually module.exports = {
  content: ["./pages/**/*.{js,ts,jsx,tsx}", "./components/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {},
  },
  plugins: [],
}; 
5) All the necesary frontend functionalitie are in book-crud-frontend which will be downloaded from git repository.
6) To run front end: go to 

	a) npm install -D @tailwindcss/postcss postcss autoprefixer
	b) npm run dev
	c) open the	URL http://localhost:3000/ in any brower, Chrome. 


# Expected Outputs:
1) User can add a valid book.
2) Book ISBN is unique.
3) User can edit.
4) User can delete a book.
5) User can search a book with either title or author or both.
6) GlobalExceptionHandler and JSR-303 Bean Validationhas been added in server.
# Not Supported right now:
1) As a free user of OPEN AI API, AI Search could not be added and tested properly becuase of limited access. So from the front end, AI Search is not included.