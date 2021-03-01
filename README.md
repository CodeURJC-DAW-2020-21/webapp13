# Porthub
![Logo](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/logo.png)

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

![Home](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/index.png)


### Sign Page
The screen used to register a new user on our system.

![Sign](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/sign.png)


### Login Page
The screen used to login an existing user on the system.

![Login](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/login.png)

### Profile Page

This screen shows you the active template which represents your profile in the system.

![Profile](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/portfolioPremium.PNG)

### Portfolio Item Page

This screen shows you one of the items that compose your template, containing the neccesary information to represent a project.

![Item](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/portfolioItemPremium.PNG)

### My Templates Page

This screen shows you a catalog within the templates you own.

![Templates](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/myTemplates.png)

### Configuration Page for Basic Information

This screen allows the user to modify his personal information such as name, age, etc.

![ConfigurationBasic](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/configBasic.png)

### Configuration Page for Advanced Information

This screen allows the user to modify more complex information, for example its profile pic or its personal description.

![ConfigurationAdvanced](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/configAdvanced.png)

### Configuration Page for Portfolio Item Information

This screen allows to upload or delete portfolio items to your personal template.

![ConfigurationItem](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/configBasic.png)

### Configuration Page for Password

This screen allows to reset the password of your account.

![ConfigurationPassword](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/configPassword.png)

### Search Page

The screen used to find new users, filtered by categories, which you can talk to and take a look on its Template.

![Search](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/search.png)


### Shop Page

The screen used to get new Templates, either free or premium.

![Shop](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/shop.png)


## Navigation diagram

Once we have stablished which are the pages that compose our system, we shall take a look at the flow between pages that emerges through the interaction with them.
![Diagramm](https://github.com/CodeURJC-DAW-2020-21/webapp13/blob/main/images/screen_diagram.png)


## Phase 2
## Phase 3
## Phase 4
 


