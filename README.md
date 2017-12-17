Movie Script Visualization Project

This project was created as a final project of CIT 591 Intro to Software Engineering at the University of Pennsylvannia.

Authors: Siyang You, Yue Yin, Yichao Li

The project is a moive script visualization application. It scratched the data of various movie scripts, then made analysis on them, and finally visualized the results. The basic working flow are listed as the followings:

1. A user enters a movie name or a movie genre to search.
2. When the user is doing accurate searching, a corresponding result will be shown up. Or, if it is a partial search, at most three most related movies' name and posters will be displayed.
3. Then the user chooses a movie and decides to visualize it. The movie scripts are sent to the Watson Natural language Understanding API, Watson Personality Insights API and Watson Toner Analyzer API, and returns the analysis of the emotion, sentiment and personalities.
4. The results are displayed in HTML format. The main characters, character's emotional changes, and the relationship between characters will be shown with graphs like circles, lines and bars.

Technologies utilized: IBM Watson API, TMDb API, WindowBuilder, JavaScript
