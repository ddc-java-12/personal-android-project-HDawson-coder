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

* Allows the user to choose their size of garden by square inches.  

* A list of plant names with relevant information. Ie, what temperature range needed to plant, how many inches of room needed per seed, and days to maturity.

* Watering schedule/tracker/reminder

* Will track whichever options you choose to track. Such as how many planted of each plant, how many fruit yielded total for each plant, and day planted-day of first harvest.

* Will track weather through weather services and let you know if anticipated temperatures are expected to reach a level that is too hot/too cold for the plants and advise what steps to take to keep them safe.

## Persistent data

* Will track any notes added regarding various incidents.

* Will track whichever options you choose to track. Such as how many planted of each plant, how many fruit yielded total for each plant, and day planted-day of first harvest.

* Plant information. Different plants, yield, watering info, etc.
    
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
