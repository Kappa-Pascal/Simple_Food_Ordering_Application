# Food Ordering Application

## Proposal
 
The application is used for food ordering in restaurants. It can hold and manage all items the restaurants provide, and it allows **customers** to
order items on this application. **Stuffs** can add and remove items to this application, thay can also manage the list of orders from customers.
Customers can order these provided items on this application. 

Having effective and efficient communication to stuffs in restaurants is almost impossible for me, espacially in the noisy environment or under
the circumstance that I do not know how to pronounce the food name correctly. I have to repeat myself multiple times to have effective
communication with stuffs. Therefore, I choose this project to improve the efficiency of communication of food ordering in restaurants.

## User Stories
- As a stuff, I want to create a new item with specified name, price, and stock.
- As a stuff, I want to add items to the item collection.
- As a stuff, I want to add the stock to a specified item.
- As a stuff, I want to mutate the name and price of a given item.
- As a stuff, I want to remove items from the the item collection.
- As a stuff, I want to view all items I am offering.
- As a customer, I want to view all items that stuffs offer.
- As a customer, I want to add items to my order.
- As a customer, I want to see to total price of my order.
- As a customer, I want to remove items from my order.
- As a customer, I want to see the printed receipt during the order.
- As a customer, I want to add my order to the collection of orders after finishing order.
- As a stuff, I want to see the collection of orders from customers.
- As a stuff, I want to be able to save all items I am offering to file if I choose so.
- As a stuff, I want to be able to load all items I am offering to file if I choose so.
- As a stuff, I want to delete order based on the given order ID when an order is complete.
- As a stuff, I want to ensure that the OrderList is empty when quitting the application.

# Instructions for End User

- You can add an item to the item collection (AllItems) by clicking button labelled "Add a single Items"
- You can add multiple items to the item collection (AllItems) by clicking button labelled "Add multiple items" and then entering the number of items you want to add.
- You can generate the first required action related to the user story "adding multiple Xs to a Y" (remove an item) by clicking button labelled "Remove An Item" and then entering the name of item you want to remove.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" (mutate an item's name and price) by clicking button labelled "Mutate An Item", entering the name of item you want to mutate, and then entering the new name and the new price of the item you want to mutate.
- You can locate my visual component by looking at the background image at the right half of the window.
- You can save the state of my application by clicking button labelled "Save".
- You can reload the state of my application by clicking button labelled "Load".

## Phase 4: Task 2
Tue Nov 26 14:32:57 PST 2024
An Item named SampleItem1 is added
Tue Nov 26 14:33:11 PST 2024
An Item named SampleItem2 is added
Tue Nov 26 14:33:30 PST 2024
An Item named SampleItem3 is added
Tue Nov 26 14:33:48 PST 2024
An Item named SampleItem3 is removed
Tue Nov 26 14:34:20 PST 2024
An Item originally named SampleItem1 is changed to the new name, RenamedSampleItem1, and its price is changed to $1.99
## Phase 4: Task 3

- I would like to refactor the GraphicUI class if I have more time. I will move all of the inner classes that extends AbstractAction and the
helper methods of reading users input to a new class called Actions in the UI package. Then, I will change the visibility of inner classes to
public and instantiate the Actions class in GraphicUI so I can instantiate these inner classes when adding buttons in GraphicUI class.

- The reason of this refactoring is that GraphicUI class does not follow the single responsibility principle and that the cohesion of the 
GraphicUI class is low, since the inner classes that extends AbstractAction and the helper methods of reading users input do not modify the 
fields other than allItems. While other methods set up the graphic UI and modify the fields other than allItems, these inner classes and helpers 
only handle user's input and only modify allItems.