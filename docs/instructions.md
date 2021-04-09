---
title: Instructions
description: Instructions to build app and how to use app.
menu: Overview
order: 50
---

## Build Instructions

* Start by going to 
  [HDawson-Coder GardenBuddy](https://github.com/ddc-java-12/personal-android-project-HDawson-coder)
  Click the green code button and copy the SSH link for the application.
  
* Create a new project in your IDEA of preference and clone from version control, insert the SHH
link, build the project, and have fun!
  
* In order to get the Google Sign In Service to work, you must create a project on Google Cloud 
Console, and create a Client ID in your project. When you do that, it will ask for an SHA-1
  certificate which you must generate from your cloned project in the IDEA.
  
## User Instructions

* When first launching the application, you will be asked to sign in to the Google sign in.
Once you have done so, the application will direct you to a mostly blank home page.
  
* Click the hamburger button on the upper left of the page to open the activity drawer. You will
see three menu options; Home, Plant, and Notes. Currently, Home and Plant are placeholder pages
  and only display some basic text.
  
* Select Notes to be taken to the most functional page of the application. You will see
three differenct categories, each with their own add note button; Pest Notes, Weather Notes,
  and Other Notes. You will also see a small arrow under the top menu bar on the right-hand side
  of the page. When you select this, you will be able to cycle through the list of different 
  plants preloaded into the application. You can see which plant you have currently selected by
  looking at the left upper corner of the application under the menu bar.
  
* When you select the Plus button on any of the categories, a dialog window will open up and prompt 
you to type in a note. Once you have typed characters in to the box, you will be able to select Ok.
  This will immediately save the new note into the category, and you are able to see all notes
  in each category by sliding up on the recycler view in the category box.