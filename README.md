![firebase_app](https://user-images.githubusercontent.com/111382157/234222693-df8af5d1-0598-498d-b137-6022d8fa5442.jpg)

# Firebase-Project Registration and Login Using Java
  [![Windows](https://svgshare.com/i/ZhY.svg)](https://svgshare.com/i/ZhY.svg) [![Unlicense](https://img.shields.io/badge/License-Unlicense-blue.svg)](https://unlicense.org/) [![GitHub](https://badgen.net/badge/icon/github?icon=github&label)](https://github.com)

<br></br>
## Table of contents
* [General info](#general-info)
* [Instruction for use](#instruction-for-use)
* [Technologies](#technologies)
* [Setup](#setup)
* [Documentation](#documentation)
* [Sources](#sources)


<br></br>
## General info
This project is a simple registration and login application that uploads and queries data from firebase database.


<br></br>
## Instruction for use

The GUI is displayed when the program begins. But we need the registration to login the application.

![registration](https://user-images.githubusercontent.com/111382157/234312412-8e28d5d3-786b-46a4-b5fa-52d0d8af4c82.jpg)

Create registration like this form, the phone number must start + and the password must contain one uppercase,lettercase,special character and one number.

![registration_form](https://user-images.githubusercontent.com/111382157/234312961-89f6569f-bb23-41d1-afab-0c04541fa30a.jpg)

And as you can see below picture we created the new user in the firabase database, but the password is a sha1 hash code becouse of the security.

![users_data](https://user-images.githubusercontent.com/111382157/234341407-38e93c9a-e747-4bd2-867a-31d6adfe638c.jpg)


there is a code for convert string into a sha1 hash code:
 ```
  public String sha1(String input) {
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha1;
    }
 ```
 Next if we login succesfull there will be a table with all users data from firebase database that we created before.
 
 ![login_succesfull](https://user-images.githubusercontent.com/111382157/234326915-419426b9-d094-4e66-81d5-c3913e5a413d.jpg)

The users table was shown.

 ![users_table](https://user-images.githubusercontent.com/111382157/234327178-59b1d38b-1981-4e49-ae65-ba73e2e011ae.jpg)


<br></br>
<br></br>
## Technologies
Project is created with:
* <a href="https://www.oracle.com/java/technologies/downloads/#java20" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="20" height="20"/> Java SE version: 20.0.1</a>

* <a href="https://firebase.google.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/firebase/firebase-icon.svg" alt="firebase" width="20" height="20"/> Firebase version: 9.1.1</a>

*    <a href="https://netbeans.apache.org/Apache" target="_blank" rel="noreferrer">     NetBeans IDE 17 </a>
	

<br></br>
## Setup
To make similar project, need to create first private key in your firebase that you can access free if you have a google account.
Firebase service account can be used to authenticate multiple Firebase features, such as Database.

There is few screenshots how we can generate private key that a .json file:

![project_settings](https://user-images.githubusercontent.com/111382157/234336813-54cb6014-e588-4256-a69d-4cd2231cfb26.jpg)

![generate_key](https://user-images.githubusercontent.com/111382157/234338580-db91a686-ea36-4124-9366-43abaaf5aa8e.jpg)

And if you not use maven or gradle dependecies [here](https://jar-download.com/artifact-search/firebase-admin) is the jar files that for the java project
in the https://jar-download.com/


 <br></br>
## Documentation
To get starting your own firebase project with cloud fire base there is few documentions from google firebase docs [here](https://firebase.google.com/docs/firestore/quickstart?hl=en&authuser=0)
 <br></br>
## Sources
This app is inspired by official firebase tutorial on [youtube](https://www.youtube.com/watch?v=Mcsp59_2E7E&ab_channel=Firebase)

[![Ask Me Anything !](https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg)](https://github.com/Marcusso91)
