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

#### Pages

#### Navigation diagram

![Diagramm](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase2/screen_diagram.png)

### Instructions for implementation

### Diagram with the entities in the database
Below is the diagram with the database entities, their fields and the relationships between them.
entities, their fields and the relationships between them. 

![Diagram](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/phase2/entityDiagram.png)

### Class diagram and templates
This is the class diagram with all the relations between the different classes in the project.

![Diagram](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/diagrams/Class%20Diagram.svg)

### Execution instructions
First of all, in our project we are using Java 11, MySQL community server 8.0.23, Spring Boot 2.4.3, Maven 4 and some other dependencies that are installed in the pom.xml file. We are using the intelliJ Ultimate Edition IDE but Visual Studio Code or Eclipse STS can be used too. We just need to download the zip project from our repository and open it in your IDE or directly synchronize with VCS using the HTTP in the IDE. After that, you have to open the project, install the dependencies and run the project.

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
## Phase 4
 


