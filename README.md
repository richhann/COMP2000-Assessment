## Major creative work

Your major creative work is worth 60% of your unit total and is made up of four components:

  * In-class activities: 12%
  * Milestone 1: 8%
  * Milestone 2: 20%
  * Viva Exam: 20%

## Milestone 1
### Due date is Sunday 3rd of September 2023, at 11:55pm.

The code is in a *separate* repository from the weekly exercises:

`git clone https://github.com/mq-soft-tech/comp2000_2023_project.git`

__You should start from the repository branch tagged as "milestone1".__

## Prompt

An indie games company, LART Studios, is in the process of developing a standard set of tools and features of their future games. Fred, who was obsessed with inheritance and object oriented design was put in charge of developing an inventory system. Fred went overboard with inheritance and was fired for creating a such a rigid system, and the company has outsourced the inventory system to you. You have been provided with Fred's last known bit of work, and a basic user interface to allow you to play with it.

## Task 1

LART Studios noticed Fred used "magic numbers" as return values as well as potentially program crashing null return values. Convert these return values to something that pushes any future developers to handle both good and bad return values.


## Task 2

There is redundant code with the `Player` and `Seller`, particularly where purchases/transactions between the two happen. Refactor these classes to make good (light) use of inheritance.

__For full marks, you must use inheritance appropriately in your solution__.

## Task 3

Undo Fred's heavy handed use of inheritance with `Item`'s, such that it instead favours composition. This may lead you to editing the static method found in `ItemReader.java` where instances of each `Item` are created. `Item`'s are read one-by-one, and are given to either the `Player` or `Seller` which is handled for you. The data needed for creating items has been extracted for you.

The company has provided data models (`InventoryTableRow` & `CartTableRow`), as well as the interfaces (`ItemInterface` and `BasketInterface`) which your `Item`'s and `Basket`'s must implement for the user interface to function correctly. They have advised you to not modify the provided method signatures in these interfaces. However, their implementations will need to be changed for this task.

## Submission

You must submit a zipped archive of your code.  Your maker will download your zip file, open it in VSCode and run it from there.  Please don't submit unnecessary temporary files (such as .class files) in your zip file. So you should:

* delete any temporary files
* rename the parent folder (comp2000\_2023\_project) to match your student ID
* upload the zip

eg. if your student ID is 45678912 then the folder structure should look like

```
45678912
├── data
│   └── config.txt
├── lib
│   └── ui.jar
├── src
│   └── *.java
└── README.md
```

## Important notes

* Submitted code __*must*__ compile. If the code does not compile and/or run you will receive a mark of zero.
* Submissions must be in a __zip__ file, not rar or any other archive format and must follow the structure described above
