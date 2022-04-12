#selenium
from selenium import webdriver
from selenium.common.exceptions import WebDriverException
from selenium.webdriver.common.keys import Keys


from bs4 import BeautifulSoup as bs
import requests
import time
import pandas as pd

# url = 'https://www.youtube.com/results?search_query=tj'
url = "https://www.youtube.com/watch?v=B3eiaNqaCik&list=PL5rkMpxC5Ex9d9NsIwd2e6Q0k9oYIovDA&index=1"
url = "https://www.youtube.com/watch?v=MKf9CK1Y2-s&list=PL5rkMpxC5Ex9d9NsIwd2e6Q0k9oYIovDA&index=18"

options = webdriver.ChromeOptions()
options.add_argument("headless")
browser = webdriver.Chrome('chromedriver.exe', options=options)
browser.get(url)
time.sleep(5)

song_list = []
links = []

html_source = browser.page_source
soup = bs(html_source, 'lxml')

contents = soup.select("#description > yt-formatted-string > span")
content = contents[0].get_text().strip()



print("concept4")
tmp = content.split("\n")
infos = tmp[1].split("--")
title = infos[0].split("-")[0].strip()
singer = infos[0].split("-")[1].strip()
print(infos[1].split(" "))
number = infos[1].strip().split(" ")[-1]
song_list.append([number, title, singer])
print(number, title, singer)


# # 노래 유튜브 링크 따오기
# videos = soup.select("#wc-endpoint")
# print(videos[0].attrs["href"])
# for video in videos : 
#     links.append("https://youtube.com" + video.attrs["href"])

# # print(links)

# # 노래 링크들 다 접속해서 아래 정보 들고오기
# for link in links : 
#     # 브라우저 켜서 동적 로딩 다 잡아놓기
#     browser.get(link)
#     time.sleep(5)
    
#     # 해당 페이지 파싱
#     html_source = browser.page_source
#     soup = bs(html_source, 'lxml')
    
#     # 제목, 가수, 노레번호(플레이리스트마다 다름)
#     contents = soup.select("#description > yt-formatted-string > span")
#     try : 
#         content = contents[0].get_text().strip()
#         tmp = content.split("\n")
#         infos = tmp[1].split("--")
#         title = infos[0].strip()
#         singer = infos[1].strip()
#         number = infos[2].strip().split(" ")[2]
#         song_list.append([number, title, singer])
#         print(number, title, singer)
#     except : 
#         try : 
#             print("concept2")
#             content = contents[1].get_text().strip()
#             tmp = content.split("\n")
#             infos = tmp[1].split("--")
#             print("tmp : ", tmp, "infos: ", infos)
#             title = infos[0].strip()
#             singer = infos[1].strip()
#             number = infos[2].strip().split(" ")[2]
#             song_list.append([number, title, singer])
#             print(number, title, singer)
#         except : 
#             try : 
#                 print("concept3")
#                 content = contents[0].get_text().strip()
#                 tmp = content.split("\n")
#                 infos = tmp[0].split("--")
#                 title = infos[0].strip()
#                 singer = infos[1].strip()
#                 number = tmp[1].split(" ")[1]
#                 song_list.append([number, title, singer])
#                 print(number, title, singer)
#             except : 
#                 print("except")
#                 print(contents)
        
        
        
    
# print(song_list)

