Yue Yin:

scriptscaper: put url of the script as parameter of constructor, such as"http://www.imsdb.com/scripts/10-Things-I-Hate-About-You.html", it can scrape whole script from web

movielists： put search key as constructor parameter ，return a map, mapping from movie name to script url. Also can use a genre name to construct an object.

Siyang Note:
I have not combined my java file with Yue's yet.

Sample output text files are rough outputs and may contain unnecessary details. Will polish them.

Yue Yin
ScriptReader2 cut scripts into chunks, I think it makes more sense to add this function to scriptReader while scriptScripter is only responsible for scraping whole script from web.
