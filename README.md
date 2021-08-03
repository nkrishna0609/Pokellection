# Pokéllection
![Pokéllection Banner](https://github.com/nkrishna0609/Pokellection/blob/master/readmeImages/pokellection_feature_graphic.png)
Carry your entire Pokémon card collection in your pocket. Pokéllection allows you to store all your Pokémon cards in one convenient place; on your phone.

## TLDR
* An **Android** app written in **Kotlin** which allows users to add, store, search for, and delete Pokémon cards from their collection 
* Utilized the **MVVM** architecture for modular, readable and scalable code
* Makes GET request to third-party API with Retrofit/OkHttp, to retrieve a card out of 12 000+ possibilities and parses received-JSON with Gson for deserialization into POJO
* Caches cards with Room Persistence Library
* Ensured clean, bug-free code with unit/instrumentation testing via JUnit/Mockito/Espresso testing frameworks

## Overview
Pokémon was a pivotal part of my childhood - from watching the shows, to collecting the cards. My younger brother himself recently got into the hobby of collecting Pokémon cards, and as of recent, his card binder is massive. I often notice him struggling to carry his binder around to show his friends his collection. So, I challenged myself to build an app which allows him to easily store and carry around his collection - on his phone!

## Demo
_**Please click on the image - it will take you to the YouTube video showcasing the app in action**_
[![IMAGE ALT TEXT](http://img.youtube.com/vi/FqfaR6qe-Ag/0.jpg)](https://www.youtube.com/watch?v=FqfaR6qe-Ag "Video Demo")

## Technical Design Aspect
### Built with
* **Kotlin**
  * Retrofit/OkHttp (REST/HTTP clients - for GET request to retrieve card data from API)
  * Room (persistence of cards for offline storage)
  * Gson (serialization/deserialization of POJO to JSON and vice versa)

### MVVM architecture
The Android app uses the MVVM architecture for modular, scalable, and maintainable code - by preventing tight coupling and "spaghetti" code. It ensures that the code has firm structure by making small classes with single responsibilities. 

As the name suggests, there are 3 components - the Models, the ViewModels, and the Views. 
The MVVM architecture ensures that:
* the Views do not contain business logic - they're solely present to handle the UI
* the ViewModel has direct reference to the Repository which is the single source of truth - the Repository has reference to the remote data source logic and the local database
* the ViewModel updates the View via LiveData - no need to manually update the View whenever the exposed data changes (the View observes the ViewModel)

### Unit/Instrumentation Testing
* Unit tests - tests logic of code
* Instrumentation tests - tests functionality of app + UI

Utilized JUnit/Mockito/Espresso frameworks to catch bugs/functionality errors in code.
