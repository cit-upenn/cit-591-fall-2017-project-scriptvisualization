scriptscaper: 把有剧本的那个网址放在作为constructor的参数 比如"http://www.imsdb.com/scripts/10-Things-I-Hate-About-You.html" 可以返回一个Script，script里有这个剧本的全部内容，scriptscaper.writetofile 可以把这个剧本output到一个txt文档

movielists： 把搜索关键词作为constructor的参数，返回一个hashmap，key是电影名称，value是包含这个电影剧本的网址。 也可以把一种genre 作为参数，返回该genre的所有电影及剧本

test