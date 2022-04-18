f = open("songDB.txt", 'r')
data = f.read().split("\n")

linkPath = []
number = []
for path in data : 
    if path[-15] == '/' : 
        linkPath.append(path[-15:])
        number.append(path[-8:-4])
    else : 
        linkPath.append(path[-16:])
        number.append(path[-9:-4])
        
writeFile = open("songDBWrite.txt", 'w')
for i in range(len(linkPath)) : 
    print("update song set link='{}' where num ='{}';".format(linkPath[i], number[i]))
    writeFile.write("update song set link='{}' where num ='{}';".format(linkPath[i], number[i]))
    writeFile.write("\n")
    
    
