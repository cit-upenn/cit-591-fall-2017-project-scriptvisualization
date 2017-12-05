Yue Yin:
scriptScaper: responsible for scraping script.
functions: 	1.get script given url
			2.get available moveis given search key
			3.get available movies given genre
scriptReader: responsible for analyzing script, return a script after analyzing
Step1: cut script to chunks
Step2: cut each chunk to name, dialogue and narrative
Step3: analyze each chunk, create a Persona if it doesn’t exist, otherwise update it. Create an edge if it doesn’t exist, otherwise update it
imageScraper: get images from google using Google Customer Search API
Relationships: maintain a graph that stores all characters and their relationships


Gui: visualize script object

 
Siyang Note:
I have not combined my java file with Yue's yet.

Sample output text files are rough outputs and may contain unnecessary details. Will polish them.

 
