---
title: Overview
description: Project Summary and intended users
menu: Overview
order: 0
---
## Summary

Garden Buddy will provide services to help gardeners better track their plants and know when the prime times are to plant them. This app will also suggest the best places to plant the starters based on the number of rows the user chooses.

## Intended users

* People who are new to gardening.

  >As a new gardener who wants to start growing my own food. I want to learn when to plant, and how to best organize my crops to get the most produce through the season.

* People who are seasoned gardeners.

  >As a seasoned gardener, I would love to be better organized and save paper by not having to label my plants with a physical sign. I would also like tips to make sure I am doing everything right for my plants.
  
## Functionality

* Gives different layouts of rows for planting and ability to select how many rows. 

* A list of plants with relevant information. Ie, when to plant, average amount of harvests for the season, if they re-seed on their own, what types of climate they are best suited for, etc...

* Watering schedule/tracker/reminder

* Will track how many fruits/vegetables you harvested for the season over the years.

* Will track weather through weather services and let you know if anticipated temperatures are expected to reach a level that is too hot/too cold for the plants and advise what steps to take to keep them safe.

## Persistent data

* Watering reminder. User will set times they would like to be reminded to water the plants.

* Reminder to shut off water. Can set how long the app should wait before reminding.

* Will remember what plants were planted last year, and how many fruits/vegetables you harvested if the info is added by user.

* Plant information. Different plants, yield, watering info, etc..
    
## Device/external services

Notifications permissions through the device

* https://developer.android.com/guide/topics/ui/notifiers/notifications

* Notifications will be used to remind users to water the plants at the scheduled times. 
  
* Notifications will also be used when the weather is going to be too hot/too cold and action is needed to be taken to prevent the plants from being damaged.

* The app will run without notifications if the user chooses to opt out of these added benefits.

Weather services

* https://www.climacell.co/data-catalog/
* or
* https://www.weather.gov/documentation/services-web-api  

* The weather services will be used to alert users if there are weather conditions that will be harmful to the plants. Ie, too hot or too cold..

* Weather services will also be able to advise user if there will be enough rain that day to not have to manually water the plants.

* Garden Buddy will function without the use of weather services if it is down, however the user would have to check the weather themselves if they are concerned about changing weather. 

## Stretch goals/possible enhancements 

* Smart watering system. To be able to link up to a smart device to access the watering system and be able to manually tell the watering system when to water.

* The ability to take a picture of packages of seeds to auto input in to the app, instead of manually typing each plant name in.

* Using a web service to host a larger database of plants and the relevant details.
