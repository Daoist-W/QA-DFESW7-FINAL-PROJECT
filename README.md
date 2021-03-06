# QA-DFESW7-FINAL-PROJECT
> Management application written in Java using the Spring Boot framework 

- [About](#about)
- [Getting Started](#getting-started)
- [Contributing](#contributing)
- [Licence](#licence)
- [Contact](#contact)

---

## About

### The Challenge (Why are we doing this ?)

![Java](https://img.shields.io/badge/-java-blue)
![mysql](https://img.shields.io/badge/-sql-yellow)
![bash](https://img.shields.io/badge/-bash-black)
![GitHub issues](https://img.shields.io/github/issues/Daoist-W/QA-DFESW7-FINAL-PROJECT)
![GitHub repo size](https://img.shields.io/github/repo-size/Daoist-W/QA-DFESW7-FINAL-PROJECT)
![GitHub last commit](https://img.shields.io/github/last-commit/Daoist-W/QA-DFESW7-FINAL-PROJECT)

This application is a solution to the final project set by QA Academy Software Development course

The brief is as follows 

>The MVP requirements of the project are as follows:
>
> -	Code fully integrated into a Version Control System using the feature-branch model: main/dev/multiple features.
> -	A project management board with full expansion on user stories, acceptance criteria and tasks needed to complete the project.
> -	A risk assessment which outlines the issues and risks faced during the project timeframe.
> -	A relational database, locally or within the Cloud, which is used to persist data for the project. 
> -	A functional application ‘back-end’, written in a suitable framework of the language covered in training (Java/Spring Boot), which meets the requirements set on your Scrum Kanban board.
> -	A build (.jar) of your application, including any dependencies it might need, produced using an integrated build tool (Maven).
> -	A series of API calls designed with postman, used for CRUD functionality. (Create, Read, Update, Delete)
> -	Fully designed test suites for the application you are creating, including both unit and integration tests.



### The Approach (How I expected the challenge to go)

For this project I wanted to focus on setting up the framework for completeing my tasks as efficiently as possible, without compromising on code quality. I prioritised documentation like the risk assessment, UML, story board and readme file first and ensured I had a clear road map in regards to what classes need to be made, what the SQL tables looked like and what their relationships were. The Jira board was to be set up with initial estmations based on my own arbitrary story point system to start with, based on 2^n, where n is the number of story points, and the overall result represented hours of producticity.

![alt text](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/images/jira-01.png "Screengrab of Jira Backlog")
> Click on Image for better resolution

Once this was set up I planned to create an initial project template using spring.io and then set out my Git branching model, with a main and a development branch. I would then create my first feature branch which focused on creating the underlying source code to support implementation of CRUD features;. This involved the necessary classes with the appropriate annotations, auto-generated constructors, getters and setters, configuration files, but empty methods and test cases.

![alt text](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/images/initial-source-code-sample-02.png "Screengrab of Skeleton code from commit code 1cb71d99bcc812e9abd3a9547ce7054d2b3df069 ")
> Click on Image for better resolution

Upon completing the above, I would then set out to implement the feature in the following order:
1. Implement Service Unit test case
2. Implement Service Unit Integration test case
3. Implement Service unit method to be tested and test agains 1 and 2
4. Implement Controller Web Integration test case
5. Implement Controller Integration test case
6. Implement Method against all test cases above, and confirm with Postman
7. Use smart commit to link with Jira, push to remote repo and merge with development branch
8. Delete feature branch, but retain commit history through git syntax merge `--no-ff`
9. throughout the above steps, I would record progress to the feature branch through smart commits


This process would then repeat with a new feature branch, and continue to do so until all tasks within the sprint are finished and the MVP has been achieved, upon which I would push the working application onto the main branch as the first release, and then pursue the stretch goals.


### The Outcome (What went well / What didn't go as planned)

What actually happened turned out to be different in a number of areas that I will highlight below:

> **Estimation of story points**

My initial story point estimation wasn't useful and in fact had to be changed a few times, in the end I settled for the standard fibonacci sequence as it indeed gives the best estimation of effort

> **Mapping feature branches to Tasks/Stories**

I tried to adopt a branch naming convention that mapped the story feature I was trying to implement. but I found that it was a little difficult to track properly when coding, as the scale of these features are so small that it became inefficient and required a lot of context switching. I switched to a more abstract naming convention, where the mapping occurs only in the smart commits.

> **Estimating the work involved with TDD**

I made it a point to enforce traditional TDD approach as much as possible, and due to only recently becoming more familiar with this testing first approach, the time taken to code was naturally higher than initially estimated, yet the code was better for it.

> **Leveraging Code Generation**

I endeavoured to create as flexible a foundation in source as possible before building out more complexity, because I've always found that as the code base grows it becomes increasingly difficult to keep track of where everything is. By having a consistent naming and coding convention across the different CRUD suites, it has allowed me to make more efficient use of reuseable code blocks.

> **Bug handling**

Due to a bug, I struggled for a meaningful amount of time to figure out why my testing suite wasn't working as it should, as my `@Transactional` annotation wasn't working. In the end a solution was found, but the underlying issue stll goes unaddressed. 

Other Configuration conflicts included issues with the application parsing the full range of CRUD operations with entity relations feature implemented. I worked around this by supressing the auto-fail configuration (see comments in application.properties), but this again is not ideal and in turn led to a stack overflow error from the toString() method of a DTO. Whilst this was again solvable, I would like to get to the root of the matter.


> Acceptance Criteria 

It was far more efficient for me to assign the role of acceptance criteria to my Controller System Integration tests source code, than to write them up manually, as I am more familiar with code. By having one source for the acceptance criteria, there is less ambiguity for me.


### Looking forward (Possible improvements for future revisions of the project)

> **Project Management**

As I get a better feel for story point estimation and using the Jira board, I feel that future sprints will be more organised and consistent across the project

> **Web Integration**

I would like to connect this application to a React-Redux website, where a replica of the database is stored in a redux store, this way I can build out a UI to present the account, job posting and seach results.

> **Security**

I'm unsatisfied with the lack of RBAC and would like to be able to seperate access rights with password control, where only managers can search up users, and users can only search jobs 

> **Deployment to the cloud** 

As an exercise in demonstrating familiarity with the cloud, I would like to deploy this application in a docker container to some cloud service, like Google, Azure or AWS.

> **Native Application** 

Being able to make this an application that could sit on a users desktop and work with its own UI is a longer term goal of mine


### The Stack

- Version Control System: Git
- Source Code Management: GitHub
- Kanban Board: Jira
- Database Management System: H2 / MySQL
- Core Language: Java
- API Dev platform: Spring Boot
- Build Tool: Maven
- Unit & Integration Testing: Junit

### Business Case (MoSCoW)

An up and coming care worker agency is looking to improve the management of their staff, and need an application that can allow them to keep track of staff, jobs and availability. They need to know who is working on what job, who is available for what job and also be able to create jobs, update them, assign them to staff and also delete jobs that are no longer valid / available.

From a staff point of view, staff must be able to create an account, search for avilable jobs and set their availability accordingly. They should be allowed to set multiple dates for availability, and also see what jobs they've been assigned. 

Both staff and jobs must be searchable by id, by name and by dates.

The application should have a feature that allows job assignment to User accounts 

Due to time constraints, the application will not have features such as a user friendly UI, hosted on a website, nor will it have any meaningful security implementation other than those offered inherently by Spring Boot such as SQL Injection protection.


### Project Management 

- Jira [Click here for original document](https://donisiko.atlassian.net/jira/software/projects/QDFP/boards/2)
![alt text](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/images/jira.png "Screengrab of Jira Board close to Sprint completion")
> Click on Image for better resolution

- Risk Assessment [Click here for original document](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/PDFs/QDFP_RISK_ASSESSMENT_MATRIX.pdf)
![alt text](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/images/RA.png "Risk Assessment Matrix")
> Click on Image for better resolution

- UML Diagram [Click here for original document](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/tree/main/documents/UMLs)
![alt text](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/images/UML.png "Screengrab of UML diagram")
> Click on Image for better resolution

- ERD [Click here for original document](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/PDFs/QDFP_ERD.pdf)
![alt text](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/images/ERD.png "Screengrab of the ERD")
> Click on Image for better resolution

- Unit & Integration tests [Click here for original document](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/PDFs/Test_Coverage_report.pdf)
![alt text](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/images/Testing.png "Screengrab of testing results")
> Click on Image for better resolution

- Testing with Postman [Click here for original documents](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/postman_collections)
![alt text](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/postman_collections/postman.png "Screengrab of using postman")
> Click on Image for better resolution


- Data persistence with MySQL (can be verified by running data.sql and schema.sql wihth devsql profile)
![alt text](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/documents/images/mysql-data_persistence.png "Screengrab of MySQL with data persistance")
> Click on Image for better resolution



---

## Getting Started

### Things to note
As this was an exercise, the app is set to work with H2 on production for testing purposes, as the github action container hasn't been configured to have MySQL installed; the test build with gitHub actions is dependent on H2.

To use the profile with MySQL, please change the application.properties profile setting to `spring.profiles.active=devsql`. 

To set up your MySQL database, please refer to the SQL scripts stored in the following location
-  [MySQL Setup](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/tree/main/documents/POC_code)

There is a working copy of this script inside the resources file, but due to occassional syntax collisions I've seperated the files used by MySQL and Hibernate to be sure.

Assuming you have JDK 11 and the corresponding JRE, the jar file located on the link below can be run by typing the following commands in your terminal

`cd /path/to/project/root/directory`

and then

`java -jar /path/to/jarfile.jar`

this should execute the jar file and allow you to run the application from your terminal

Files:
-  [Jar File](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT/blob/main/senpai-1.0.0-RELEASE.jar)

---

## Contributing

1. Fork the project
2. Create a feature branch: `git checkout -b feature-branch`
3. Commit your changes: `git commit -am 'Added new features'`
4. Push your changes: `git push origin feature-branch`
5. Create a pull request

---

## Licence

Distributed under the MIT Licence.

---

## Contact

**Developer:** Don Isiko - isikodon@googlemail.com

Repository Link: [QA-DFESW7-FINAL-PROJECT](https://github.com/Daoist-W/QA-DFESW7-FINAL-PROJECT)

Jira Board Link: [QDFP](https://donisiko.atlassian.net/jira/software/projects/QDFP/boards/2/roadmap)
