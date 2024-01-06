# Covid-19-Tracker

As the name says, this repo was a personal project to create an app that provides a world map imposed bubble-chart visualisation of live-data of Covid-19 cases, infections and deaths for every country during the pandemic period. Written in Java, the program uses Java Swing for the frontend UI and obtains Covid-19 statistics from the [Covid-19 dataflowkit API service](https://covid-19.dataflowkit.com/).

### Some interesting facts about the project
Being interested in how [Mercator Projections](https://en.wikipedia.org/wiki/Mercator_projection#:~:text=The%20Mercator%20projection%20maps%20trajectories,point%20to%20point%2C%20on%20the) worked, I decided to try manually implementing it to project country location data correctly onto the app's resolution dependent background image of the [Map-of-the-World](https://en.wikipedia.org/wiki/World_map). Needless to say the math was pretty fun ^.^ 


