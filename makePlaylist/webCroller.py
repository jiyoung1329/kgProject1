#selenium
from selenium import webdriver
from selenium.common.exceptions import WebDriverException
from selenium.webdriver.common.keys import Keys


from bs4 import BeautifulSoup as bs
import requests
import time
import pandas as pd

# url = 'https://www.youtube.com/results?search_query=tj'
url = "https://www.youtube.com/watch?v=Qj1Gt5z4zxo&list=PL5rkMpxC5Ex8cD8itOnCtFjx0iTg1TgKY"

browser = webdriver.Chrome('chromedriver.exe')
browser.get(url)
time.sleep(5)


html_source = browser.page_source
soup = bs(html_source, 'lxml')

# 노래 유튜브 링크 따오기
videos = soup.select("#wc-endpoint")
print(videos[0].attrs["href"])

# 제목, 가수, 노레번호
contents = soup.select("#description > yt-formatted-string > span")
content = contents[0].get_text()
tmp = content.split("\n")
title_singer = tmp[0].split("--")
title = title_singer[0].strip()
singer = title_singer[1].strip()
number = tmp[1].split(".")[1]
print(title, singer, number)
time.sleep(10000)






# try:
#     browser.find_element_by_css_selector("#dismiss-button > a").click()
# except:
#     pass