# Fridgey

## Fridge efficiency at its finest!

> **What will the application do?**
> - Keep track of the items currently stored within the refrigerator 
> - Keep track of each item's expiration dates
> - View each item's quantity
> - View all the items
> - Search for an item 




> **Who will use it?**
> - Everyone wanting to efficiently manage their refrigerator
> - For those who often throw away items because they forget to eat it before the expiration date.

> **Why is this project of interest to you?**
> - Many people often forget that they had the item that they needed already in their fridge or couldn't find it because it was too well hidden. However, by building this software, I hope these events never occur again.  



> **What I want my application to do**
> - As a user, I want to be able to add items into the refrigerator 
> - As a user, I want to be able to remove items inside the refrigerator
> - As a user, I want to be able to search if an item is currently in the refrigerator  
> - As a user, I want to be able to view the expiration dates for a certain item
> - As a user, I want to be able to check the quantity of a certain item 
> - As a user, I want to be view all the items 

> **Instructions**
> - You can generate the first required action (adding) related to adding Xs to a Y by filling in the Name, Expiration Date, Quantity, and State text fields and pressing the "Add Item" button.
> - You can generate the second required action (removing) related to adding Xs to a Y by filling in the Name and pressing the "Remove" button.
> - You can locate my visual component from the application's background
> - You can save the state of my application by pressing the "Save" button
> - You can reload the state of my application by pressing the "Load" button

> **Actions**
> - An Item has been added
> - An Item has been searched and returned 
> - An Item has been searched and returned  
> - An Item has been removed

> **Reflection**
> - After reflecting on my UML class diagram, implementing the Singleton Pattern crossed my mind. This procedure will ensure that only one instance of the Refrigerator class would be instantiated across the entire program
and all objects would access the same single instance. By eliminating multiple instances and avoiding any possible inconsistencies of the Refrigerator state,
it would simplify my program design and will reduce memory usage and, therefore, runtime.
> - Although applying this pattern would bring benefit to my program, it may also cause certain problems. Firstly, 
if I wanted to have a refrigerator and a freezer in my program, instantiating two Refrigerator class may be necessary. Also, testing can become difficult with the implementation of this pattern
since the singleton object is shared across the entire program. Changing its state for testing can affect other parts of the program, creating difficulty to write independent tests.


[//]: # (> - As a user, when I select the quit option from the application menu, I want to be reminded to save my items to file and have the option to do so or not. )

[//]: # (> - As a user, when I start the application, I want to be given the option to retrieve my items from file.)

![poster](images/fridgey.png)

<img width="452" alt="Screenshot 2024-01-24 at 10 43 38 AM" src="https://github.com/hyt152004/Fridgey/assets/111398735/c97edc32-9949-4c1f-b075-0947706348cd">
<img width="423" alt="Screenshot 2024-01-24 at 10 43 51 AM" src="https://github.com/hyt152004/Fridgey/assets/111398735/c42a641a-9a4f-4f54-8367-31d099e69584">
<img width="416" alt="Screenshot 2024-01-24 at 10 44 05 AM" src="https://github.com/hyt152004/Fridgey/assets/111398735/2d69aa04-4ddc-4135-8e6f-173d74c39af3">
<img width="426" alt="Screenshot 2024-01-24 at 10 44 18 AM" src="https://github.com/hyt152004/Fridgey/assets/111398735/9bd4be79-4323-4d9a-b5d8-2fd4d69df34d">
<img width="426" alt="Screenshot 2024-01-24 at 10 44 27 AM" src="https://github.com/hyt152004/Fridgey/assets/111398735/a31e676b-2e6f-4927-8f8f-1cbf4ee64d84">






