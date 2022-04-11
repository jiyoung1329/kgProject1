from bs4 import BeautifulSoup as bs
import requests


url = 'https://www.youtube.com/results?search_query=tj'
response = requests.get(url)
soup = bs(response.text, 'html.parser')
test = soup.select("#items > ytd-video-renderer")
print(test)

