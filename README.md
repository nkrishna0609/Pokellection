# Pokéllection
![logo](https://github.com/nkrishna0609/Pokellection/blob/master/readmeImages/pokellection_feature_graphic.png)
Carry your entire Pokémon card collection in your pocket. Pokéllection allows you to store all your Pokémon cards in one convenient place; on your phone.

Video Demo: https://drive.google.com/file/d/1AoPdJztH9F9ZN3-JEszjLg_oW1uoGHrC/view?usp=sharing

## Description of Functionalities ##
![logo](https://github.com/nkrishna0609/Pokellection/blob/master/readmeImages/pokellection_ss.png)
Simply enter the card's name, set name as well as set number to store it in your Pokéllection. You haven't memorized all 110+ Pokémon set names? Not to worry. Simply type in the name of your card, and identify which card is yours using the easy-to-navigate interface.

Sadly parting with a card in your real-life collection? Update your Pokéllection by simply swiping on the card which you want to say goodbye to.

No Wi-Fi? No worries. Pokéllection can display your beautiful collection offline. You can resume adding cards to your collection once you reconnect to Wi-Fi.

Download Pokéllection and be the greatest Pokémon card collector among your friends today!

Disclaimer:

Pokéllection is an unofficial, free fan-made app and is NOT affiliated, endorsed or supported by Nintendo or The Pokémon Company in any way.
Some images used in this app are copyrighted and are supported under fair use.
Pokémon and Pokémon character names are trademarks of Nintendo.
No copyright infringement intended.

Pokémon © 2002-2020 Pokémon. © 1995-2020 Nintendo/Creatures Inc.

## Architecture ##
This app was built using the MVVM design pattern, which ensures that there is separation between the Models and the Views. This introduces scalability/maintainability within the code, since the user interface is not dependent on the business logic. If the business logic code were to be changed/improved, there wouldn't be a need to do the same with the UI code due to the separation between the Models and the Views. Moreover, MVVM allows for easier testing, since the UI components can be tested separately from the business logic components. This separation of unit testing and instrumentation/UI testing allows for efficient test-driven development.
