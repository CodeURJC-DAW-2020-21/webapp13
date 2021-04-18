# Porthub
![Logo](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/logo.png)

## Description
Porthub is a social network about sharing your personal portfolios to meet profesionals from many different ambits. 

## Team

### Organization tools
To keep track on the updates that we made to the project we are going to use __GitHub Projetcs__ with the agile methodology __Kanban__. You can check the state of the project [here](https://github.com/CodeURJC-DAW-2020-21/webapp13/projects/1).

### Members
| Name | Email | Github Username|
| ---  | --- |	---	|
| Cristian de Gracia Nuero | c.degracia.2018@alumnos.urjc.es | CrisDgrnu  |
| José Justo Tena Agudo | jj.tena.2018@alumnos.urjc.es | jj-tena |
| José Manuel Aguado Gala | jm.aguado.2018@alumnos.urjc.es | JMAguado |
| Aitor Ortega Pacheco | a.ortega.2018@alumnos.urjc.es | aitorortega03 |

## Phase 0

### Entities
The system has 4 entities:
- __User:__ Represents an user profile having it´s whole data.
- __Template:__ Contains a visual representation of the user information and his selected projects.
- __Portfolio item:__ Contains detailed information about a project. The user can owns more than one.
- __Message:__ It is the minimun unit of communication between users, composed by the emisor, the receiver, the content and the time in which it was sent and received.

### User permission
The system has 3 user modes:
- __Administrator:__ The admin user can manage the other users permissions and add or delete the content of the store.
- __Normal:__ The normal user can chat with other users and create his own free or premium portfolio.
- __Invited:__ The invited user only can see other users portfolios, but he can't chat with other users and create his own portfolio.

### Template info
The portfolio reflects your work and experience in a particular knowledge field.
The portfolio shall contain the user´s name, email, occupation and examples of its work. Also it may have pictures of itself and its work.

### Portfolio item
Portfolio item's represents a single project. You can have more than one portfolio item to show your different projects. A portfolio item has a tittle, a photo, a description and some useful information about the project.

### Images
The user may be able to upload pictures of itself and examples of its work in the portfolio, in order to make the portfolio looks cleaner and being easily recognizable.
The user´s profile can contain a picture of the user.

### Charts
- __Registered users__: This chart shows the relation between the users register and the days in which the accounts were made.
- __Most valuable templates__: This chart shows the most valuables templates rated by the users.

### Complementary technologies
For the complementary technology we will use Web Sockets to implement a chat between the users of our system.

### Advanced queries
We will implement an algorithm that recommends users which have similar interests or types of work.

## Phase 1

## Pages
During this phase we have worked defining the frontend of our web application, as a result of this work we can show you an example of the screens of our system.


### Home Page
The initial page where you have a general overview of the system.

![Home](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/index.png)


### Sign Page
The screen used to register a new user on our system.

![Sign](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/sign.png)


### Login Page
The screen used to login an existing user on the system.

![Login](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/login.png)

### Profile Page

This screen shows you the active template which represents your profile in the system.

![Profile](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/portfolioPremium.PNG)

### Portfolio Item Page

This screen shows you one of the items that compose your template, containing the neccesary information to represent a project.

![Item](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/portfolioItemPremium.PNG)

### My Templates Page

This screen shows you a catalog within the templates you own.

![Templates](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/myTemplates.png)

### Configuration Page for Basic Information

This screen allows the user to modify his personal information such as name, age, etc.

![ConfigurationBasic](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/configBasic.png)

### Configuration Page for Advanced Information

This screen allows the user to modify more complex information, for example its profile pic or its personal description.

![ConfigurationAdvanced](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/configAdvanced.png)

### Configuration Page for Portfolio Item Information

This screen allows to upload or delete portfolio items to your personal template.

![ConfigurationItem](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/configBasic.png)

### Configuration Page for Password

This screen allows to reset the password of your account.

![ConfigurationPassword](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/configPassword.png)

### Search Page

The screen used to find new users, filtered by categories, which you can talk to and take a look on its Template.

![Search](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/search.png)


### Shop Page

The screen used to get new Templates, either free or premium.

![Shop](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/shop.png)


### Admin Users Page
This screen shows a list of all users in Porthub

![AdminUsers](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/adminUserList.png)

### Admin Banned Users Page
This screen shows a list of all banned users in Porthub

![AdminBannedUsers](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/adminBannedUsers.png)

### Admin Templates List Page
This screen shows a list of all templates avaible in Porthub

![AdminTemplatesList](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/adminTemplatesList.png)

## Navigation diagram

Once we have stablished which are the pages that compose our system, we shall take a look at the flow between pages that emerges through the interaction with them.
![Diagramm](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase1/screen_diagram.png)


## Phase 2

### Navigation

#### Navigation diagram

![Diagramm](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase2/screen_diagram.png)

### Diagram with the entities in the database
Below is the diagram with the database entities, their fields and the relationships between them.
entities, their fields and the relationships between them. 

![Diagram](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase2/entityDiagram.png)

### Class diagram and templates
This is the class diagram with all the relations between the different classes in the project.

![Diagram](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/diagrams/Class%20Diagram.svg)

### Execution instructions
First of all, in our project we are using Java 11, MySQL community server 8.0.23, Spring Boot 2.4.3, Maven 4 and some other dependencies that are installed in the pom.xml file. We are using the intelliJ Ultimate Edition IDE but Visual Studio Code or Eclipse STS can be used too. We just need to download the zip project from our repository and open it in your IDE or directly synchronize with VCS using the HTTP in the IDE. After that, you have to open the project, install the dependencies and run the project.

To make sure the application works, you have to create a schema in the database and remember your username and password of MySQL database, because the same data must be inserted in the application.properties file in the spring.datasource.url/.username/.password attributes. 

### Participation

#### Cristian
##### Description
In this phase, I worked a lot with ajax, implementing it in different pages like the search page, projects page or in your main portfolio to show you projects. I worked too with images uploading and updating them to the database, I got in some troubles, but in the end I manage to solve all the issues. The most important commit that I perform is the chat, that I achieve to work dynamically with some JavaScript tricks. Other stuff that I have made it's to fix some CSS, because despite to not being evaluable, from my point of view it's important to keep the aplicaction as aesthetic as possible.

##### Top 5 commits
1. [Full chat implementation](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/e2f33cba82aecee6d9253504f5f4a8e3153ce579)
2. [Completed ajax on search page](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/41e1aa9ab9c9a123e0ab1766d3282e5fc3e543fd)
3. [Fixed portfolio items images on update](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/b3a798ae267a0f44433e0668dfbae0cbf07e49a8)
4. [Update portfolio item info](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/145737d5187b879f163ea34b07fee716a341e6df)
5. [Search bar](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/33946eef4a5e56aba9ea08d368a56ee2cc4d3ae0)

##### Top 5 files
1. __SearchController.java__
2. __PortfolioItemService.java__
3. __SearchService.js__
4. __ajax.js__
5. __chat.js__

#### José Manuel
##### Description
My priority in this phase has been working in the application's security. I've implemented the login and CSRF functionalities and URLs security configuration depending on the user's role. Also, I've worked in a first part of the chat functionality, setting the default users in the database, creating the error page, and I've helped my teammates in the development of the rest of the application's backend and solving problems and errors which couldn't be solved.

##### Top 5 commits
1. [Functional login](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/33a59863fddb84d2f2f546d865ca9583d77c3cae)
2. [First part of login](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/40155d79008a1a585e160ac865afe273b672e1cb)
3. [Basic chat implementation](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/ffc5b35ed8e0d50a85ec7c722608c0eff8a87f84)
4. [Enabled CSRF and login with new users](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/ac201c505fc8a16e0b34e33171e5a6708871c3b2)
5. [Set images in default users](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/691aca77e8a30a000eeaa780f1c40e53d510455a)

##### Top 5 files
1. __SecurityConfiguration.java__
2. __CSRFHandlerConfiguration.java__
3. __RepositoryUserDetailsService.java__
4. __WebSocketConfig.java__
5. __DataBaseController.java__

#### José Justo 
##### Description
In this phase I have worked mainly on the use of user information, allowing it to be displayed and implemented clearly in the templates.
Therefore I have worked on everything that had to do with the use of templates and portfolio items, allowing its correct creation, viewing and listing.
In the shop page I have implemented that the templates are shown, you can buy them and you can preview them.
On the my templates page I have implemented that the user can select which of the templates that he/she has wants to activate.
In addition to implementing the advanced query.
In general I have worked on the most intrinsic parts of the application.

##### Top 5 commits
1. [Fixed link to other users templates](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/4451b25ab6dfbabfd58436895db8063bcd517e34)
2. [Store and my-templates screens synchronized with user data](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/591d935de12af097348bddc118dd07b658f5c2c8)
3. [Improved my-templates screen showing which is your active template](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/5be545504248d4bd2594ed6a39b2c69ea121436a)
4. [Improved the shop page](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/d962f85895c9b5df089a99b84c33e3794b2347bf) 
5. [Added the sign up fuctionality](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/90d9da358f6e7dba519b6a2070f0eea1b3aaa7cd)

##### Top 5 files
1. __TemplateController.java__
2. __ShopController.java__ 
3. __ActiveTemplateService.java__
4. __PurchasedTemplateService.java__ 
5. __ConfigController.java__

#### Aitor
##### Description
My principal task in this phase has been to do the admin functionallity. All the admin screens and the functions to manage the app without coding from the administrators
of the app. First of all, I did the front-end of all admin screens and prepared them to implement the database petition requests. Then, I implemented the list of all users
of the app with the button 'Delete user'. After that, I did the list of templates avaible on the app and added the option to add new templates. And then I coded the dinamic
graphic that tracks the total number of users registered on the app.
##### Top 5 commits
1. [Done the admin users list in admin.html](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/1419710632466f83360f74ba66fc0d49c3db81f7)
2. [Added to admin users the option to enter on admin.html. admin-app-graphics.html graphic changed some colors for better seeing](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/ec3f633991ca676a9ab16319fd95b7b5cf916033)
3. [admin.html delete user done, started the add template function](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/7808de6ee72fc605d8433d3550cf2a8c14a161ba)
4. [Add new template to admin-templates-list.html done and operative](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/a58da8e5410e94f90174c7644331cfce422b0fbc)
5. [Fixed links in admin-templates-list.html](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/247934921372f1de56391f0d97c18f4dda7ea395)

##### Top 5 files
1. __AdminController.java__
2. __TemplateControler.java__
3. __admin.html__
4. __admin-templates-list.html__
5. __admin-app-graphics.html__


## Phase 3

### API REST documentation
[Link to the OpenAPI specification](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/api-docs/api-docs.yaml)

[Link to the website specification](https://rawcdn.githack.com/CodeURJC-DAW-2020-21/webapp13/518cb63bae73b48354fd6ca7868605bec9e7250c/api-docs/api-docs.html)

### Class diagrama update
![Diagram](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/diagrams/Class%20Diagram%202.svg)

### Instructions for running the dockerised application
In order to run the app you need to have installed:
- docker (You can install it from https://docs.docker.com/engine/install/ or if you have snap package manager you can use `$ sudo snap install docker`)
- docker-compose (`$ sudo apt install docker-compose`)

Once you have the requirements, you need to go to the working directory `$ cd webapp13`, then go to `$ cd docker`.
Now you must generate the .jar file, here you have two options:
- Execute the command `$ sudo docker run --rm -v "$PWD":/data -w /data maven mvn package`.
- Execute the script `$ sudo ./create_image.sh`, but it will also generate a docker image that isn't neccesary in this step.

Finally use `$ sudo docker-compose up` to run the application

### Documentation for building the docker image

### Participation

#### Cristian
##### Description
In this phase, I' ve been working in the UserRestController to manage the users information. I have dockerized the app too and made the bash script to create the docker image. Also I made some refactor to the code of the previus phase, to bring some organization.
##### Top 5 commits
1. [App dockerized ](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/32a05686b69e5b97cfb8a1129e8723eb9358de14)
2. [Bash script to create docker image](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/49228d3a74977e337d3c29ade52ed6b9c19b30d8)
3. [UserRestController full implemented ](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/8bbf9ce889486bf323d568aff7e2791048b94435)
4. [Functional way of uploading photos](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/aa6a9eb123fbace3f69677d11f7ab0bbcc8cb94a)
5. [Full service refactor](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/94ca9c5e1e5050d949252d208064889a9bae9df3)

##### Top 5 files
1. __UserRestController.java__
2. __UserDTO.java__
3. __PortfolioItemService.java__
4. __docker-compose.yml__
5. __create_image.sh__

#### José Manuel
##### Description
In this phase, I've been focused in the API Rest's security and in the Messages' requests of the API too. Also, I've helped my teammates with their tasks and to solve some errors that came up during the development of the API Rest.

##### Top 5 commits
1. [Security for API Rest](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/ea313dc8d5096b68bc479a7c1b29102dd18e6e14)
2. [MessageRestController and fixed chat.html](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/94708193d610434350b4dfadf59ba50abc02a649)
3. [MessageDTO, reactivated CSRF and modified error endpoint](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/521484337cf9a1aaabb1be13ba1a1522cff56833)
4. [MessageRestController modified and chat.js fixed](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/299445f01183c8bc35694c48ab079a03ffd90815)
5. [MessageRestController improved](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/08b5aab4d6875d6566b9325128b5813ab50ce236)

##### Top 5 files
1. __RestSecurityConfig.java__
2. __MessageRestController.java__
3. __LoginRestController.java__
4. __MessageDTO.java__
5. __MessageService.java__

#### José Justo 
##### Description
I have worked mainly on the rest controller methods of the portfolio item entity and the api documentation.
I have also helped in the development of the api collection and the dockerization of the application, specifically in the upload of the web application image to docker hub.

##### Top 5 commits
1. [Implemented all the method that user images in the portfolio item rest controller](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/f0a7a9c6e682543c4d68456c487e532f3e043f9d)
2. [Created a DTO for portfolio item](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/3dcf8e9b05a6a7574f10f73c2b06f2a48bfb17e8)
3. [Added the files corresponding to api documentation](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/abf82bbcd9c889040ab0a5b3cd60c6e83bbd787c)
4. [Securized all the portfolio item api methods](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/d3cfbab680693660757d3277fdf780007cc14b70)
5. [Generated documentation for the api](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/1b8c408117c53cb38fdf9aa1396993be6dc115ca)

##### Top 5 files
1. __PortfolioItemRestController.java__
2. __PortfolioItemDTO.java__
3. __API.postman_collection.json__
4. __PortfolioItemService.java__
5. __api-docs.html__

#### Aitor
##### Description
In this phase, my work has been based on doing the API REST Templates part. I did the TemlateRestController with all the possible methods related to the functionalities using the
Template entity and created the DTO. Then, I changed some parts of the user registration to add the creation date when a user creates his first account and this date get saved in
the database. This was necessary because we changed the graphic to show the users registered in PortHub every month.
By other side, after ending the Template API REST part, I prepared all the Postman tests related with this entity, and edited the RestController to add a security feature because
there are some methods available only for administrators.
##### Top 5 commits
1. [Done the TemplateRestController and adapted the TemplateService](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/0866a68e810a9db8256ab8f15cfba5e5b9b807ef)
2. [Added DTO to TemplateRestController and reformated the directory of Template API files](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/3ad0a6a3f338e896b465e13069871023bbbcf97a)
3. [Done the creation date in users](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/a1dfecf11a843c545be2b169bf9ce438d4701830)
4. [Adapted TemplateRestController with security](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/9c98dd10746236076a42c55edfc9115a43109f10)
5. [Edited files to change de Admin graphic](https://github.com/CodeURJC-DAW-2020-21/webapp13/commit/6b02f1c68cf70946dd43d69e02a81517c82a42e7)

##### Top 5 files
1. __TemplateRestController.java__
2. __TemplateDTO.java__
3. __AuthenticationController.java__
4. __UserService.java__
5. __UserRepository__

## Phase 4
 


