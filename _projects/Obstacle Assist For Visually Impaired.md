---
title: "Obstacle Assistant for Visually Impaired"
excerpt: "Final project for Mobile and Pervasive Computing course (15-821) at CMU. I wrote a Google Glass app and helped create a system to help blind people avoid overhanging obstacles.<br/><br/><img src='/images/ObjA4VI.jpg' style='height: 300px'>"
collection: projects
permalink: /projects/GoogleGlass/
date: 2023-12-1
---
Mobile and Pervasive Computing (15-821) is a class about more than just IoT. In particular, we covered various apsects of the space including cloudlets (datacenters in a box - pioneered by our professor, Satya), user interaction, and mobile hardware limitations. Using our learnings from the class, Murphy Austin and I created a system to help visually impaired people navigate around overhanging obstacles. We were able to leverage several unique benefits of cloudlets as well as get the unique experience of developing an app for the Google Glass. Our final prototype uses the Google Glass's camera to stream images of the user's view to a local cloudlet. The cloudlet processes the image by using the MiDaS model to create a depth map, filers and crops to get the relevant details, and uses an HRTF to generate a binaural audio alert. If an overhanging object is detected, the audio is streamed back to the Google Glass and the user can localize where in space the obstacle is based on the direction of the sound. 

Our poster and demo video can be found [here](https://www.cs.cmu.edu/~15-821/archive/#2023)