---
title: "GPAssist"
excerpt: "GPAssist is a userscript/browser extension for Schoology which enables students to
more easily see how they are doing in their classes, analyze what they can improve
on, and see how future assignments will affect their grade and overall GPA.<br/><br/><img src='/images/GPAssist.png'>"
collection: projects
permalink: /projects/GPAssist/
date: 2019-4-12
---
*Awards*
* Honorable Mention - Code Fest (2019) at CHS
[Writeup](http://https://drive.google.com/file/d/1OXNvLl_WeQgj_sUxqHnZ2a80-cf2DGHF/view?usp=sharing)

GPAssist is a userscript/browser extension for Schoology which enables students to
more easily see how they are doing in their classes, analyze what they can improve
on, and see how future assignments will affect their grade and overall GPA.

<iframe width="560" height="315" src="https://www.youtube.com/embed/r70OTzcWVCM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

Project on [Github](https://gist.github.com/Mdkar/579b95041969c0753d4d421ebbc95311)

*How to install and use*
* *Step 0:* install the [Tampermonkey](https://chrome.google.com/webstore/detail/tampermonkey/dhdgffkkebhmkfjojejmpbldmpobfkfo?hl=en) extension in the Chrome Web Store.
Tampermonkey is a userscript manager for Chrome. The code we have written will run within this extension.

* *Step 1:* Install the userscript (GPAssist) here: https://gist.github.com/Mdkar/579b95041969c0753d4d421ebbc95311
by clicking on the “Raw” button (a Tampermonkey page should pop up shortly and you should be able to follow the instructions to set it up) -- Please note the disclaimer

* *Step 2:* Go to https://schoology.tesd.net/grades/grades
  * There should be a graph of grades of core classes
  * There should be a sidebar on the right side saying “GPAssist”
    * Click on the sidebar to reveal it

  *Graph Features:*
  * Mousing over the bar chart of grades shows their percent values
	* Clicking on one of the bars takes you to the grades page of that class
  * On a course’s grades page, clicking the new “Graphical View” button shows a graph of grades for each marking period
  * Clicking on one of the bars shows a breakdown of categories in that MP (click on “Graphical View)
  *Sidebar Features:*
  * 1st time you pull it up, you will see a list of all classes you are in. You can probably press continue without changing anything. The script automatically tries to detect which classes are core classes and their respective levels, but there are manual override level selectors and “Add” and “Remove” buttons if needed.
  * Next menu is your estimated weighted GPA, a list of core classes, and a feedback button (please give us your opinions and bugs!)
  * Clicking on a class in the list will give you a MP history of grades, letting you edit the current MP grades
    * _You can directly change the current MP grade or add new assignments to see how your MP grade (and GPA) changes_ 
    * Add in a midterm/final grade to make the GPA more accurate (as mentioned in the disclaimer, this tool will never be 100% accurate as we do not have access to grades in the HAC and we are not 100% sure on how the school handles rounding when calculating it)
  * You can do the same in a course’s grade page, but only that class is available in the menu and the GPA is the GPA in that particular class
