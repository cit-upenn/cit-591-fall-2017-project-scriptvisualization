Yue Yin:
scriptScaper: responsible for scraping script.
functions: 	1.get script given url
			2.get available moveis given search key
			3.get available movies given genre
scriptReader: responsible for analyzing script, return a script after analyzing
Step1: cut script to chunks
Step2: cut each chunk to name, dialogue and narrative
Step3: analyze each chunk, create a Persona if it doesn’t exist, otherwise update it. Create an edge if it doesn’t exist, otherwise update it
imageScraper: get images of characters from google using Google Customer Search API
Get movie post from moviedb
Relationships: maintain a graph that stores all characters and their relationships


Gui: visualize script object

Siyang:
Natural Language Understanding Output
Takes about 40-50 seconds to retrieve result of a movie.
Refer to SampleNaturalLangReport to see sample output of HashMap<String, HashMap<String, Double>> naturalLangUnderstanding.

	sentiment
		Review the overall sentiment and targeted sentiment of the content.

	emotion
		Analyze the overall emotion and the targeted emotion of the content.

	keywords
		Determine important keywords ranked by relevance.

	concepts
		Identifies general concepts that may not be directly referenced in the text.

	entities
		Extract people, companies, organizations, cities, geographic features, and other information from the content.

	categories
		Classify content into a hierarchy that's five levels deep with a score.
 
