# Task distribution by P.O.

![kan](https://github.com/kiarashk8128/SWLab-HW8/assets/82291200/c3419927-7421-4255-8383-370ac969a288)


# Refactoring

## Two Instances of Facade

We first create a CompilerFacade class to provide a general interface to run the compiler.

Next, we create a CodeGeneratorFacade the separate the code generating functionalities from the other parts of the project.

## One Instance of Replacing Polymorphism for Switch Case

We replaced the switch case block of code in the Action file with polymorphism and implemented three types of Act each extending the Act class. The three new child classes were Accept, Reduce, and Shift.

## One Instance of Separate Query From Modifier

The change was applied to the getNextParam function because it was calling the getNextParameter function which was both returning a value and modifying the index.
So we added another Query method for getting the value and in the getNextParameter we call that, and then separately modify the index.

## One Instance of Self Encapsulated Field


The change was applied to klasses variable which was accessed directly in the methods. and this is how it was refactored:

1- Create a getter (and optional setter) for the field. They should be either protected or public.

2- Find all direct invocations of the field and replace them with getter and setter calls.

So a getter and setter function was written for it and then the getter was called instead of calling directly in methods.

## Other Refactoring Techniques, Extract Method:

Problem:

Extract Method is used to combat long methods that contain too much code, making them hard to understand and maintain. In the Parser class, the startParse method is quite long and contains multiple responsibilities.

Solution:

Refactor the startParse method in the Parser class by extracting smaller methods for different responsibilities. which is cut into these methods:initializeParser, parseTokens, handleShiftAction, and handleReduceAction.

## Other Refactoring Techniques, Replace Temp with Query:


Problem:

This refactoring technique is used to eliminate temporary variables that are only used to hold intermediate results. These temporaries can be replaced with a method call that performs the same computation.
In lexicalAnalyzer class, the tokenPattern temporary variable can be replaced with a method call.


Solution:

So we wrote a buildTokenPattern method to handle this temporary variable.


## Formatter Plugin

The formatter plugin has been added.

# Questions

## Question 1

کد تمیز: کد تمیز کدی است که فهمیدن، نگهداری و توسعه آن آسان باشد.
بدهی فنی: بدهی فنی به هزینه پنهان بازسازی اضافی ناشی از انتخاب یک راه‌حل آسان در حال حاضر به جای یک رویکرد بهتر که زمان بیشتری می‌برد، اشاره دارد.
بوی بد: بوی بد در کد به هر نشانه‌ای در کد منبع اشاره دارد که ممکن است به یک مشکل عمیق‌تر مانند افزونگی یا پیچیدگی غیرضروری دلالت کند.

## Question 2


**Bloaters:**

Bloaters are codes, methods, and classes that have increased to such gargantuan proportions that they are hard to work with. Usually, these smells do not crop up right away, rather they accumulate over time as the program evolves (and especially when nobody makes an effort to eradicate them). Different kinds of bloaters are:

- Long Method
- Large Class
- Primitive Obsession
- Long Parameter List
- Data Clumps


**Object-Orientation Abusers:**

All these smells are incomplete or incorrect applications of object-oriented programming principles. These include:

- Alternative Classes with Different Interfaces
- Refused Bequest
- Switch Statements
- Temporary Field


**Change Preventers:**

These smells mean that if you need to change something in one place in your code, you have to make many changes in other places too. Program development becomes much more complicated and expensive as a result.


**Dispensables:**

A dispensable is something pointless and unneeded whose absence would make the code cleaner, more efficient and easier to understand. They may include:

- Comments
- Duplicate Code
- Data Class
- Dead Code
- Lazy Class
- Speculative Generality


**Couplers:**

All the smells in this group contribute to excessive coupling between classes or show what happens if coupling is replaced by excessive delegation.

## Question 3

- **Category:** The Lazy Class bad smell falls into the category of "Dispensables," which refers to something that is unnecessary and can be removed.
- **Refactoring Suggestions:** To eliminate this smell, the refactorings suggested are "Inline Class" or "Collapse Hierarchy," where the class's responsibilities are merged into other classes.
- **When to Ignore:** This smell might be ignored when the class is expected to grow in the future with more responsibilities, or when the class serves as a placeholder for a future design.

## Question 4

**1. Long Method:**
Methods in classes like Phase1CodeGenerator.java and Phase2CodeGenerator.java might be too long, making them difficult to understand and maintain. For instance the constructor Phase1CodeGenerator is too long.

**2. Primitive Obsession:**
Primitive obsession occurs when primitive data types (e.g., int, float, String, etc.) are overused instead of creating small objects to handle specific tasks or concepts. Several String arrays are defined for reserved keywords and types in LexicalAnalyzer.java, which could be represented by enumerations or a dedicated class.


**3. Large Class:**
Classes such as Phase1CodeGenerator.java in phase1CodeGeneration are too large and might be doing too much, violating the Single Responsibility Principle (SRP)

**4. Switch Statements:**
In many parts of the code, i.e. the LexicalAnalyzer.java there exists long switch operators with many cases.

**5. God Class:**
A God Class (or Blob Class) is a class that takes on too many responsibilities, violating the Single Responsibility Principle (SRP). It is characterized by doing too much and knowing too much, often becoming a central point of control in the application. The Phase2CodeFileManipulator class is an example of a God Class.

**6. Duplicate Code:**
The method findMethodCallerBlock in Phase2CodeFileManipulator invokes the long chain of methods 'pairVector.elementAt(tokenPalace).getValue0().equals' too many times, resulting in duplicate code.

**7. Lazy Class:**
Classes like CompleteValueType.java suffer the lazy class code smell and are too small and useless.

**8. Data Class:**
The ClassInfo is a data class and does not have any significant functionalities, the methods are mostly getters and setters.

**9. Long Parameter List:**
The Phase2CodeGenerator constructor has many input parameters.

**10. Divergent Change:**
Divergent Change is when you find yourself having to change many unrelated methods when you make changes to a class. Phase1CodeGenerator.java is an example of a class having such a problem due to its complexity and size.


## Question 5

The Formatter Maven plugin automatically formats the source code of a project according to specified coding standards and guidelines. It can be helpful because it ensures a consistent code style across the entire codebase, making the code easier to read and maintain. Consistent formatting also reduces the likelihood of merge conflicts in version control systems when multiple developers are working on the same project.

The relationship between the formatter plugin and code refactoring is that both aim to improve the quality and maintainability of the code. While the formatter focuses on the surface level by ensuring consistent formatting, refactoring involves deeper changes to the code structure to improve its design and readability without altering its functionality. Using a formatter can complement refactoring efforts by maintaining a clean and standardized codebase, which makes it easier to identify and apply necessary structural improvements.
