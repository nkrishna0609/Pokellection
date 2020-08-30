# Pokéllection
Carry your entire Pokémon card collection in your pocket. Pokéllection allows you to store all your Pokémon cards in one convenient place; on your phone.

## Description of Functionalities ##
Simply enter the card's name, set name as well as set number to store it in your Pokéllection. You haven't memorized all 110+ Pokémon set names? Not to worry. Simply type in the name of your card, and identify which card is yours using the easy-to-navigate interface.

Sadly parting with a card in your real-life collection? Update your Pokéllection by simply swiping on the card which you want to say goodbye to.

No WiFi? No worries. Pokéllection can display your beautiful collection offline. You can resume adding cards to your collection once you reconnect to WiFi.

Download Pokéllection and be the greatest Pokémon card collector among your friends today!

## Design Pattern Choice ##
This app was built using the MVVM design pattern, which ensures that there is separation between the Models and the Views. This introduces scalability/maintainability within the code, since the user interface is not dependent on the business logic. If the business logic code were to be changed/improved, there wouldn't be a need to do the same with the UI code due to the separation between the Models and the Views. Moreover, MVVM allows for easier testing, since the UI components can be tested separately from the business logic components. This separation of unit testing and instrumentation/UI testing allows for efficient test-driven development.
