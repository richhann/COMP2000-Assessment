## Major creative work

Your major creative work is worth 60% of your unit total and is made up of four components:

  * In-class activities: 12%
  * Milestone 1: 8%
  * __Milestone 2: 20%__
  * __Viva Exam: 20%__

## Milestone 2
### Due date is Sunday 22nd of October 2023, at 11:55 pm.

The code is in the same repository as milestone1 but on a *separate* branch:

`git clone https://github.com/mq-soft-tech/comp2000_2023_project.git`

__You should start from the repository branch tagged as "milestone2".__

The boss at LART Studios liked your work for the previous milestone, and has hired you to replace Fred. In this milestone, the boss has recently learned about design patterns and wants some of them applied to the inventory management system. After some consideration, new features have been shortlisted for you to implement, each with the intended design pattern that you should use to implement that feature.

## Main changes since milestone 1
From milestone 1, the developers are LART Studios have done some work to the inventory system including some updates to the GUI.

### Exceptions and UI
When an exception is thrown in the application, the UI will display a popup error message for the user to see. If you find yourself catching an exception to solve the issue, but still wish for the popup error message to appear you can following for format of the following code snippet:

```java
void foo() throws Exception {
    try {
        // troublesome code
    } catch (Exception e) {
        // the fix
        throw e;  // re-throwing exception for UI to see.
    }
}
```

### Items, ItemDefinitions, and the ItemDictionary
Items of a single kind have their common attributes stored in the `ItemDefinition` class. These definitions are also stored in the `ItemDictionary` which is a singleton you can retrieve to lookup all item definitions in the program.
From the starting point, `Item` is a fairly barebones class (invoking stuff on its definition) which you may add to in the tasks.

### Player weight limit and storage
Players can only carry so much weight before being overburdened by it. You will find the limit at the bottom of the application. Items that are no longer needed to be held by the player can be stored in the storage (which has no weight limit). Items of use can then be retrieved from the storage, provided that it does not push the player over their carry weight limit.

## Task size guidance
Each task specifies a range of how many lines of code is needed to solve the task. This is to allow you to check that you aren't doing too much as the intended solution falls somewhere within this range. Do not use this range as strict a lower and upper limit, and note that it is not representative of difficulty of each task.

## Task 1 - Behavioural
For task 1 you have a choice between two behavioural patterns to implement.

### Option 1: Strategy Pattern - Item Searching
In the previous milestone, Fred had left some really nasty searching and sorting code. The searching has been brought out into the code which you can work on, which is not well written. With the starting point, search criteria is stored as a String which is a flawed system for specifying specific types. You should look around for all relevant bits of code for item searching, but a dev has expressed dissatisfaction about the `searchItems(String)` method in the `Inventory.java` file.

Additionally, creating the <s>sorting</s> searching buttons/options in the GUI has already been provided for you inside of the `SetupSearching(InventoryPage)` method of `App.java`. These importantly call the Inventory Pages `addSearchByButton` method where the name of the option and the action to perform is provided as a lambda function. You will need slightly modify these to facilitate the implementation of the strategy pattern.

Size guide: 40 to 80 lines of code.

### Option 2: Observer Pattern - Storage Updates
In the future the inventory system might be put to work in a multi-player game. In this scenario, multiple players may have access to the same Storage box where the Observer (aka Pub-Sub) might prove useful. For this to work smoothly, each player should have their own copy of Storage to work with. Whenever an update happens to the storage (such as storing or retreiving a new item), all players be updated about the new stock of the Inventory.

In the provided starting code, the player is given a reference to the instance of storage in the constructor of `App.java`. Apply the observer pattern such that the player is a subscriber/observer of the storage. 

Size guide: 20 to 50 lines of code

## Task 2 - Structural
LART Studios has noticed the booming popularity of a particular mining and crafitng game, and wishes to cash in on this idea. To do so, items are broken into two categories, base items and craftable items.  

* Base items exist on their own and have their own weight.  
* Craftable items are made up of other craftable items and/or base items, and have the weight be the sum of all items which make up the craftable item.

Your task is to use the `composite pattern` to facilitate crafting and uncrafting/pulling apart of craftable items. When an item is crafted, the items should be sourced from the players inventory. When an item is pulled apart/uncrafted, the items should be returned to the players inventory.

Within the Item page and crafting page of the GUI, you will find `Craft` and `Uncraft` buttons. The action that these buttons perform is defined in the `setupCrafting(ItemCraftPage)` and `setupUncrafting(ProductPage)` methods of `App.java`.In the provided starting point, these only print something to the console. Once your have implemented craft/uncrafting, you should change these to invoke what ever methods perform those actions.

Size guide: 40 to 80 lines of code.

## Task 3 - Improvements of your choice
This last task is to make at least two improvements of your choice to the application itself. This may be (but is not limited to) bug fixes, improving code quality, or applying some other design pattern that you see as appropriate. You will also need to document what you changed and why.

Your changes must reflect the topics and techniques that are represented in the lectures and in-class exercises, such as:  

* Interfaces  
* Generics  
* Exceptions  
* Lambdas  
* Design patterns

Your choice of improvement must include a rationale - how does your change enhance code quality, fix a bug, or improve the application?

* If you choose to implement bug fixes.
    * Documentation of bug fixes must include what the bug was and how your fix corrects the buggy behaviour.
* If you wish to improve code quality, this will generally be via refactorisation.
    * Documentation of quality improvements must indicate why the existing code needed improvement and how your changes improve code quality.  
* If you choose to implement yet another design pattern.
   * You must indicate which pattern you are implementing, including any deviation from the textbook's version of the pattern.
   * How does utilising your chosen pattern improve the application.

## Documentation
You should document what you have done inside of the `Documentation.md` file found in the `docs` directory.  

* For task 1, state which pattern you have decided to implement.  
* For both tasks 1 and 2, specify/itemise which files were changed.  
* For task 3, describe what was changed, why it was changed, and the benefits of said change(s).  

### Submission
You must submit a zipped VSCode project.  Your marker will download your zip file, open it in VSCode and run it from there.  Please don't submit unnecessary temporary files (such as class files) in your zip file.

## Important Note
* You should consider Milestone 2 and the Viva as a single assessment:
    * If you do not submit a reasonable attempt at Milestone 2 you cannot sit the Viva.
    * Similarly, if you are not successful with the academic integrity component of the Viva you will receive a mark of zero for milestone 2.
* Also note that failure to list and describe where code changes are made for any of your tasks then those tasks will not be marked.

#### Edit (13/10/2023):
correction, changed "sorting" to "searching" in Task 1.

#### Edit (18/10/2023):
a number of students have asked if they can use the pattern they didn't choose for Task 1 as their Task3. In other words if they implement *both* Strategy and *Observer* will that satisfy both Tasks 1 and 3? As long as it's clear which pattern is addressing which task, and you adhere to all the other requirements then, yes, this is allowed.

#### Edit (21/10/2023):
It seems that there is a bug in the `ui.jar` file that may prevent *some* approaches to using lambdas for the *uncraft* button from working. We've added a corrected version, but as the due date is tomorrow you will not be required to complete this aspect of task 2. In other words you only need implement the *craft* button not the *uncraft* button.